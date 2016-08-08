package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.security.*;
import javax.crypto.*;
import model.*;
import controller.InvalidKeyLengthException;

public class DESDataEncrypterTest {

	public static final String input = "Data to be encrypted";
	public static final String expectedEncryptedData = "2FÈ>0Ghå¢ÚS7À×Š+¢Á‡Ñ";
	public static final String eightCharactersKey = "abcdefgh";
	private SecretKey secretKey;
	private SecretKey invalidDESSecretKey;
	private DESDataEncrypter dde;
	
	@Before
	public void initialize() {
		SecretKey secretKey = null;
		try {
			secretKey = DESKeyGenerator.
					generateDESKeyFromEightCharactersString(eightCharactersKey);
		} catch (InvalidKeyLengthException e) {
			e.printStackTrace();
			fail("Key does not have exactly eight characters.");
		}
		
		this.secretKey = secretKey;
		dde = new DESDataEncrypter();
		invalidDESSecretKey = generateAESSecretKey();
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
	public void encryptMethodShouldEncryptCorrectly() {
		String encryptionResult = null;
		try {
			encryptionResult = dde.encrypt(input, secretKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			fail("Invalid secret key.");
		}
		assertTrue(encryptionResult.equals(expectedEncryptedData));
	}
	
	@Test (expected = InvalidKeyException.class)
	public void invalidKeyShouldThrowInvalidKeyException() throws InvalidKeyException {
		dde.encrypt(input, invalidDESSecretKey);
	}
	
	@After
	public void finalize() {
		secretKey = null;
		invalidDESSecretKey = null;
		dde = null;
	}
}