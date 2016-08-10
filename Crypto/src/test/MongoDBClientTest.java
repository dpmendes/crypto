package test;

import static org.junit.Assert.*;
import org.junit.*;
import model.*;

public class MongoDBClientTest {

	private static final String USERNAME = "testuser";
	private static final String ENCRYPTED_DATA = "MkbIBD4wR2jlotpTN8AT14orGqLBFIfR";
	private static final String HEADER = "Test Data";
	private MongoDBClient mdc = null;
	
	@Before
	public void initialize() {
		mdc = new MongoDBClient(USERNAME);
	}
	
	@Test
	public void insertDataWithHeaderShouldSaveToDB() {
		EncryptionDataStructure eds = 
				new EncryptionDataStructure(ENCRYPTED_DATA, HEADER);
		
		mdc.insertEncryptedDataWithHeader(eds);
		EncryptionDataStructure result = null;
		
		try {
			result = mdc.findFirstOccurence(eds);
		} catch (DataNotFoundException e) {
			e.printStackTrace();
			fail("Data not present in database.");
		}
		mdc.dropCollection();
		assertTrue(result.equals(eds));
	}
}