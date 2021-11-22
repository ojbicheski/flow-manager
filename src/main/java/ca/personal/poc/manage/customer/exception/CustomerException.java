/**
 * 
 */
package ca.personal.poc.manage.customer.exception;

/**
 * @author Orlei Bicheski
 *
 */
@SuppressWarnings("serial")
public class CustomerException extends RuntimeException {

	/**
	 * 
	 */
	public CustomerException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CustomerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CustomerException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CustomerException(Throwable cause) {
		super(cause);
	}

}
