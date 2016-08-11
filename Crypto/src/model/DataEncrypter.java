package model;

import java.security.InvalidKeyException;

public interface DataEncrypter {
	String encrypt(String plainInput, String secretKeyString) throws InvalidKeyException;
}
