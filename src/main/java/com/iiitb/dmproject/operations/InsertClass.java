package com.iiitb.dmproject.operations;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.MongoClient; 
import java.util.List;

import javax.print.Doc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.*;
import org.bson.Document;
import org.json.JSONException;

import com.mongodb.WriteResult;


public class InsertClass{
	/*CreateConnection cc = new CreateConnection();
	MongoClient client = cc.connection();*/
	MongoDatabase database = CreateDatabase.getDatabase_name();
	MongoCollection<Document> collectionname;
	MongoCollection<Document> collectionhistory;
	/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
	LocalDateTime now = LocalDateTime.now();*/
	Document docs = new Document();
	
	public InsertClass(String collection,Document new_status_doc){
	this.docs=new_status_doc;
	String coll = collection+"_history";
	//database = client.getDatabase(db);
	collectionname = database.getCollection(collection);
	collectionhistory = database.getCollection(coll);
	
	
	}

public void insertMany() throws IOException, JSONException {

	//docs.put("start_time", dtf.format(now));
	docs.put("start_time", CreateDatabase.getDateTime());
	collectionhistory.insertOne(docs);
	docs.remove("start_time");
	docs.remove("end_time");
	collectionname.insertOne(docs);
	
}
	
   	      

	
}






