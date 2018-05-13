package com.iiitb.dmproject.operations;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;


public class MyMapReduce {
	CreateConnection cc = new CreateConnection();
	MongoClient client = cc.connection();
	//MongoDatabase database;
	DB database;
	DBCollection collectionname;
	DBCollection collectionhistory;
	//MongoCollection<Document> collectionname;
	//MongoCollection<Document> collectionhistory;
	String coll="";
	
	public MyMapReduce(String db, String collection){
		coll = collection+"_history";
		this.database = client.getDB(db);
		collectionname = database.getCollection(collection);
		collectionhistory = database.getCollection(coll);
		
		
	}

	

	
	//First
	
	public void getFirstMP(String filter,String column) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonify = (JSONObject) parser.parse(filter);
		String keystr="";
		Object keyvalue="";
		for(Object key : jsonify.keySet()) {
			keystr = key.toString();
			keyvalue = jsonify.get(keystr);
			
			System.out.println("key "+keystr+" value "+keyvalue);
		}
		BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put(keystr,keyvalue);
	          
        String map = "function(){emit({"+keystr+":"+"this."+keystr+"},{start_time:this.start_time,"+column+":this."+column+"});}";
		   String reduce = "function(key, value) {" + "var result ={},temp=0,index=0;"
	                + "result.start_time=value[0].start_time;"
	                + "result."+column+"=value[0]."+column+";"
	                + "for(i=1;i<value.length;i++)" + "{"
			+ "temp=value[i].start_time;"
			+ "if(result[\"start_time\"]>temp)"+"{"
			+ "result.start_time=value[i].start_time;"
			+ "result."+column+"=value[i]."+column+";"
			+ "index=i;"
			+ "}"
			+ "}"
			+ "result.start_time=value[index].start_time;"
			+ "return result;"
			+ "};";
		   /*String map = "function(){emit({sensor:this.sensor},{start_time:this.start_time,temp:this.temperature});}";
		   String reduce = "function(key, value) {" + "var result ={},temp=0,index=0;"
	                + "result.start_time=value[0].start_time;"
	                + "result.temp=value[0].temp;"
	                + "for(i=1;i<value.length;i++)" + "{"
			+ "temp=value[i].start_time;"
			+ "if(result[\"start_time\"]<temp)"+"{"
			+ "result.start_time=value[i].start_time;"
			+ "result.temp=value[i].temp;"
			+ "index=i;"
			+ "}"
			+ "}"
			+ "result.start_time=value[index].start_time;"
			+ "return result;"
			+ "};";
		   */
		   MapReduceCommand cmd = new MapReduceCommand(collectionhistory,map,reduce,null,MapReduceCommand.OutputType.INLINE,whereQuery);
		   MapReduceOutput out = collectionhistory.mapReduce(cmd);
		   
