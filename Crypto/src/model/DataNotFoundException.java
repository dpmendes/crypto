package model;

public class DataNotFoundException extends Exception {
	private static final long serialVersionUID = 5463911838795759332L;
	private String message;
	
	public DataNotFoundException() {
		message = "";
	}
	
	public DataNotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
