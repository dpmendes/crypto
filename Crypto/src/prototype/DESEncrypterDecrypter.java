package prototype;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;

public class DESEncrypterDecrypter {
	static Cipher cipher = null;
	
	public static byte[] encrypt(byte[] input, SecretKey secretKey) {
		cipher = null;
		
		try {
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		byte[] encrypted = new byte[cipher.getOutputSize(input.length)];
		int enc_len = 0;
		try {
			enc_len = cipher.update(input, 0, input.length, encrypted, 0);
		} catch (ShortBufferException e) {
			e.printStackTrace();
		}

		try {
			enc_len += cipher.doFinal(encrypted, enc_len);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (ShortBufferException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return encrypted;
	}
	
	public static byte[] decrypt(byte[] input, SecretKey secretKey) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey, cipher.getParameters());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		byte[] decrypted = null;
		try {
			decrypted = cipher.doFinal(input);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return decrypted;
	}
	
}
