package view;

import java.security.InvalidKeyException;

import javax.crypto.SecretKey;

import controller.KeyboardInputController;
import controller.InvalidDESKeyLengthException;
import model.DESDataEncrypter;
import model.DESKeyGenerator;

public class TemporaryConsoleDebugger {

	public static void main(String[] args) {
		KeyboardInputController keyboardInputController = 
				KeyboardInputController.getInstance();
		
		System.out.println("Type the data to be encrypted: ");
		String dataToBeEncrypted = keyboardInputController.inputDataToBeEncrypted();

		String eightCharactersEncryptionKey = "";
		while(eightCharactersEncryptionKey.length() != 8)
		{
			System.out.println("Type exactly 8 characters for the encryption key: ");
			eightCharactersEncryptionKey = 
					readEncryptionKeyFromKeyboard(keyboardInputController);			
		}
			
		SecretKey secretDESKey = 
				generateDESKeyFromEightCharactersString(eightCharactersEncryptionKey);
		
		String encryptedData = encryptDataWithSecretKey(dataToBeEncrypted, secretDESKey);
		System.out.println("Encrypted data: " + encryptedData);
	}

	private static String readEncryptionKeyFromKeyboard
					(KeyboardInputController keyboardInputController) {
		try {
			String eightCharactersEncryptionKey = keyboardInputController.
					inputDESEightCharactersEncryptionKey();
			return eightCharactersEncryptionKey;
		} catch (InvalidDESKeyLengthException e2) {
			return "";
		}
	}
	
	private static SecretKey
	generateDESKeyFromEightCharactersString(String eightCharactersEncryptionKey) {
		SecretKey secretDESKey = null;
		try {
			secretDESKey = DESKeyGenerator.
					generateDESKeyFromEightCharactersString
					(eightCharactersEncryptionKey);
		} catch (InvalidDESKeyLengthException e1) {
			e1.printStackTrace();
		}
		return secretDESKey;
	}

	private static String encryptDataWithSecretKey(String dataToBeEncrypted, SecretKey secretDESKey) {
		DESDataEncrypter dde = DESDataEncrypter.getInstance();		
		
		String encryptedData = null;
		try {
			encryptedData = dde.encrypt(dataToBeEncrypted, secretDESKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return encryptedData;
	}
}