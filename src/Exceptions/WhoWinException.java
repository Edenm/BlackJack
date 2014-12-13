package Exceptions;

public class WhoWinException extends Exception{
	/** */
	private static final long serialVersionUID = 1L;
	
	/** Save the Message of the exception object*/
	private String message;

	/**
	 * full ctu'r
	 * @param message
	 */
	public WhoWinException(String message) {
		this.message = message;
	}
	
	/**
	 * get the value of meesage
	 */
	public String getMessage() {
		return message;
	}
	
}
