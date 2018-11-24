package club.tech4kids.demo.dao;	
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import club.tech4kids.demo.model.Product;

@Repository
public interface ProductDAO  extends JpaRepository<Product, Integer> {
	Product findById(int id);
	List<Product> findByPrixGreaterThan(int prixlimit);
	
	@Query("select p.id, p.name, p.prix from Product p  where p.prix > :prixlimit ")
	List<Product> findByPrixMax(@Param("prixlimit")  int prix);
}
