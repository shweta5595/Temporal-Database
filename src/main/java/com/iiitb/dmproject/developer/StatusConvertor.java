package com.iiitb.dmproject.developer;

import org.bson.Document;
import java.util.Date;

public class StatusConvertor {
	
	public static Document converttoDoc (StatusPojo status_pojo) {
		Document status_doc = new Document();
		status_doc.append("userid", status_pojo.getUserid()).append("status", status_pojo.getStatus()).append("location", status_pojo.getLocation()).append("start_time", status_pojo.getStart_time()).append("end_time", status_pojo.getEnd_time());
		
		return status_doc;	
	}
	
	public static StatusPojo converttoPojo (Document status_doc) {
		StatusPojo status_pojo =  new StatusPojo();
		status_pojo.setUserid((String) status_doc.get("userid"));
		status_pojo.setStatus((String) status_doc.get("status"));
		status_pojo.setLocation((String) status_doc.get("location"));
		status_pojo.setStart_time((Date) status_doc.get("start_time"));
		status_pojo.setEnd_time((Date) status_doc.get("end_time"));
		
		return status_pojo;
	}
	

}
