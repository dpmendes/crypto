package test;

import static org.junit.Assert.*;

import java.security.InvalidKeyException;

import javax.crypto.SecretKey;

import org.junit.*;

import controller.InvalidDESKeyLengthException;
import model.DESDataDecrypter;
import model.DESKeyGenerator;

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
		ddd = DESDataDecrypter.getInstance();
	}
	
	@Test
	public void decryptMethodShouldDecryptCorrectly() {
		String decryptionResult = null;
		try {
			decryptionResult = ddd.decrypt(INPUT_ENCRYPTED_DATA, secretKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			fail("Invalid secret key.");
		}
		assertTrue(decryptionResult.equals(EXPECTED_PLAIN_DATA));
	}
}
