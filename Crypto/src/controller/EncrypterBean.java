package controller;

import java.security.InvalidKeyException;

import model.*;

public class EncrypterBean {

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
		mongoDBClient = new MongoDBClient(username);
	}
	
	public String encryptAndSaveToDb() {
		try {
			encryptedData = desDataEncrypter.encrypt(plainInput, secretKeyString);
		} catch (InvalidKeyException e) {
			return "fail";
		}
		
		EncryptedDataStructure encryptedDataStructure = 
				new EncryptedDataStructure(encryptedData, header);
		
		mongoDBClient.insertEncryptedDataWithHeader(encryptedDataStructure);
		
		return "success";
	}
	
}
