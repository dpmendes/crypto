package view;

import java.security.InvalidKeyException;

import javax.crypto.SecretKey;

import controller.DataAndKeyInputController;
import controller.InvalidKeyLengthException;
import model.DESDataEncrypter;
import model.DESKeyGenerator;

public class TemporaryConsoleDebugger {

	public static void main(String[] args) {
		DataAndKeyInputController dataAndKeyInputController = DataAndKeyInputController.getInstance();
		
		System.out.println("Type the data to be encrypted: ");
		String dataToBeEncrypted = dataAndKeyInputController.inputDataToBeEncrypted();
	
		//SecretKey secretDESKey = DESKeyGenerator.generateRandomDESKey();
		SecretKey secretDESKey = null;
		try {
			secretDESKey = DESKeyGenerator.generateDESKeyFromEightCharactersString("abcdefgh");
		} catch (InvalidKeyLengthException e1) {
			e1.printStackTrace();
		}
		
		DESDataEncrypter dde = new DESDataEncrypter();		
		
		String encryptedData = null;
		try {
			encryptedData = dde.encrypt(dataToBeEncrypted, secretDESKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		System.out.println("Encrypted data: " + encryptedData);
	}

}