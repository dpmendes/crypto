package test;

import static org.junit.Assert.*;
import javax.crypto.*;
import org.junit.*;

import controller.InvalidKeyLengthException;
import model.DESKeyGenerator;

public class DESKeyGeneratorTest {
	
	private static String fixedKeyDescriptor = "com.sun.crypto.provider.DESKey@181f5";

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
		} catch (InvalidKeyLengthException e) {
			e.printStackTrace();
		}
		assertTrue(desFixedSecretKey.toString().equals(fixedKeyDescriptor));
	}
	
	@Test (expected = InvalidKeyLengthException.class)
	public void generateDESKeyFromSevenCharactersKeyShouldThrowException()
												throws InvalidKeyLengthException {
		String sevenCharactersKey = "1234567";
		@SuppressWarnings("unused")
		SecretKey desFixedSecretKey = DESKeyGenerator.
					generateDESKeyFromEightCharactersString(sevenCharactersKey);
	}
	
	@Test (expected = InvalidKeyLengthException.class)
	public void generateDESKeyFromNineCharactersKeyShouldThrowException()
												throws InvalidKeyLengthException {
		String nineCharactersKey = "123456789";
		@SuppressWarnings("unused")
		SecretKey desFixedSecretKey = DESKeyGenerator.
					generateDESKeyFromEightCharactersString(nineCharactersKey);
	}
	
}
