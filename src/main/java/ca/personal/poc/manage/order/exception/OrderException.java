/**
 * 
 */
package ca.personal.poc.manage.order.exception;

/**
 * @author Orlei Bicheski
 *
 */
@SuppressWarnings("serial")
public class OrderException extends RuntimeException {

	/**
	 * 
	 */
	public OrderException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OrderException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public OrderException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public OrderException(Throwable cause) {
		super(cause);
	}

}
