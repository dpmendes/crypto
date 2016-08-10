package model;

import java.security.InvalidKeyException;
import javax.crypto.SecretKey;

public interface DataEncrypter {
	String encrypt(String plainInput, SecretKey secretKey) throws InvalidKeyException;
}
