package controller;

import java.util.Scanner;

public class KeyboardInputController {
	private static KeyboardInputController dataAndKeyInputControllerInstance = null;
	private String encryptionKey;
	private Scanner keyboardInputScanner;

	private KeyboardInputController() {
		keyboardInputScanner = new Scanner(System.in);
	}
	
	public static KeyboardInputController getInstance() {
		if(dataAndKeyInputControllerInstance == null)
			dataAndKeyInputControllerInstance = new KeyboardInputController();
		return dataAndKeyInputControllerInstance;
	}
	
	public String inputGenericData() {
		return keyboardInputScanner.nextLine();
	}
	
	public String inputDESEightCharactersEncryptionKey() throws InvalidDESKeyLengthException {
		encryptionKey = keyboardInputScanner.nextLine();
		int encryptionKeyLength = encryptionKey.length();
		if(encryptionKeyLength != 8)
			throw new InvalidDESKeyLengthException(encryptionKeyLength);
		return encryptionKey;
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}
	
	public void finalize() {
		keyboardInputScanner.close();
		keyboardInputScanner = null;
	}

}
