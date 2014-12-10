package Exceptions;

public class PlayerEndOfGameException extends Exception {
	/** */
	private static final long serialVersionUID = 1L;
	
	/** Save the Message of the exception object*/
	private String message;

	/**
	 * full ctu'r
	 * @param message
	 */
	public PlayerEndOfGameException(String message) {
		this.message = message;
	}

	/**
	 * get the value of meesage
	 */
	public String getMessage() {
		return message;
	}
	
}
