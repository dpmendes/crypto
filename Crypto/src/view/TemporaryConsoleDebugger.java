package view;

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

import prototype.DESEncrypterDecrypter;

public class TemporaryConsoleDebugger {

	public static void main(String[] args) {
		byte[] encryptedData;
		byte[] decryptedData;
		final byte[] input = "Data to be encrypted".getBytes();
	
		SecretKeyFactory secretKeyFactory = null;
		try {
			secretKeyFactory = SecretKeyFactory.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] key = new byte[] {
			(byte)0x10, (byte)0x20, (byte)0x30, (byte)0x40, 
			(byte)0x50, (byte)0x60, (byte)0x70, (byte)0x80
		};
	
		KeySpec desKeySpec = null;
		try {
			desKeySpec = new DESKeySpec(key);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		SecretKey secretKey = null;
		try {
			secretKey = secretKeyFactory.generateSecret(desKeySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		//SecretKey secretKey = new SecretKeySpec(key, "DES");
		
		encryptedData = DESEncrypterDecrypter.encrypt(input, secretKey);
		System.out.println("Encrypted data: " + new String(encryptedData));
		
		decryptedData = DESEncrypterDecrypter.decrypt(encryptedData, secretKey);
		System.out.println("Decrypted data: " + new String(decryptedData));
	}

}
