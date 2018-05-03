package pointOfSale.model;

/**
 * The class represent a exception when the user type string which is not in database.
 * 
 *
 */
public class InvalidBarCodeException extends Exception{
	
	public InvalidBarCodeException() {
		super();
	}
	
	public InvalidBarCodeException(String message) {
		super(message);
	}
	
	public InvalidBarCodeException(String message, Exception cause) {
		super(message, cause);
	}
	

}
