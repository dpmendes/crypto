package test;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.DESDataEncrypter;

public class DESDataEncrypterTest {

	public static final String input = "Data to be encrypted";
	public static final String expectedEncryptedData = "\"YvT5¦ºïx¡ZI§—fE<?ø ";
	public SecretKey secretKey = null;
	
	@Before
	public void initialize() {
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		keyGenerator.init(56);
		SecretKey secretKey = keyGenerator.generateKey();
		this.secretKey = secretKey;
	}
	
	@Test
	public void encryptMethodShouldEncryptCorrectly() {
		DESDataEncrypter dde = new DESDataEncrypter();
		String encryptionResult = dde.encrypt(input, this.secretKey);
		System.out.println("encryptionResult = " + encryptionResult);
		System.out.println("expectedEncryptedData = " + expectedEncryptedData);
		assertTrue(encryptionResult.equals(expectedEncryptedData));
	}
	
	@After
	public void finalize() {
		this.secretKey = null;
	}
}
