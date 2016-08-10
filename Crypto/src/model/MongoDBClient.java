package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBClient {

	private MongoClient mongoClient = null;
	private MongoDatabase mdb = null;
	private MongoCollection<Document> cryptoMongoCollection = null;
	
	public MongoDBClient() {
		mongoClient = new MongoClient();
		mdb = mongoClient.getDatabase("test");
		cryptoMongoCollection = mdb.getCollection("crypto");
	}
	
	public void insertEncryptedDataWithHeader(byte[] encryptedData, String header) {
		
	}
	
}