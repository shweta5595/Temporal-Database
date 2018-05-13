package com.iiitb.dmproject.operations;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bson.Document;
import org.json.JSONException;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javassist.bytecode.Descriptor.Iterator;

public class performJoin {
	MongoDatabase database = CreateDatabase.getDatabase_name();
	MongoCollection<Document> collection1;
	MongoCollection<Document> collection2;
	String colOne,colTwo;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern(CreateDatabase.getDateTimeFormat());
	LocalDateTime now = LocalDateTime.now();
	
	
	public performJoin(String db,String col1,String col2) {
   	 colOne=col1+"_history";
   	 colTwo=col2+"_history";
	collection1 = database.getCollection(colOne);
	collection2 = database.getCollection(colTwo);
	
	
	}
   public HashMap<Document,Set<Document>> join(String fk, String startTime, String endTime ) throws IOException, JSONException, ParseException
   {  
	   System.out.println("inside join");
	   List<Document> docs1=(List<Document>)collection1.find().into(new ArrayList<Document>());
      List<Document> docs2=(List<Document>)collection2.find().into(new ArrayList<Document>());
      Set<String> unq=new HashSet<String>();
      HashMap<Document,Set<Document>> resultMap=new HashMap<>();
      for(Document d:docs1)
	   {  
    	unq.add((String) d.get(fk));
	   }
      
      System.out.println(unq);
     // RetrieveClass rc=new RetrieveClass("temp");
      
      for (String t : unq) {
          Document filter1=new Document();
          Document filter2=new Document();
          filter1.put(fk, t);
          filter2.put(fk, t);
          //check=rc.overlap(filter1, filter2, startTime, endTime);
          
          SimpleDateFormat sdf = new SimpleDateFormat(CreateDatabase.getDateTimeFormat());
  		Date start = sdf.parse(startTime);
  		Date end = sdf.parse(endTime);

  		List<Document> doc1 = (List<Document>)collection1.find(filter1).into(new ArrayList<Document>());
  		List<Document> doc2 = (List<Document>)collection2.find(filter2).into(new ArrayList<Document>());
  		Set<Document> list1 = new HashSet<Document>();
  		Set<Document> list2 = new HashSet<Document>();
  		//HashMap<Document, Set<Document>> resultMap = new HashMap<>();
  		
  		for (Document d : doc1) {
  			String st = d.get("start_time").toString();
  			String et = "";
  			if (d.get("end_time") == null) {
  				et = "2020/12/31 00:00:00.000";
  			} 
  			else {
  				et = d.get("end_time").toString();
  			}
  			Date d1 = sdf.parse(st);
  			Date d2 = sdf.parse(et);
  			if (end.after(d1) && start.before(d2)) {
  				list1.add(d);
  			}
  		}
  	
  		
  		for (Document d : doc2) {
  			String st = d.get("start_time").toString();
  			String et = "";
  			if (d.get("end_time") == null) {
  				et = "2020/12/31 00:00:00.000";
  			} 
  			else {
  				et = d.get("end_time").toString();
  			}
  			Date d1 = sdf.parse(st);
  			Date d2 = sdf.parse(et);
  			if (end.after(d1) && start.before(d2)) {
  				list2.add(d);
  			}
  		}
  		
  		Set<Document> al = new HashSet<Document>();
  		
  		for (Document l1 : list1) {
  			String st1 = l1.get("start_time").toString();
  			String et1 = "";
  			if (l1.get("end_time") == null) {
  				et1 = "2020/12/31 00:00:00.000";
  			}
  			else {
  				et1 = l1.get("end_time").toString();
  			}
  			
  			Date start1 = sdf.parse(st1);
  			Date end1 = sdf.parse(et1);

  			for (Document l2 : list2) {
  				String st2 = l2.get("start_time").toString();
  				String et2 = "";
  				if (l2.get("end_time") == null) {
  					et2 = "2020/12/31 00:00:00.000";
  				} 
  				else {
  					et2 = l2.get("end_time").toString();
  				}
  				Date start2 = sdf.parse(st2);
  				Date end2 = sdf.parse(et2);

  				System.out.println("start1: "+start1+" ,start2: "+start2);
  				System.out.println("end1: "+end1+" ,end2: "+end2);
  				if (start2.after(start1) && end2.before(end1)) {
  					al.add(l2);
  				}
  			}
  			
  			if (al.size() > 0) {
  				resultMap.put(l1, al);
  			}
  		
  		}

          System.out.println(resultMap.size());
          for (HashMap.Entry<Document, Set<Document>> entry :resultMap.entrySet()) {
        	     System.out.println(entry.getKey().toString());
        	     System.out.println(entry.getValue().toString());
          }
      }
      
      return resultMap;
  }
} 
