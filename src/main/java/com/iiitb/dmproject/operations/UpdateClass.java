package com.iiitb.dmproject.operations;



import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor;
import javax.print.attribute.DocAttributeSet;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class UpdateClass {
	/*CreateConnection cc = new CreateConnection();
	MongoClient client = cc.connection();*/
	MongoDatabase database = CreateDatabase.getDatabase_name();
	MongoCollection<Document> collection;
	
	//String url;
	//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
	Document deletefilter;
	Document newfile;
	//String newfile = "";
	
	String col="";
	String filter;
	MongoCollection<Document> collectionhistory;
	String colhist="";
	public UpdateClass(String collection,Document filter,Document newValue){
		colhist = collection+"_history";
		//this.db=db;
		this.col=collection;
		//database = client.getDatabase(db);
		this.collection = database.getCollection(collection);
		this.collectionhistory =database.getCollection(colhist);
		this.newfile = newValue;
		this.deletefilter = filter;
	
	}
		
		
public void update() throws IOException, JSONException, ParseException {
	
		Document dt = new Document();
		dt.append("start_time", -1);
		List<Document> doc_list = (List<Document>)collectionhistory.find(deletefilter).sort(dt).limit(1).into(new ArrayList<Document>());
		Document current_doc = new Document();
		current_doc = doc_list.get(0);
		
		//coalesce implementation
			current_doc.remove("start_time");
			current_doc.remove("end_time");
			current_doc.remove("_id");
			newfile.remove("start_time");
			newfile.remove("end_time");
			System.out.println(newfile);
			if(!newfile.equals(current_doc)) {
				System.out.println("if k andar jaa raha hai");
				newfile.append("start_time", null);
				newfile.append("end_time", null);
				InsertClass ic=new InsertClass(col,newfile);
				collection.deleteOne(deletefilter);
				
				deletefilter = deletefilter.append("end_time", null);
				
				Bson setValue = new Document("end_time",CreateDatabase.getDateTime());
				Bson updateOperation = new Document("$set",setValue);
				collectionhistory.updateOne(deletefilter, updateOperation);

				ic.insertMany();
				
			}
			
		
		
		
		
		}
	
		
}

