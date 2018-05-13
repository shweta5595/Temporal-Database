package com.iiitb.dmproject.developer;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bson.Document;

import com.iiitb.dmproject.operations.*; 

public class FeaturesDAO {
	
	public void temporaliseDAO(String temp_input) {
		Document input_doc = Document.parse(temp_input);
		String dbname = input_doc.getString("dbname");
		String collname = input_doc.getString("collname");
		String chronon = input_doc.getString("chronon");
		TemporaliseClass temporaliseobj = new TemporaliseClass(dbname, collname, chronon);
		temporaliseobj.makeTemporal();
	}
	
	public void uploadcsvDAO(String temp_input) throws IOException {
		System.out.println("inside dao");
		Document input_doc = Document.parse(temp_input);
		String dbname = input_doc.getString("dbname");
		String collname = input_doc.getString("collname");
		String filepath = input_doc.getString("filepath");
		UploadCSV obj = new UploadCSV(dbname, collname, filepath);
		obj.uploadCSV();
	}
}
