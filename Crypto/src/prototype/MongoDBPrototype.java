package prototype;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.*;

public class MongoDBPrototype {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase mdb = mongoClient.getDatabase("test");
		
		MongoCollection<Document> namesCollection = mdb.getCollection("names");
		Document dbEntry = new Document();
		dbEntry = dbEntry.append("name", "Lucas");
		namesCollection.insertOne(dbEntry);
		
		FindIterable<Document> collectionIterable = namesCollection.find();
		
		collectionIterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				System.out.println("\n=======\n" + document + "\n=======\n");
			}
		});
		
		namesCollection.drop();
		
		mongoClient.close();
	}

}
