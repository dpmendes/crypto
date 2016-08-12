package controller;

import java.security.InvalidKeyException;

import model.*;

public class EncrypterBean {

	private MongoDBClient mongoDBClient = null;
	private DESDataEncrypter desDataEncrypter = null;
	private String username = null;
	private String plainInput = null;
	private String secretKeyString = null;
	private String header;
	private String encryptedData;
	
	public EncrypterBean() {
		desDataEncrypter = DESDataEncrypter.getInstance();
		username = "fromOtherBean";
		secretKeyString = "abcdefgh";
	}
	
	public String encryptAndSaveToDb() {
		mongoDBClient = new MongoDBClient(username);
		EventLogger eventLogger = EventLogger.getInstance();
		DataToBeEncryptedStructure dataToBeEncryptedStructure = 
				new DataToBeEncryptedStructure(plainInput, header);
		try {
			encryptedData = desDataEncrypter.encrypt(plainInput, secretKeyString);
		} catch (InvalidKeyException e) {
			eventLogger.logFailedEncryptEvent(username, 
					dataToBeEncryptedStructure);
			return "fail";
		}
		
		EncryptedDataStructure encryptedDataStructure = 
				new EncryptedDataStructure(encryptedData, header);
		
		mongoDBClient.insertEncryptedDataWithHeader(encryptedDataStructure);
		mongoDBClient.close();
		mongoDBClient = null;
		
		eventLogger.logSuccessfulEncryptEvent(username, dataToBeEncryptedStructure);
		
		return "success";
	}
	
	public MongoDBClient getMongoDBClient() {
		return mongoDBClient;
	}

	public void setMongoDBClient(MongoDBClient mongoDBClient) {
		this.mongoDBClient = mongoDBClient;
	}

	public DESDataEncrypter getDesDataEncrypter() {
		return desDataEncrypter;
	}

	public void setDesDataEncrypter(DESDataEncrypter desDataEncrypter) {
		this.desDataEncrypter = desDataEncrypter;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlainInput() {
		return plainInput;
	}

	public void setPlainInput(String plainInput) {
		this.plainInput = plainInput;
	}

	public String getSecretKeyString() {
		return secretKeyString;
	}

	public void setSecretKeyString(String secretKeyString) {
		this.secretKeyString = secretKeyString;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	
}
