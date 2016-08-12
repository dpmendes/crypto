package model;

import com.mongodb.MongoClient;
import com.mongodb.client.*;
import org.bson.Document;

public class MongoDBClient {

	MongoClient mongoClient = null;
	private MongoCollection<Document> cryptoMongoCollection = null;
	
	public MongoDBClient() throws InvalidConstructorException {
		throw new InvalidConstructorException
		("Use new MongoDBClient(username) constructor.");
	}
	
	public MongoDBClient(String collection) {
		mongoClient = new MongoClient();
		MongoDatabase mdb = mongoClient.getDatabase("test");
		cryptoMongoCollection = mdb.getCollection(collection);
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
	
	public EncryptedDataStructure findFirstOccurenceByHeader
	(String header) throws DataNotFoundException {
		Document searchDocument = createDocumentFromSingleField("header", header);
		Document searchResult = findFirstOccurrenceFromDocument(searchDocument);
		
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
	
	private Document createDocumentFromSingleField(String fieldName, String fieldContent) {
		Document document = new Document();
		document.append(fieldName, fieldContent);
		return document;
	}
	
	private Document findFirstOccurrenceFromDocument(Document searchDocument) {
		FindIterable<Document> iterable = cryptoMongoCollection.find(searchDocument);
		Document searchResult = iterable.first();
		return searchResult;
	}
	
	public UserDataStructure findFirstOccurrenceByUsername
	(String username) throws DataNotFoundException {
		Document searchDocument = createDocumentFromSingleField
				("username", username);
		Document searchResult = findFirstOccurrenceFromDocument(searchDocument);
		
		if(searchResult != null)
		{
			String password = searchResult.getString("password");
			UserDataStructure resultStructure = 
					new UserDataStructure(username, password);
			return resultStructure;			
		}
		else
			throw new DataNotFoundException("There is no data corresponding to " + 
											"the received username.");
	}
	
	public void dropCollection() {
		cryptoMongoCollection.drop();
	}
	
	public void deleteEncryptedDataFromHeader(String header) throws DataNotFoundException {
		EncryptedDataStructure eds = findFirstOccurenceByHeader(header);
		Document document = createDocument(eds);
		cryptoMongoCollection.deleteOne(document);
	}

	public void close() {
		mongoClient.close();
	}
	
	public void finalize() {
		if(mongoClient != null)
			mongoClient.close();
	}
	
}