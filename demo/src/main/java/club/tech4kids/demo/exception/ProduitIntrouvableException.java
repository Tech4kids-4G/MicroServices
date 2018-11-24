package club.tech4kids.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProduitIntrouvableException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1382362514497337575L;

	public ProduitIntrouvableException(String s) {
		super(s);
		
	}	

}