		   for(DBObject o : out.results()) {
			   System.out.println("First "+o.toString());
		   }
	}
	public void getLastMP(String filter,String column) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonify = (JSONObject) parser.parse(filter);
		String keystr="";
		Object keyvalue="";
		for(Object key : jsonify.keySet()) {
			keystr = key.toString();
			keyvalue = jsonify.get(keystr);
			
			//System.out.println("key "+keystr+" value "+keyvalue);
		}
		BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put(keystr,keyvalue);
	          
        String map = "function(){emit({"+keystr+":"+"this."+keystr+"},{start_time:this.start_time,"+column+":this."+column+"});}";
		   String reduce = "function(key, value) {" + "var result ={},temp=0,index=0;"
	                + "result.start_time=value[0].start_time;"
	                + "result."+column+"=value[0]."+column+";"
	                + "for(i=1;i<value.length;i++)" + "{"
			+ "temp=value[i].start_time;"
			+ "if(result[\"start_time\"]<temp)"+"{"
			+ "result.start_time=value[i].start_time;"
			+ "result."+column+"=value[i]."+column+";"
			+ "index=i;"
			+ "}"
			+ "}"
			+ "result.start_time=value[index].start_time;"
			+ "return result;"
			+ "};";
		   
		   MapReduceCommand cmd = new MapReduceCommand(collectionhistory,map,reduce,null,MapReduceCommand.OutputType.INLINE,whereQuery);
		   MapReduceOutput out = collectionhistory.mapReduce(cmd);
		   
		   for(DBObject o : out.results()) {
			   System.out.println("Last "+o.get("value"));
		   }
	}
	//Evolution_History (Printing the change history of given column corresponding to a filter
			public void getEvolutionHistoryMR(String filter,String column) throws ParseException{
				JSONParser parser = new JSONParser();
				JSONObject jsonify = (JSONObject) parser.parse(filter);
				String keystr="";
				Object keyvalue="";
				for(Object key : jsonify.keySet()) {
					keystr = key.toString();
					keyvalue = jsonify.get(keystr);
					
					//System.out.println("key "+keystr+" value "+keyvalue);
				}
				BasicDBObject whereQuery = new BasicDBObject();
		        whereQuery.put(keystr,keyvalue);
		        String map = "function(){emit({"+keystr+":"+"this."+keystr+"},{start_time:this.start_time,"+column+":this."+column+"});}";   
		       /* String reduce = "function(key, value) {" + "var result=[],temp=0,index=0,obj={};"
		                + "obj.start_time=value[0].start_time;"
		                + "obj."+column+"=value[0]."+column+";"
		                + "result.push(obj);"
		                + "for(i=1;i<value.length;i++)" + "{"
				+ "temp=value[i]."+column+";"
				+ "if(obj["+column+"]!=temp)"+"{"
				+ "obj.start_time=value[i].start_time;"
				+ "obj."+column+"=value[i]."+column+";"
				+ "result.push(obj);"
				+ "}"
				+ "}"
				+ "return result;"
				+ "};";
				   */
		        
		        String reduce = "function(key, value) {" + "var result=[],temp=0,index=0,j=0,obj={};"
		                + "obj.temperature=value[0].temperature;"
		                + "for(i=1;i<value.length;i++)" + "{"
				+ "temp=value[i].temperature;"
				+ "if(obj.temperature!=temp)"+"{"
				+ "result[j]=value[i].start_time;"
				+ "obj.temperature=value[i].temperature;"
				+ "j++;"
				+ "}"
				+ "}"
				+ "return result[0];"
				+ "};";
				   
				   MapReduceCommand cmd = new MapReduceCommand(collectionhistory,map,reduce,null,MapReduceCommand.OutputType.INLINE,whereQuery);
				   MapReduceOutput out = collectionhistory.mapReduce(cmd);
				   
				   for(DBObject o : out.results()) {
					   System.out.println("Evolution History "+o.toString());
				   }		
		
		
			}
			
			public void getPreviousValue(String filter,String value) throws ParseException
			{
				JSONParser parser = new JSONParser();
				JSONObject jsonify_filter = (JSONObject) parser.parse(filter);
				String filter_key="";
				Object filter_value="";
				for(Object key : jsonify_filter.keySet()) {
					filter_key = key.toString();
					filter_value = jsonify_filter.get(filter_key);
				}
					System.out.println("fkey "+filter_key+" fvalue "+filter_value);
					
					
					JSONParser parser1 = new JSONParser();
					JSONObject jsonify_value = (JSONObject) parser1.parse(value);
					String value_key="";
					Object value_value="";
					for(Object key1 : jsonify_value.keySet()) {
						value_key = key1.toString();
						value_value = jsonify_value.get(value_key);
				    }
					System.out.println("valkey "+value_key+" valvalue "+value_value);
					BasicDBObject whereQuery = new BasicDBObject();
			        whereQuery.put(value_key,value_value);
				
			        String map = "function(){emit({"+filter_key+":"+"this."+filter_key+"},{start_time:this.start_time,"+value_key+":this."+value_key+",end_time:this.end_time});}";
			        String reduce="function(key, value) {" + "var result={},temp=0,i=0,res=[];"
			        		+ "value.sort(function(a, b){\n"
			        		+  "var dateA=new Date(a.start_time), dateB=new Date(b.start_time);"
			        	    +"return dateB-dateA;" //sort by date ascending 
			        		+ "});"
			        		+ "res[0]=value[0].start_time;"
			        		+ "res[1]=value[1].start_time;"
			                /*+ "result.start_time=value[0].start_time;"
			                + "result."+value_key+"=value[0]."+value_key+";"
		                    + "result.end_time=value[0].end_time;"

			                + "for(i=0;i<value.length;i++)" + "{"
			                + "temp=value[i]."+value_key+";"
			                + "if(temp=="+value_value+"&& value[i].end_time==null)"+"{"
		                        +"result.end_time=value[i].start_time ;"
		                        +"}"
		                        +"}"
		                        
		                        +"for(i=0;i<value.length;i++)"+ "{"
		                        +"if(result.end_time>value[i].start_time)"+"{"
		                        +"result.start_time=value[i].start_time;"	
		                        +"result."+value_key+"=value[i]."+value_key+";"
		                        +"}"
		                        +"}"
*/
		               		+ "return res[1];"
					+ "};";
			        
			        MapReduceCommand cmd = new MapReduceCommand(collectionhistory,map,reduce,null,MapReduceCommand.OutputType.INLINE,whereQuery);
					   MapReduceOutput out = collectionhistory.mapReduce(cmd);
					   
					   for(DBObject o : out.results()) {
						   System.out.println("previous  "+o.toString());
					   }
			        

			}
			}

	
			
