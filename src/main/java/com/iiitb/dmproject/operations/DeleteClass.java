package com.iiitb.dmproject.operations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class DeleteClass{
		//CreateConnection cc = new CreateConnection();
		//MongoClient client = cc.connection();
		MongoDatabase database = CreateDatabase.getDatabase_name();;
		MongoCollection<Document> collection;
		MongoCollection<Document> collectionhistory;
		Document filter;		
		String colhist="";
		
		public DeleteClass(String collection, Document filter){
		//database = client.getDatabase(db);
			this.collection = database.getCollection(collection);
			this.filter = filter;
			colhist = collection+"_history";
			this.collectionhistory =database.getCollection(colhist);	
		}
		/*String key="";
		String value="";
		//Bson condition1=new Document("$gt",1);
		//Bson condition2=new Document("$lt",10);
		public void Delete(HashMap<String,String> hm,String delAttribute) {
			for(Map.Entry m:hm.entrySet())
			{
				key=(String) m.getKey();
				value=(String) m.getValue();
			    condition.append(key, value);
			    
			}
			
		
			
			System.out.println("Before");
			Bson filter=new Document(delAttribute,condition);
			System.out.println("After");
			collectionname.deleteMany(filter);
			System.out.println("After after");
		}*/
		
		public void delete() {
			
			collection.deleteOne(filter);
			filter = filter.append("end_time", null);
			Bson setValue = new Document("end_time", CreateDatabase.getDateTime());
			Bson updateOperation = new Document("$set",setValue);
			collectionhistory.updateOne(filter, updateOperation);
		}
		
	

}
