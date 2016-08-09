package prototype;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBPrototype {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase mdb = mongoClient.getDatabase("test");
		
		Document dbEntry = new Document();
		dbEntry = dbEntry.append("name", "Lucas");
		mdb.getCollection("names").insertOne(dbEntry);
		
		mongoClient.close();
	}

}
