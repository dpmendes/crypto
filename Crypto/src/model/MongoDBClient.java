package model;

import com.mongodb.*;
import com.mongodb.client.*;

import java.util.ArrayList;
import java.util.List;

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
	
	public void insertEncryptedDataWithHeader
	(EncryptedDataStructure encryptionDataStructure) {
		Document dbEntry = createDocumentforEncryptedDataEntry(encryptionDataStructure);
		cryptoMongoCollection.insertOne(dbEntry);
	}
	
	private Document createDocumentforEncryptedDataEntry
	(EncryptedDataStructure encryptionDataStructure) {
		Document document = new Document();
		String header = encryptionDataStructure.header;
		document.append("header", header);
		String encryptedData = encryptionDataStructure.encryptedData;
		document.append("data", encryptedData);
		return document;
	}
	
	public void insertLog(LogStructure log) {
		Document dbEntry = createDocumentForLogEntry(log);
		cryptoMongoCollection.insertOne(dbEntry);
	}
	
	private Document createDocumentForLogEntry(LogStructure log) {
		Document document = new Document();
		String time = log.logTime;
		document.append("time", time);
		String username = log.username;
		document.append("username", username);
		String message = log.message;
		document.append("message", message);
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
		FindIterable<Document> iterable = 
				cryptoMongoCollection.find(searchDocument);
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
	
	public List<LogStructure> findLogs() {
		FindIterable<Document> iterable = cryptoMongoCollection.find();

		final List<LogStructure> logStructureList = new ArrayList<LogStructure>();
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        String time = document.getString("time");
		        String username = document.getString("username");
		        String message = document.getString("message");
		        
		        LogStructure logResult = new LogStructure();
		        logResult.logTime = time;
		        logResult.username = username;
		        logResult.message = message;
		        
		        logStructureList.add(logResult);
		    }
		});
		
		return logStructureList;
	}
	
	public void dropCollection() {
		cryptoMongoCollection.drop();
	}
	
	public void deleteEncryptedDataFromHeader(String header) throws DataNotFoundException {
		EncryptedDataStructure eds = findFirstOccurenceByHeader(header);
		Document document = createDocumentforEncryptedDataEntry(eds);
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