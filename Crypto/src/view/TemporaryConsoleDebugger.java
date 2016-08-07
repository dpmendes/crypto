package view;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import prototype.DESEncrypterDecrypter;

public class TemporaryConsoleDebugger {

	public static void main(String[] args) {
		byte[] encryptedData;
		final byte[] input = "Data to be encrypted".getBytes();
		final byte[] initializationVectorBytes = "Input vector".getBytes();
		
		KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		SecretKey secretKey = keyGen.generateKey();
		
		encryptedData = DESEncrypterDecrypter.encryptDecrypt(input, secretKey, initializationVectorBytes);
		System.out.println("Encrypted data: " + encryptedData.toString());
	}

}
