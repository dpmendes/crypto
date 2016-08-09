package model;

import java.security.*;
import javax.crypto.*;

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
	public byte[] decrypt(byte[] encryptedInputBytes, SecretKey secretKey) throws InvalidKeyException {
		Cipher cipher = null;
		cipher = getCipherInstanceWithDESEncryption(cipher);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		byte[] decryptedDataBytes = null;
		
		try {
			decryptedDataBytes = cipher.doFinal(encryptedInputBytes);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return decryptedDataBytes;
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
