package test;

import static org.junit.Assert.*;
import javax.crypto.*;
import org.junit.*;

import model.DESKeyGenerator;
import model.InvalidDESKeyLengthException;

public class DESKeyGeneratorTest {
	
	private static final String FIXED_KEY_DESCRIPTOR 
	= "com.sun.crypto.provider.DESKey@181f5";

	@Test
	public void generateRandomDESKeyShouldReturnDESKey() {
		SecretKey desSecretKey = DESKeyGenerator.generateRandomDESKey();
		assertTrue(desSecretKey.getAlgorithm().equals("DES"));
	}
	
	@Test
	public void generateRandomDESKeyShouldNotReturnNullEncodedKey() {
		SecretKey desSecretKey = DESKeyGenerator.generateRandomDESKey();
		assertFalse(desSecretKey.getEncoded() == null);
	}
	
	@Test
	public void generateDESKeyFromEightCharactersStringShouldReturnSameKey() {
		String eightCharactersKey = "12345678";
		SecretKey desFixedSecretKey = null;
		try {
			desFixedSecretKey = DESKeyGenerator.
					generateDESKeyFromEightCharactersString(eightCharactersKey);
		} catch (InvalidDESKeyLengthException e) {
			e.printStackTrace();
		}
		assertTrue(desFixedSecretKey.toString().equals(FIXED_KEY_DESCRIPTOR));
	}
	
	@Test (expected = InvalidDESKeyLengthException.class)
	public void generateDESKeyFromSevenCharactersKeyShouldThrowException()
												throws InvalidDESKeyLengthException {
		String sevenCharactersKey = "1234567";
		@SuppressWarnings("unused")
		SecretKey desFixedSecretKey = DESKeyGenerator.
					generateDESKeyFromEightCharactersString(sevenCharactersKey);
	}
	
	@Test (expected = InvalidDESKeyLengthException.class)
	public void generateDESKeyFromNineCharactersKeyShouldThrowException()
												throws InvalidDESKeyLengthException {
		String nineCharactersKey = "123456789";
		@SuppressWarnings("unused")
		SecretKey desFixedSecretKey = DESKeyGenerator.
					generateDESKeyFromEightCharactersString(nineCharactersKey);
	}
	
}
