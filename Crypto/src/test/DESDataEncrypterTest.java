package test;

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

import org.junit.*;
import static org.junit.Assert.*;
import model.DESDataEncrypter;

public class DESDataEncrypterTest {

	public static final String input = "Data to be encrypted";
	public static final String expectedEncryptedData = "k±*0õ^Ç/äàK!I¨ßTýD½fq";
	public static final byte[] key = new byte[] {
			(byte)0x10, (byte)0x20, (byte)0x30, (byte)0x40, 
			(byte)0x50, (byte)0x60, (byte)0x70, (byte)0x80
		};
	public SecretKey secretKey = null;
	
	@Before
	public void initialize() {
		SecretKeyFactory secretKeyFactory = getDESSecretKeyFactoryInstance();
		KeySpec desKeySpec = createDESKeySpecWithKey();
		SecretKey secretKey = alwaysCreateSameSecretKey(secretKeyFactory, desKeySpec);

		this.secretKey = secretKey;
	}

	private SecretKeyFactory getDESSecretKeyFactoryInstance() {
		SecretKeyFactory secretKeyFactory = null;
		try {
			secretKeyFactory = SecretKeyFactory.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			fail("Could not find encryption algorithm.");
		}
		return secretKeyFactory;
	}

	private KeySpec createDESKeySpecWithKey() {
		KeySpec desKeySpec = null;
		try {
			desKeySpec = new DESKeySpec(key);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			fail("Invalid key for DESKeySpec creation.");
		}
		return desKeySpec;
	}
	private SecretKey alwaysCreateSameSecretKey(SecretKeyFactory secretKeyFactory, KeySpec desKeySpec) {
		SecretKey secretKey = null;
		try {
			secretKey = secretKeyFactory.generateSecret(desKeySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			fail("Invalid KeySpec.");
		}
		return secretKey;
	}

	@Test
	public void encryptMethodShouldEncryptCorrectly() {
		DESDataEncrypter dde = new DESDataEncrypter();
		String encryptionResult = dde.encrypt(input, this.secretKey);
		assertTrue(encryptionResult.equals(expectedEncryptedData));
	}
	
	@After
	public void finalize() {
		this.secretKey = null;
	}
}