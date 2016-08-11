package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.security.*;
import model.*;

public class DESDataEncrypterTest {

	private static final String INPUT_PLAIN_DATA = "Data to be encrypted";
	private static final String EXPECTED_ENCRYPTED_DATA = "MkbIBD4wR2jlotpTN8AT14orGqLBFIfR";
	private static final String EIGHT_CHARACTERS_KEY = "abcdefgh";
	private DESDataEncrypter dde;
	
	@Before
	public void initialize() {
		dde = DESDataEncrypter.getInstance();
	}

	@Test
	public void encryptMethodShouldEncryptCorrectly() {
		String encryptionResult = null;
		try {
			encryptionResult = new String
					(dde.encrypt(INPUT_PLAIN_DATA, EIGHT_CHARACTERS_KEY));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			fail("Invalid secret key.");
		}
		assertTrue(encryptionResult.equals(EXPECTED_ENCRYPTED_DATA));
	}
	
	@After
	public void finalize() {
		dde = null;
	}
}