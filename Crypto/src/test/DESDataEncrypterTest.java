package test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import model.CipherData;
import model.DESDataEncrypter;

public class DESDataEncrypterTest {

	public static final byte[] input = "Data to be encrypted".getBytes();
	public static final byte[] keyBytes = "Encryption key".getBytes();
	public static final byte[] initializationVectorBytes = "Input vector".getBytes();
	public CipherData cipherData = new CipherData(keyBytes, initializationVectorBytes);
	public static final byte[] expectedEncryptedData = "".getBytes();
	
	@Test
	public void encryptMethodShouldEncryptCorrectly() {
		DESDataEncrypter dde = new DESDataEncrypter();
		byte[] result = dde.encrypt(input, cipherData);
		assertArrayEquals(result, expectedEncryptedData);
	}
}
