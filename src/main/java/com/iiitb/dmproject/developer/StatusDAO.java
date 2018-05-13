package com.iiitb.dmproject.developer;

import java.io.IOException;
import java.text.ParseException;

import org.bson.Document;
import org.json.JSONException;

import com.iiitb.dmproject.operations.*;

public class StatusDAO {
	
	public void addNew_Status(StatusPojo new_status) throws IOException, JSONException {
		DbSpecification dbs = new DbSpecification();
		Document new_status_doc = new Document(); 
	    new_status_doc = StatusConvertor.converttoDoc(new_status);
	    InsertClass insertobj = new InsertClass("StatusCollection", new_status_doc);
		insertobj.insertMany();
		
	}
	
	public void updateStatus(StatusPojo update_status) throws IOException, JSONException, ParseException {
		DbSpecification dbs = new DbSpecification();
		Document update_status_doc = new Document(); 
	    update_status_doc = StatusConvertor.converttoDoc(update_status);
	    Document filter = new Document();
	    filter.put("userid", update_status_doc.getString("userid"));
	    UpdateClass updateobj = new UpdateClass("StatusCollection", filter, update_status_doc);
	    updateobj.update();
	}
	
	public void deleteStatus(String userid) throws IOException, JSONException {
		DbSpecification dbs = new DbSpecification();
	    Document filter = new Document();
	    filter.put("userid", userid);
	    DeleteClass deleteobj = new DeleteClass("StatusCollection", filter);
	    deleteobj.delete();
	}
	
	public StatusPojo retrieveStatus(String userid) {
		System.out.println("in dao");
		DbSpecification dbs = new DbSpecification();
		Document cur_stat = new Document();
		RetrieveClass retrieveobj = new RetrieveClass("StatusCollection");
		cur_stat = retrieveobj.retrieveStatus(userid);
		System.out.println(cur_stat);
		//cur_stat.append("userid", "apoorva").append("status", "chal raha hai").append("location", "yahi eclipse par").append("start_time", null).append("end_time", null);
		StatusPojo cur_stat_pojo = new StatusPojo();
		cur_stat_pojo = StatusConvertor.converttoPojo(cur_stat);
		return cur_stat_pojo;
	}

}
