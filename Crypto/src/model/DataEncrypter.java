package model;

import javax.crypto.SecretKey;

public interface DataEncrypter {

	String encrypt(String input, SecretKey secretKey);
	
}
