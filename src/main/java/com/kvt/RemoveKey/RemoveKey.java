package com.kvt.RemoveKey;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;

public class RemoveKey {

	public static void main(String[] args) {

		MongoClientURI uri = new MongoClientURI(
				"mongodb+srv://user:user@cluster0-emxhp.mongodb.net/test?retryWrites=true&w=majority");
		MongoClient mongoClient = new MongoClient(uri);
//		MongoDatabase database = mongoClient.getDatabase("testDatabse");
//		MongoCollection<Document> collection = database.getCollection("collection");

		// get database and collection
		DB db = mongoClient.getDB("testDatabse");
		DBCollection collName = db.getCollection("collName");
		DBCollection collName2 = db.getCollection("collName2");

		String userid = "email@acc.ltd";
		DBObject useremail = new BasicDBObject("email", userid);
		DBObject usermobile = new BasicDBObject("mobile", userid);

		// using $or operator for userid comparision
//		String bodyemail = body.optString("email");
//		String bodymobile = body.optString("mobile");

		BasicDBList or = new BasicDBList();
		or.add(useremail);
		or.add(useremail);

		// Querying collection through (email or mobile)
		DBObject query = new BasicDBObject("$or", or);
		String status = "contractDel";
		if (status.equalsIgnoreCase("offerDelete")) {
			// Remove a field in a single document
			// DBObject query = new BasicDBObject("email", "email@acc.ltd");

			DBObject update = new BasicDBObject();
			update.put("$unset", new BasicDBObject("keyName", ""));

			WriteResult result = collName.update(query, update);
			System.out.println("Field removed successfully from collName2 !!!");

		} else if (status.equalsIgnoreCase("contractDelete")) {
			// Remove a field in a single document
			// DBObject query = new BasicDBObject("email", "email@acc.ltd");

			DBObject upadte = new BasicDBObject();
			upadte.put("$unset", new BasicDBObject("keyName", ""));

			WriteResult result = collName2.update(query, upadte);
			System.out.println("Field removed successfully from collName2 !!!");

		} else {
			System.out.println("Something went wrong");
		}

		// closing mongoClustor connection
		mongoClient.close();

	}

}