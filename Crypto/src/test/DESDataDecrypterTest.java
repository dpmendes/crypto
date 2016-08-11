package test;

import static org.junit.Assert.*;
import java.security.*;
import org.junit.*;
import model.*;

public class DESDataDecrypterTest {

	private static final String INPUT_ENCRYPTED_DATA = "MkbIBD4wR2jlotpTN8AT14orGqLBFIfR";
	private static final String EXPECTED_PLAIN_DATA = "Data to be encrypted";
	private static final String EIGHT_CHARACTERS_KEY = "abcdefgh";
	private DESDataDecrypter ddd;
	
	@Before
	public void initialize() {
		ddd = DESDataDecrypter.getInstance();
	}
	
	@Test
	public void decryptMethodShouldDecryptCorrectly() {
		String decryptionResult = null;
		try {
			decryptionResult = new String
					(ddd.decrypt(INPUT_ENCRYPTED_DATA, EIGHT_CHARACTERS_KEY));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			fail("Invalid secret key.");
		}
		assertTrue(decryptionResult.equals(EXPECTED_PLAIN_DATA));
	}
	
}
