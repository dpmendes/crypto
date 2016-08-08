package controller;

public class InvalidDESKeyLengthException extends Exception {
	private static final long serialVersionUID = -4263405485453469290L;
	private String message;
	
	public InvalidDESKeyLengthException() {
		message = "";
	}
	
	public InvalidDESKeyLengthException(String message) {
		this.message = message;
	}
	
	public InvalidDESKeyLengthException(int invalidKeyLength) {
		this.message = "The DES encryption key should have 8 characters, not " + 
						invalidKeyLength + ".";
	}

	@Override
	public String getMessage() {
		return message;
	}
}