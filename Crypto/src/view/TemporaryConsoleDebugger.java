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
		KeyGenerator keyGen = null;
		
		try {
			keyGen = KeyGenerator.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		SecretKeyFactory secretKeyFactory = null;
		try {
			secretKeyFactory = SecretKeyFactory.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] key = {
			(byte)0x00, (byte)0x01, (byte)0x02, (byte)0x03, 
			(byte)0x04, (byte)0x05, (byte)0x06, (byte)0x07
		};
	
		KeySpec desKeySpec = null;
		try {
			desKeySpec = new DESKeySpec(key);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
//		SecretKey secretKey = null;
//		try {
//			secretKey = secretKeyFactory.generateSecret(desKeySpec);
//		} catch (InvalidKeySpecException e) {
//			e.printStackTrace();
//		}
		
		SecretKey secretKey = new SecretKeySpec(key, "DES");
		
		encryptedData = DESEncrypterDecrypter.encrypt(input, secretKey);
		System.out.println("Encrypted data: " + new String(encryptedData));
		
		decryptedData = DESEncrypterDecrypter.decrypt(encryptedData, secretKey);
		System.out.println("Decrypted data: " + new String(decryptedData));
	}

}
