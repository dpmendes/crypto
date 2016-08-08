package model;

import java.security.InvalidKeyException;

import javax.crypto.SecretKey;

public interface DataEncrypter {

	String encrypt(String input, SecretKey secretKey) throws InvalidKeyException;
	
}
