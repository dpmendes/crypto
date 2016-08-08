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
		String eightCharacterKey = "12345678";
		SecretKey desFixedSecretKey = null;
		try {
			desFixedSecretKey = DESKeyGenerator.
					generateDESKeyFromEightCharactersString(eightCharacterKey);
		} catch (InvalidKeyLengthException e) {
			e.printStackTrace();
		}
		assertTrue(desFixedSecretKey.toString().equals(fixedKeyDescriptor));
	}
	
}
