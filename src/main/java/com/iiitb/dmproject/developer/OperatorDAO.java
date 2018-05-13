package com.iiitb.dmproject.developer;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.Document;
import org.json.JSONException;

import com.iiitb.dmproject.operations.*;
import com.mongodb.util.JSON;



public class OperatorDAO {
	
	RetrieveClass obj;
	
	OperatorDAO(){
		DbSpecification dbs = new DbSpecification();
		obj = new RetrieveClass("StatusCollection");
	}
	
	private Document stringtoDoc(String input) {
		Document input_doc = new Document();
		input_doc = Document.parse(input);
		return input_doc;
	}
	
	private String fetchColumn(Document input_doc) {
		String column = (String) input_doc.get("fieldname");
		input_doc.remove("fieldname");
		return column;
	}
	
	public String getFirstDAO(String input) {
		Document input_doc = stringtoDoc(input);
		String column = fetchColumn(input_doc);
		Document output_doc = obj.getFirst(input_doc, column);
		String output = output_doc.toJson();
		return output;
	}
	
	public String getLastDAO(String input) {
		Document input_doc = stringtoDoc(input);
		String column = fetchColumn(input_doc);
		Document output_doc = obj.getLast(input_doc, column);
		String output = output_doc.toJson();
		return output;
	}

	public String getPrevMonthDAO(String input) {
		Document input_doc = stringtoDoc(input);
		String column = fetchColumn(input_doc);
		Document output_doc = obj.getPreviousMonth(input_doc, column);
		String output = output_doc.toJson();
		return output;
	}

	public String getPrevHourDAO(String input) {
		Document input_doc = stringtoDoc(input);
		String column = fetchColumn(input_doc);
		Document output_doc = obj.getPreviousHour(input_doc, column);
		String output = output_doc.toJson();
		return output;
	}

	public String getFirstEvDAO(String input) {
		Document input_doc = stringtoDoc(input);
		String column = fetchColumn(input_doc);
		Document output_doc = obj.firstEvolution(input_doc, column);
		String output = output_doc.toJson();
		return output;
	}

	public String getLastEvDAO(String input) {
		Document input_doc = stringtoDoc(input);
		String column = fetchColumn(input_doc);
		Document output_doc = obj.lastEvolution(input_doc, column);
		String output = output_doc.toJson();
		return output;
	}

	public String getPrevValDAO(String input) {
		Document input_doc = stringtoDoc(input);
		String column = fetchColumn(input_doc);
		Document value_doc = new Document();
		value_doc.append(column, input_doc.getString("fieldvalue1"));
		input_doc.remove("fieldvalue1");
		Document output_doc = obj.getPreviousVal(value_doc, input_doc, column);
		String output = output_doc.toJson();
		return output;
	}

	public String getNextValDAO(String input) {
		Document input_doc = stringtoDoc(input);
		String column = fetchColumn(input_doc);
		Document value_doc = new Document();
		value_doc.append(column, input_doc.getString("fieldvalue1"));
		input_doc.remove("fieldvalue1");
		Document output_doc = obj.getNextVal(value_doc, input_doc, column);
		String output = output_doc.toJson();
		return output;
	}
	
	public List<String> getEvolHistoryDAO(String input) {
		Document input_doc = stringtoDoc(input);
		String column = fetchColumn(input_doc);
		List<String> output_doc = obj.getEvolutionHistory(input_doc, column);
		return output_doc;
	}
	
	public String[] getEvolDAO(String input) {
		Document input_doc = stringtoDoc(input);
		String column = fetchColumn(input_doc);
		Document val1_doc = new Document();
		val1_doc.append(column, input_doc.get("fieldvalue1").toString());
		input_doc.remove("fieldvalue1");
		Document val2_doc = new Document();
		val2_doc.append(column, input_doc.get("fieldvalue2").toString());
		input_doc.remove("fieldvalue2");
		String[] output_doc = obj.getEvolution(input_doc, column, val1_doc, val2_doc);
		return output_doc;
	}
	
	public String getOverlapDAO(String input) throws ParseException{
		Document input1_doc = stringtoDoc(input);
		String start_time = input1_doc.get("start_time").toString();
		input1_doc.remove("start_time");
		String end_time = input1_doc.get("end_time").toString();
		input1_doc.remove("end_time");
		Document input2_doc = new Document();
		input1_doc.append("userid", input1_doc.get("userid1").toString());
		input1_doc.remove("userid1");
		input2_doc.append("userid", input1_doc.get("userid2").toString());
		input1_doc.remove("userid2");
		HashMap<Document, Set<Document>> output = obj.overlap(input1_doc, input2_doc, start_time, end_time);
		String data = "";
		int i=1;
		for(Entry<Document, Set<Document>> m : output.entrySet()){  
			Document key_doc = m.getKey();
			key_doc.remove("_id");
			String key_str = key_doc.toJson();
			data = data + i + ") " + key_str + "\n OVERLAPS WITH \n";
			i++;
			for(Document d:m.getValue())
			{
				d.remove("_id");
				String value_str = d.toJson();
				data = data + value_str + "\n";
			}
			data = data + "\n"; 
		}
		return data;
	}
	
	public String getJoinDAO(String input) throws ParseException, IOException, JSONException{
		System.out.println("inside dao");
		Document input1_doc = stringtoDoc(input);
		String start_time = input1_doc.get("start_time").toString();
		input1_doc.remove("start_time");
		String end_time = input1_doc.get("end_time").toString();
		input1_doc.remove("end_time");
		String Coll1Name = input1_doc.get("coll1").toString();
		String Coll2Name = input1_doc.get("coll2").toString();
		
		String foreignkey = input1_doc.get("fieldname").toString();
		performJoin obj = new performJoin("TempDatabase", Coll1Name, Coll2Name);
		HashMap<Document, Set<Document>> output = obj.join(foreignkey, start_time, end_time);
		String data = "";
		int i=1;
		for(Entry<Document, Set<Document>> m : output.entrySet()){  
			Document key_doc = m.getKey();
			key_doc.remove("_id");
			String key_str = key_doc.toJson();
			data = data + i + ") " + key_str + "\n JOINS WITH \n";
			i++;
			for(Document d:m.getValue())
			{
				d.remove("_id");
				String value_str = d.toJson();
				data = data + value_str + "\n";
			}
			data = data + "\n"; 
		}
		return data;
	}

}
