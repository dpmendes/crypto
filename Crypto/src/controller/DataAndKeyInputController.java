package controller;

import java.util.Scanner;

public class DataAndKeyInputController {
	private static DataAndKeyInputController dataAndKeyInputControllerInstance = null;
	private String dataToBeEncrypted;
	private String encryptionKey;
	private Scanner keyboardInputScanner;

	private DataAndKeyInputController() {
		keyboardInputScanner = new Scanner(System.in);
	}
	
	public static DataAndKeyInputController getInstance() {
		if(dataAndKeyInputControllerInstance == null)
			dataAndKeyInputControllerInstance = new DataAndKeyInputController();
		return dataAndKeyInputControllerInstance;
	}
	
	public String inputDataToBeEncrypted() {
		dataToBeEncrypted = keyboardInputScanner.nextLine();
		return dataToBeEncrypted;
	}
	
	public String inputDESEightCharactersEncryptionKey() throws InvalidKeyLengthException {
		encryptionKey = keyboardInputScanner.nextLine();
		int encryptionKeyLength = encryptionKey.length();
		if(encryptionKeyLength != 8)
			throw new InvalidKeyLengthException(encryptionKeyLength);
		return encryptionKey;
	}
		
	public String getDataToBeEncrypted() {
		return dataToBeEncrypted;
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}
	
	public void finalize() {
		keyboardInputScanner.close();
		keyboardInputScanner = null;
	}

}
