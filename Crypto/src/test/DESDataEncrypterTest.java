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
	private SecretKey secretKey;
	private SecretKey invalidDESSecretKey;
	private DESDataEncrypter dde;
	
	@Before
	public void initialize() {
		SecretKeyFactory secretKeyFactory = getDESSecretKeyFactoryInstance();
		KeySpec desKeySpec = createDESKeySpecWithKey();
		SecretKey secretKey = alwaysCreateSameSecretKey(secretKeyFactory, desKeySpec);
		
		this.secretKey = secretKey;
		dde = new DESDataEncrypter();
		invalidDESSecretKey = generateAESSecretKey();
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