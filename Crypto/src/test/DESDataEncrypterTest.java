package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.security.*;
import javax.crypto.*;
import model.*;
import controller.InvalidDESKeyLengthException;

public class DESDataEncrypterTest {

	private static final String INPUT_PLAIN_DATA = "Data to be encrypted";
	private static final String EXPECTED_ENCRYPTED_DATA = "2FÈ>0Ghå¢ÚS7À×Š+¢Á‡Ñ";
	private static final String EIGHT_CHARACTERS_KEY = "abcdefgh";
	private SecretKey secretKey;
	private SecretKey invalidDESSecretKey;
	private DESDataEncrypter dde;
	
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
		dde = DESDataEncrypter.getInstance();
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
			encryptionResult = dde.encrypt(INPUT_PLAIN_DATA, secretKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			fail("Invalid secret key.");
		}
		assertTrue(encryptionResult.equals(EXPECTED_ENCRYPTED_DATA));
	}
	
	@Test (expected = InvalidKeyException.class)
	public void invalidKeyShouldThrowInvalidKeyException() throws InvalidKeyException {
		dde.encrypt(INPUT_PLAIN_DATA, invalidDESSecretKey);
	}
	
	@After
	public void finalize() {
		secretKey = null;
		invalidDESSecretKey = null;
		dde = null;
	}
}