package model;

import java.security.InvalidKeyException;
import javax.crypto.SecretKey;

public interface DataEncrypter {
	byte[] encrypt(byte[] plainInputBytes, SecretKey secretKey) throws InvalidKeyException;
}
