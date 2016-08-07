package model;

public interface DataEncrypter {

	byte[] encrypt(byte[] input, CipherData cipherData);
	
}
