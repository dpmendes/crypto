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
		EncryptedDataStructure eds = 
				new EncryptedDataStructure(ENCRYPTED_DATA, HEADER);
		
		mdc.insertEncryptedDataWithHeader(eds);
		EncryptedDataStructure result = null;
		
		try {
			result = mdc.findFirstOccurenceByHeader(HEADER);
		} catch (DataNotFoundException e) {
			e.printStackTrace();
			fail("Data not present in database.");
		}
		assertTrue(result.equals(eds));
	}
	
	@Test
	public void deleteDataWithHeaderShouldRemoveFromDB() {
		try {
			mdc.deleteEncryptedDataFromHeader(HEADER);
		} catch (DataNotFoundException e) {
			e.printStackTrace();
			fail("The required data should have been found since it has " +
			"been inserted in last test.");
		}
		try {
			mdc.findFirstOccurenceByHeader(HEADER);
		} catch (DataNotFoundException e) {
			assertTrue(true);
			mdc.dropCollection();
			return;
		}
		fail("The deleted data can still be found in the database.");
	}	
}