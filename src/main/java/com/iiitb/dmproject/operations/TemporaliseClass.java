package com.iiitb.dmproject.operations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TemporaliseClass {
	

		CreateConnection cc=new CreateConnection();
		MongoClient client=cc.connection();
		MongoDatabase database;
		MongoCollection<Document> collection;
		MongoCollection<Document> collectionHistory;
		String chronon="";
		MongoClient chrononclient = cc.connection();
		
		public TemporaliseClass(String db,String col, String chronon)
		{
			String col_his=col+"_history";
			database=client.getDatabase(db);
			collection=database.getCollection(col);
			collectionHistory=database.getCollection(col_his);
			MongoDatabase chronondb = chrononclient.getDatabase("chrononstore");
			MongoCollection<Document> chrononcoll = chronondb.getCollection("chrononcollection");
			Document chronondoc = new Document();
			chronondoc.append("dbname", db).append("chronon", chronon);
			chrononcoll.insertOne(chronondoc);
			
			switch(chronon) {
			
			case "Minute":
				this.chronon = "yyyy/MM/dd HH:mm";
				break;
			
			case "Hour":
				this.chronon = "yyyy/MM/dd HH";
				break;
			
			case "Day":
				this.chronon = "yyyy/MM/dd";
				break;
				
			default:
				this.chronon = "yyyy/MM/dd HH:mm:ss";
			}
		}
		
		public void makeTemporal()
		{
			System.out.println("inside function");
			DateTimeFormatter dtf=DateTimeFormatter.ofPattern(chronon);
			LocalDateTime now=LocalDateTime.now();
			List<Document> doc = (List<Document>)collection.find().into(new ArrayList<Document>());
			for(Document d:doc)
			{
				d.append("start_time", dtf.format(now));
			  	d.append("end_time",null);
			  	collectionHistory.insertOne(d);
			}
		}
		
	}


