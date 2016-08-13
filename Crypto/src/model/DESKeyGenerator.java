package model;

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DESKeyGenerator {
	public static SecretKey generateRandomDESKey() {
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return keyGenerator.generateKey();
	}
	
	public static SecretKey generateDESKeyFromEightCharactersString
	(String keyString) throws InvalidDESKeyLengthException {
		if(keyString.length() != 8)
			throw new InvalidDESKeyLengthException(keyString.length());
		SecretKeyFactory secretKeyFactory = getDESSecretKeyFactoryInstance();
		KeySpec desKeySpec = createDESKeySpecWithKey(keyString);
		SecretKey secretKey = createSecretKeyUsingFactoryFromKeySpec
								(secretKeyFactory, desKeySpec);
		return secretKey;
	}
	
	private static SecretKeyFactory getDESSecretKeyFactoryInstance() {
		SecretKeyFactory secretKeyFactory = null;
		try {
			secretKeyFactory = SecretKeyFactory.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return secretKeyFactory;
	}

	private static KeySpec createDESKeySpecWithKey(String key) {
		KeySpec desKeySpec = null;
		try {
			desKeySpec = new DESKeySpec(key.getBytes());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return desKeySpec;
	}

	private static SecretKey createSecretKeyUsingFactoryFromKeySpec
	(SecretKeyFactory secretKeyFactory, KeySpec desKeySpec) {
		SecretKey secretKey = null;
		try {
			secretKey = secretKeyFactory.generateSecret(desKeySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return secretKey;
	}
	
}