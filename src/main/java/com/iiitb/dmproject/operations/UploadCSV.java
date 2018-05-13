package com.iiitb.dmproject.operations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class UploadCSV{
	
	CreateConnection cc = new CreateConnection();
	MongoClient client = cc.connection();
	MongoDatabase dbname ;
	MongoCollection<Document> collectionname;
	String db; // Database Name
	String coll;//Collection
	BufferedReader input_file; 
	
	public UploadCSV(String database, String collection, String input_file) throws FileNotFoundException{
		
		db = database;
		coll = collection;
	    dbname = client.getDatabase(db);
	    System.out.println("Successfully created database !!!");
	    collectionname = dbname.getCollection(coll);
	    this.input_file = new BufferedReader(new FileReader(input_file));
	}

	public void uploadCSV() throws IOException
	{
	    CSV csv = new CSV(true, ',', input_file );
	    List < String > fieldNames = null;
	    if (csv.hasNext()) fieldNames = new ArrayList < > (csv.next());
	    List < Map < String, String >> list = new ArrayList < > ();
	    while (csv.hasNext()) {
	        List < String > x = csv.next();
	        Map < String, String > obj = new LinkedHashMap < > ();
	        for (int i = 0; i < fieldNames.size(); i++) {
	            obj.put(fieldNames.get(i), x.get(i));
	        }
	        list.add(obj);
	      
		    ObjectMapper mapper = new ObjectMapper();
		    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    Document doc=new Document();
		    doc=Document.parse(mapper.writeValueAsString(obj));
		    collectionname.insertOne(doc);
	    }
	    
	}
}