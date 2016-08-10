package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBClient {

	MongoClient mongoClient = null;
	private MongoCollection<Document> cryptoMongoCollection = null;
	
	public MongoDBClient() {
		mongoClient = new MongoClient();
		MongoDatabase mdb = mongoClient.getDatabase("test");
		cryptoMongoCollection = mdb.getCollection("crypto");
	}
	
	public void insertEncryptedDataWithHeader(String encryptedData, String header) {
		Document dbEntry = createDocument(encryptedData, header);
		cryptoMongoCollection.insertOne(dbEntry);
	}
	
	private Document createDocument(String encryptedData, String header) {
		Document document = new Document();
		document.append("encrypted data", encryptedData);
		document.append("header", header);
		return document;
	}

	public void finalize() {
		mongoClient.close();
	}
	
}