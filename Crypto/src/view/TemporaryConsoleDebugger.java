package view;

import java.security.InvalidKeyException;

import javax.crypto.SecretKey;

import model.DESDataEncrypter;
import model.DESKeyGenerator;

public class TemporaryConsoleDebugger {

	public static void main(String[] args) {
		String input = "Data to be encrypted";
	
		SecretKey secretDESKey = DESKeyGenerator.generateDESKey();
		DESDataEncrypter dde = new DESDataEncrypter();		
		
		String encryptedData = null;
		try {
			encryptedData = dde.encrypt(input, secretDESKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		System.out.println("Encrypted data: " + encryptedData);
	}

}