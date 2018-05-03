package pointOfSale.model;

/**
 *
 * 
 * The class represent a exception when the user type empty string in
 * bar code field.
 *
 */
public class EmptyBarCodeException extends Exception {

	public EmptyBarCodeException() {
		super();
		
	}
	
	public EmptyBarCodeException(String message) {
		super(message);
	}
	
	public EmptyBarCodeException(String message, Exception cause) {
		super(message, cause);
	}
}
