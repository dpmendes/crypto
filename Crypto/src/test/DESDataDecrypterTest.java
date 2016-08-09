package test;

import static org.junit.Assert.*;
import java.security.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.junit.*;
import controller.InvalidDESKeyLengthException;
import model.*;

public class DESDataDecrypterTest {

	private static final String INPUT_ENCRYPTED_DATA = "2FÈ>0Ghå¢ÚS7À×Š+¢Á‡Ñ";
	private static final String EXPECTED_PLAIN_DATA = "Data to be encrypted";
	public static final String EIGHT_CHARACTERS_KEY = "abcdefgh";
	private DESDataDecrypter ddd;
	private SecretKey secretKey;
	private SecretKey invalidDESSecretKey;
	
	@Before
	public void initialize() {
		SecretKey secretKey = null;
		try {
			secretKey = DESKeyGenerator.
					generateDESKeyFromEightCharactersString(EIGHT_CHARACTERS_KEY);
		} catch (InvalidDESKeyLengthException e) {
			e.printStackTrace();
			fail("Key does not have exactly eight characters.");
		}
		
		this.secretKey = secretKey;
		invalidDESSecretKey = generateAESSecretKey();
		ddd = DESDataDecrypter.getInstance();
	}
	
	private SecretKey generateAESSecretKey() {
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			fail("There is no AES Algorithm.");
		}
		
		return keyGenerator.generateKey();
	}
	
	@Test
	public void decryptMethodShouldDecryptCorrectly() {
		String decryptionResult = null;
		try {
			decryptionResult = new String
					(ddd.decrypt(INPUT_ENCRYPTED_DATA.getBytes(), secretKey));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			fail("Invalid secret key.");
		}
		assertTrue(decryptionResult.equals(EXPECTED_PLAIN_DATA));
	}
	
	@Test (expected = InvalidKeyException.class)
	public void invalidKeyShouldThrowInvalidKeyException() throws InvalidKeyException {
		ddd.decrypt(INPUT_ENCRYPTED_DATA.getBytes(), invalidDESSecretKey);
	}
	
}
