package model;

import java.security.*;
import javax.crypto.*;

public class DESDataEncrypter implements DataEncrypter{
	private static DESDataEncrypter desDataEncrypterInstance = null;
	
	private DESDataEncrypter() {
	}
	
	public static DESDataEncrypter getInstance() {
		if(desDataEncrypterInstance == null)
			desDataEncrypterInstance = new DESDataEncrypter();
		return desDataEncrypterInstance;
	}
	
	@Override
	public String encrypt(String input, SecretKey secretKey) throws InvalidKeyException {
		Cipher cipher = null;
		cipher = getCipherInstanceWithDESEncryption(cipher);
		initializeCipherInEncryptionModeWithSecretKey(cipher, secretKey);
	
		byte[] inputBytes = input.getBytes();
		byte[] encryptedBytes = encryptInputBytesUsingCipher(inputBytes, cipher);
		
		return new String(encryptedBytes);
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

	private void initializeCipherInEncryptionModeWithSecretKey(Cipher cipher, SecretKey secretKey) throws InvalidKeyException {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		} catch (InvalidKeyException e) {
			throw e;
		}
	}
	
	private byte[] encryptInputBytesUsingCipher(byte[] inputBytes, Cipher cipher) {
		byte[] encryptionResult = null;
		try {
			encryptionResult = cipher.doFinal(inputBytes);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptionResult;
	}

}