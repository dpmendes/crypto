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
	
	public void insertEncryptedDataWithHeader(EncryptedDataStructure encryptionDataStructure) {
		Document dbEntry = createDocument(encryptionDataStructure);
		cryptoMongoCollection.insertOne(dbEntry);
	}
	
	private Document createDocument(EncryptedDataStructure encryptionDataStructure) {
		Document document = new Document();
		String header = encryptionDataStructure.header;
		document.append("header", header);
		String encryptedData = encryptionDataStructure.encryptedData;
		document.append("data", encryptedData);
		return document;
	}
	
	private Document createDocumentFromHeader(String header) {
		Document document = new Document();
		document.append("header", header);
		return document;
	}
	
	public EncryptedDataStructure findFirstOccurenceByHeader
	(String header) throws DataNotFoundException {
		Document searchDocument = createDocumentFromHeader(header);
		FindIterable<Document> iterable = cryptoMongoCollection.find(searchDocument);
		
		Document searchResult = iterable.first();
		
		if(searchResult != null)
		{
			String encryptedData = searchResult.getString("data");
			EncryptedDataStructure resultStructure = 
					new EncryptedDataStructure(encryptedData, header);
			return resultStructure;			
		}
		else
			throw new DataNotFoundException("There is no data corresponding to " + 
											"the received header.");
	}
	
	public void dropCollection() {
		cryptoMongoCollection.drop();
	}
	
	public void deleteEncryptedDataFromHeader(String header) throws DataNotFoundException {
		EncryptedDataStructure eds = findFirstOccurenceByHeader(header);
		Document document = createDocument(eds);
		cryptoMongoCollection.deleteOne(document);
	}

	public void finalize() {
		mongoClient.close();
	}
	
}