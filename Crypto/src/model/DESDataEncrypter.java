package model;

import java.security.*;
import javax.crypto.*;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

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
	public String encrypt(String plainInput, SecretKey secretKey) throws InvalidKeyException {
		Cipher cipher = null;
		cipher = getCipherInstanceWithDESEncryption(cipher);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	
		byte[] plainInputBytes = plainInput.getBytes();
		byte[] encryptedBytes = encryptInputBytesUsingCipher(plainInputBytes, cipher);
		
		char[] encryptedCharArray = Base64Coder.encode(encryptedBytes);
		String encryptedString = String.valueOf(encryptedCharArray);
		return encryptedString;
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