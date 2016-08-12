package controller;

import java.security.InvalidKeyException;
import model.*;

public class DecrypterBean {

	private DESDataDecrypter desDataDecrypter = null;
	private String username = null;
	private String plainData = null;
	private String encryptedInput = null;
	private String secretKeyString = null;
	
	public DecrypterBean() {
		username = "fromOtherBean";
		secretKeyString = "poiuytre";
		desDataDecrypter = DESDataDecrypter.getInstance();
	}
	
	public String decrypt() {
		EventLogger eventLogger = EventLogger.getInstance();
		DataToBeDecryptedStructure dtbds = 
				new DataToBeDecryptedStructure(encryptedInput, "");
		try {
			plainData = desDataDecrypter.decrypt(encryptedInput, secretKeyString);
		} catch (InvalidKeyException e) {
			eventLogger.logInvalidKeyEvent(username, dtbds);
			return "fail";
		} catch (IllegalArgumentException e) {
			eventLogger.logInvalidInputEvent(username, dtbds);
			return "invalidinput";
		}
		
		eventLogger.logSuccessfulDecryptEvent(username, dtbds);
		return "success";
	}
	
	public DESDataDecrypter getDesDataDecrypter() {
		return desDataDecrypter;
	}

	public void setDesDataDecrypter(DESDataDecrypter desDataDecrypter) {
		this.desDataDecrypter = desDataDecrypter;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlainData() {
		return plainData;
	}

	public void setPlainData(String plainData) {
		this.plainData = plainData;
	}

	public String getEncryptedInput() {
		return encryptedInput;
	}

	public void setEncryptedInput(String encryptedInput) {
		this.encryptedInput = encryptedInput;
	}

	public String getSecretKeyString() {
		return secretKeyString;
	}

	public void setSecretKeyString(String secretKeyString) {
		this.secretKeyString = secretKeyString;
	}
}
