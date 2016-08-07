package model;

import javax.naming.directory.InvalidAttributeValueException;

public class CipherData {
	private byte[] keyBytes;
	private byte[] inputVectorBytes;
	
	private CipherData() throws InvalidAttributeValueException {
		throw new InvalidAttributeValueException();
	}
	
	public CipherData(byte[] keyBytes, byte[] inputVectorBytes) {
		this.keyBytes = keyBytes;
		this.inputVectorBytes = inputVectorBytes;
	}
	
	public byte[] getKeyBytes() {
		return keyBytes;
	}
	public void setKeyBytes(byte[] keyBytes) {
		this.keyBytes = keyBytes;
	}
	public byte[] getInputVectorBytes() {
		return inputVectorBytes;
	}
	public void setInputVectorBytes(byte[] inputVectorBytes) {
		this.inputVectorBytes = inputVectorBytes;
	}
}