package com.iiitb.dmproject.operations;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class CreateDatabase {

	static CreateConnection cc = new CreateConnection();
	static MongoClient client = cc.connection();
	static MongoClient chrononclient = cc.connection();
	static String database_name;
	static String chronon;
	
//	public CreateDatabase(String database_name, String chronon) {
//		this.database_name = database_name;
//		this.chronon = chronon;
//	}
	
	public CreateDatabase(String database_name) {
		this.database_name = database_name;
		MongoDatabase chronondb = chrononclient.getDatabase("chrononstore");
		MongoCollection<Document> chrononcoll = chronondb.getCollection("chrononcollection");
		Document chronondoc = new Document();
		chronondoc.append("dbname", database_name);
		List<Document> doc = (List<Document>) chrononcoll.find(chronondoc).into(new ArrayList<Document>());
		for(Document d : doc) {
			this.chronon = d.get("chronon").toString();
		}
	}
	
	static MongoDatabase getDatabase_name() {
		
		return client.getDatabase(database_name);
	}
	
	static String getDateTime() {
		
		DateTimeFormatter dtf;
		LocalDateTime current_date;
		
		switch(chronon) {
		
		case "Minute":
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
			current_date = LocalDateTime.now();
			break;
		
		case "Hour":
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH");
			current_date = LocalDateTime.now();
			break;
		
		case "Day":
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			current_date = LocalDateTime.now();
			break;
			
		default:
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			current_date = LocalDateTime.now();
		}
		
		return dtf.format(current_date);
	} 

	static String getDateTimeFormat() {
		
		String date_format = "";
		
		switch(chronon) {
		
		case "Minute":
			date_format = "yyyy/MM/dd HH:mm";
			break;
		
		case "Hour":
			date_format = "yyyy/MM/dd HH";
			break;
		
		case "Day":
			date_format = "yyyy/MM/dd";
			break;
			
		default:
			date_format = "yyyy/MM/dd HH:mm:ss";
		}
		
		return date_format;
	} 

}
