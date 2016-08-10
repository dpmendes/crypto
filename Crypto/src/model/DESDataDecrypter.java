package model;

import java.security.*;
import javax.crypto.*;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

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
	public String decrypt(String encryptedInput, SecretKey secretKey)
			throws InvalidKeyException {
		Cipher cipher = null;
		cipher = getCipherInstanceWithDESEncryption(cipher);

		initializeCipher(secretKey, cipher);
		
		byte[] encryptedInputBytes = Base64Coder.decode(encryptedInput);
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
