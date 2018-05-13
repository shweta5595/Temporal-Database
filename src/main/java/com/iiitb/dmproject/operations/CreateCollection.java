package com.iiitb.dmproject.operations;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CreateCollection {
	CreateConnection cc = new CreateConnection();
	MongoClient client = cc.connection();
	MongoDatabase dbname ;
	MongoCollection<Document> collectionname;
	String db; // Database Name
	ArrayList<String> coll;//Collection
	
		public CreateCollection(String database,ArrayList<String> collection){
			
			db = database;
			coll = collection;
		    
		    dbname = client.getDatabase(db);
		    System.out.println("Successfully created database !!!");
		    for(int i=0;i<collection.size();i++) {
		    collectionname = dbname.getCollection(collection.get(i));
		    
		    }
		    System.out.println("Successfully created collections !!!");
		
			
	}
		
}
