package model;

import java.security.InvalidKeyException;

public interface DataDecrypter {
	String decrypt(String encryptedInput, String secretKeyString) throws InvalidKeyException;
}
