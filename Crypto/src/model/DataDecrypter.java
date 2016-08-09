package model;

import java.security.InvalidKeyException;
import javax.crypto.SecretKey;

public interface DataDecrypter {
	byte[] decrypt(byte[] encryptedInputBytes, SecretKey secretKey) throws InvalidKeyException;
}
