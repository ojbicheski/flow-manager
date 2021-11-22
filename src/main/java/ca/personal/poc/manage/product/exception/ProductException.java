/**
 * 
 */
package ca.personal.poc.manage.product.exception;

/**
 * @author Orlei Bicheski
 *
 */
@SuppressWarnings("serial")
public class ProductException extends RuntimeException {

	/**
	 * 
	 */
	public ProductException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ProductException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ProductException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ProductException(Throwable cause) {
		super(cause);
	}

	
}
