package controller;

public class InvalidKeyLengthException extends Exception {
	private static final long serialVersionUID = -4263405485453469290L;
	private String message;
	
	public InvalidKeyLengthException() {
		message = "";
	}
	
	public InvalidKeyLengthException(String message) {
		this.message = message;
	}
	
	public InvalidKeyLengthException(int invalidKeyLength) {
		this.message = "The DES encryption key should have 8 characters, not " + 
						invalidKeyLength + ".";
	}

	@Override
	public String getMessage() {
		return message;
	}
}