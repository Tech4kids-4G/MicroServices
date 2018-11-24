package club.tech4kids.demo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import club.tech4kids.demo.dao.ProductDAO;
import club.tech4kids.demo.exception.ProduitIntrouvableException;
import club.tech4kids.demo.model.Product;

@RestController
public class ProductController {
	
	@Autowired
	private ProductDAO prodcutDAO;
	
	
	@RequestMapping("/")
	  public String home() {
	    return "Spring boot is working!";
	  }
	
	@GetMapping(value = "Product/{id}")
	public Product afficheProduct(@PathVariable int id) throws ProduitIntrouvableException {
		Product produit = prodcutDAO.findById(id);
		if (produit == null){ throw new ProduitIntrouvableException("Le produit avec l'id: " + id + " n'exit pas");}
		return produit;
	}
	
	@GetMapping(value = "ListeProducts")
	public List<Product> listeProducts() {
		return prodcutDAO.findAll();
	}
	
	@GetMapping(value = "ListeProductsLimit/{prixlimit}")
	public List<Product> listeProducts(@PathVariable int prixlimit) {
		return prodcutDAO.findByPrixGreaterThan(prixlimit);
	}
	
	@GetMapping(value = "ListeProductsPrixMax/{prixmax}")
	public List<Product> listeProductsPrixMax(@PathVariable int prixmax) {
		return prodcutDAO.findByPrixMax(prixmax);
	}
	
	@PostMapping(value = "AddProduct")
	public ResponseEntity<Void> ajouterProduct(@Valid @RequestBody Product product) {
		Product created_product = prodcutDAO.save(product);
		if (created_product == null) {
			return ResponseEntity.badRequest().build();
		}
		URI product_location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(created_product.getId())
				.toUri();
		return ResponseEntity.created(product_location).build();
			}
	
	
	
	
}
