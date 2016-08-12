package model;

import java.security.*;
import javax.crypto.*;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import controller.InvalidDESKeyLengthException;

public class DESDataDecrypter implements DataDecrypter {
	
	private static DESDataDecrypter desDataDecrypterInstance = null;
	
	private DESDataDecrypter() {
	}
	
	public static DESDataDecrypter getInstance() {
		if(desDataDecrypterInstance == null)
			desDataDecrypterInstance = new DESDataDecrypter();
		return desDataDecrypterInstance;
	}

	@Override
	public String decrypt(String encryptedInput, String secretKeyString)
			throws InvalidKeyException, IllegalArgumentException {
		SecretKey secretKey = generateDESKeyFromEightCharactersString(secretKeyString);
		
		Cipher cipher = null;
		cipher = getCipherInstanceWithDESEncryption(cipher);

		initializeCipher(secretKey, cipher);
		
		byte[] encryptedInputBytes = null; 
		
		try{
			encryptedInputBytes = Base64Coder.decode(encryptedInput);
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException
				("Base64 coder input must have a multiple of 4 characters.");
		}
				
		
		byte[] decryptedDataBytes = null;
		
		try {
			decryptedDataBytes = cipher.doFinal(encryptedInputBytes);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return new String(decryptedDataBytes);
	}
	
	private SecretKey generateDESKeyFromEightCharactersString
	(String eightCharactersEncryptionKey) {
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

	private void initializeCipher(SecretKey secretKey, Cipher cipher) 
			throws InvalidKeyException {
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey, cipher.getParameters());
		} catch (InvalidAlgorithmParameterException e1) {
			e1.printStackTrace();
		}
	}

	private Cipher getCipherInstanceWithDESEncryption(Cipher cipher) {
		try {
			cipher = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		return cipher;
	}


}
