package view;

import java.security.InvalidKeyException;
import java.text.*;
import java.util.Calendar;
import controller.*;
import model.*;

public class TemporaryConsoleDebugger {

	public static void main(String[] args) {

		LogBean logBean = new LogBean();
		logBean.setUsername("lucas");
		logBean.retrieveLogs();
		
		for(LogStructure log : logBean.getLogList()) {
			System.out.println("username: " + log.username + "\n" +
						"time: " + log.logTime + "\n" + 
						"message: " + log.message + "\n\n");
		}
		
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String now = dateFormat.format(calendar.getTime());
		System.out.println(now);
		
		KeyboardInputController keyboardInputController = 
				KeyboardInputController.getInstance();

		System.out.println("Username: ");
		String username = keyboardInputController.inputGenericData();
		
		System.out.println("Type the data to be encrypted: ");
		String dataToBeEncrypted = keyboardInputController.inputGenericData();

		String eightCharactersEncryptionKey = "";
		while(eightCharactersEncryptionKey.length() != 8)
		{
			System.out.println("Type exactly 8 characters for the encryption key: ");
			eightCharactersEncryptionKey = 
					readEncryptionKeyFromKeyboard(keyboardInputController);			
		}
			
		String encryptedData = 
				encryptDataWithSecretKey(dataToBeEncrypted, eightCharactersEncryptionKey);
		System.out.println("Encrypted data: " + encryptedData);
		
		System.out.println("Insert header for encrypted data: ");
		String header = keyboardInputController.inputGenericData();
		
		MongoDBClient dbClient = new MongoDBClient(username);
		EncryptedDataStructure eds = new EncryptedDataStructure(encryptedData, header);
		dbClient.insertEncryptedDataWithHeader(eds);
		
		String decryptedData = 
				decryptDataWithSecretKey(encryptedData, eightCharactersEncryptionKey);
		System.out.println("Decrypted data: " + decryptedData);
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
	
	private static String encryptDataWithSecretKey
	(String dataToBeEncrypted, String secretDESKeyString) {
		DESDataEncrypter dde = DESDataEncrypter.getInstance();		
		
		String encryptedData = null;
		try {
			encryptedData = dde.encrypt(dataToBeEncrypted, secretDESKeyString);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return encryptedData;
	}
	
	private static String decryptDataWithSecretKey
	(String encryptedData, String secretDESKeyString) {
		String decryptedData = null;
		DESDataDecrypter ddd = DESDataDecrypter.getInstance();
		try {
			decryptedData = ddd.decrypt(encryptedData, secretDESKeyString);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return decryptedData;
	}
}