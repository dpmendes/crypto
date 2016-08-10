package model;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBClient {

	MongoClient mongoClient = null;
	private MongoCollection<Document> cryptoMongoCollection = null;
	
	public MongoDBClient() throws InvalidConstructorException {
		throw new InvalidConstructorException
		("Use new MongoDBClient(username) constructor.");
	}
	
	public MongoDBClient(String username) {
		mongoClient = new MongoClient();
		MongoDatabase mdb = mongoClient.getDatabase("test");
		cryptoMongoCollection = mdb.getCollection(username);
	}
	
	public void insertEncryptedDataWithHeader(EncryptionDataStructure encryptionDataStructure) {
		Document dbEntry = createDocument(encryptionDataStructure);
		cryptoMongoCollection.insertOne(dbEntry);
	}
	
	private Document createDocument(EncryptionDataStructure encryptionDataStructure) {
		Document document = new Document();
		String header = encryptionDataStructure.header;
		String encryptedData = encryptionDataStructure.encryptedData;
		document.append(header, encryptedData);
		return document;
	}
	
	public EncryptionDataStructure findFirstOccurence
	(EncryptionDataStructure encryptionDataStructure) throws DataNotFoundException {
		Document searchDocument = createDocument(encryptionDataStructure);
		FindIterable<Document> iterable = cryptoMongoCollection.find(searchDocument);
		
		Document searchResult = iterable.first();
		
		if(searchResult != null)
		{
			String header = encryptionDataStructure.header;
			String encryptedData = searchResult.getString(header);
			EncryptionDataStructure resultStructure = 
					new EncryptionDataStructure(encryptedData, header);
			return resultStructure;			
		}
		else
			throw new DataNotFoundException("There is no data corresponding to " + 
											"EncryptionDataStructure received.");
	}
	
	public void dropCollection() {
		cryptoMongoCollection.drop();
	}

	public void finalize() {
		mongoClient.close();
	}
	
}