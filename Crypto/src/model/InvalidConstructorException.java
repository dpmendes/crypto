package model;

public class InvalidConstructorException extends Exception {
	private static final long serialVersionUID = 107432281067934756L;
	private String message;
	
	public InvalidConstructorException() {
		message = "";
	}
	
	public InvalidConstructorException(String message) {
		this.message = message;
	}	
	
	public String getMessage() {
		return this.message;
	}
}