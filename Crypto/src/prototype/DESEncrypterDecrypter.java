package prototype;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;

public class DESEncrypterDecrypter {
	public static byte[] encryptDecrypt(byte[] input, SecretKey secretKey, byte[] initializationVectorBytes) {
		Cipher cipher = null;
		
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
		
		byte[] encrypted= new byte[cipher.getOutputSize(input.length)];
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
}
