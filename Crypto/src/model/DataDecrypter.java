package model;

import java.security.InvalidKeyException;
import javax.crypto.SecretKey;

public interface DataDecrypter {
	String decrypt(String encryptedInput, SecretKey secretKey) throws InvalidKeyException;
}
