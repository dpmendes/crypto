package view;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import prototype.DESEncrypterDecrypter;

public class TemporaryConsoleDebugger {

	public static void main(String[] args) {
		byte[] encryptedData;
		byte[] decryptedData;
		final byte[] input = "Data to be encrypted".getBytes();
		KeyGenerator keyGen = null;
		
		try {
			keyGen = KeyGenerator.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		SecretKey secretKey = keyGen.generateKey();
		
		encryptedData = DESEncrypterDecrypter.encrypt(input, secretKey);
		System.out.println("Encrypted data: " + new String(encryptedData));
		
		decryptedData = DESEncrypterDecrypter.decrypt(encryptedData, secretKey);
		System.out.println("Decrypted data: " + new String(decryptedData));
	}

}
