package com.iiitb.dmproject.operations;
import java.util.ArrayList;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class PrimaryKey {
	
		

		public ArrayList<String> primaryKey = new ArrayList<String>();
		CreateConnection cc = new CreateConnection();
		MongoClient client = cc.connection();
		MongoDatabase database;
		MongoCollection<Document> collectionname;
		
		
		public String getPK(String jsonstr) throws JSONException{
			String newpk="";
			//System.out.println(jsonstr);
			JSONObject json = new JSONObject(jsonstr); //jsonStr -
			System.out.println(primaryKey.size());
			for(String i :primaryKey) {
				System.out.println("in :"+i);
				newpk = newpk+ json.get(i);
				System.out.println(json.get(i));
			}
			
			return newpk;
		}
		public void setPK(String db,String collection,String ...pk) {
			
			for(String s: pk) {
				System.out.println(s);
				primaryKey.add(s);
				System.out.println("added");
			}
			
		}
}
