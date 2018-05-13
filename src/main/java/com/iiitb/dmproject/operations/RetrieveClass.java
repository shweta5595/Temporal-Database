package com.iiitb.dmproject.operations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sound.midi.Soundbank;

import org.bson.Document;
import org.json.JSONString;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class RetrieveClass {
	
	MongoDatabase database = CreateDatabase.getDatabase_name();
	MongoCollection<Document> collectionname;
	MongoCollection<Document> collectionhistory;
	String coll = "";

	public RetrieveClass(String collection) {
		coll = collection + "_history";
		collectionname = database.getCollection(collection);
		collectionhistory = database.getCollection(coll);
	}

	// simple retrieve
	public Document retrieveStatus(String filter) {
		Document filter_doc = new Document();
		filter_doc = Document.parse(filter);
		List<Document> doc = (List<Document>) collectionname.find(filter_doc).into(new ArrayList<Document>());
		// System.out.println(doc.get(0));
		if (doc.isEmpty())
			return filter_doc;
		else
			return doc.get(0);
	}

	// First
	public Document getFirst(Document filter, String column) {

		Document dt = new Document();
		dt.put("start_time", 1);
		List<Document> doc = (List<Document>) collectionhistory.find(filter).sort(dt).limit(1)
				.into(new ArrayList<Document>());
		Document result_doc = new Document();
		if (doc.isEmpty())
			return result_doc.append("result", "No such userid exist");
		else {
			String column_value = (String) doc.get(0).get(column);
			return result_doc.append(column, column_value).append("start_time", (String) doc.get(0).get("start_time"))
					.append("end_time", (String) doc.get(0).get("end_time"));
		}
	}

	// Last
	public Document getLast(Document filter, String column) {

		Document dt = new Document();
		dt.put("start_time", -1);
		List<Document> doc = (List<Document>) collectionhistory.find(filter).sort(dt).limit(1)
				.into(new ArrayList<Document>());
		// System.out.println("Last Document : "+doc.get(0).get(column));
		Document result_doc = new Document();
		if (doc.isEmpty())
			return result_doc.append("result", "No such userid exist");
		else {
			String column_value = (String) doc.get(0).get(column);
			return result_doc.append(column, column_value).append("start_time", (String) doc.get(0).get("start_time"))
					.append("end_time", (String) doc.get(0).get("end_time"));
		}
	}

	// Previous Scale Value : Month
	public Document getPreviousMonth(Document filter, String column) {
		Document dt = new Document();
		dt.put("start_time", -1);
		Document result_doc = new Document();
		List<Document> doc = (List<Document>) collectionhistory.find(filter).sort(dt).into(new ArrayList<Document>());
		// System.out.println("Previous Value : "+doc.get(0).get("start_time"));
		if (doc.isEmpty())
			return result_doc.append("result", "No such userid exist");
		else {
			DateFormat df = new SimpleDateFormat(CreateDatabase.getDateTimeFormat());
			Date today = Calendar.getInstance().getTime();
			String report = df.format(today);
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH);
			// month += 1;
			int i = 0, k = 0;
			int prevmonth;
			for (Document d : doc) {
				String starttime = (String) d.get("start_time");
				int temp = Integer.parseInt(starttime.substring(5, 7));
				if (temp == (month + 23) % 12 + 1) {
					k = i;
					break;
				}
				i++;
			}
			if (i == doc.size())
				return result_doc.append("result", "No previous month value exist");
			else
				return result_doc.append(column, doc.get(k).get(column))
						.append("start_time", (String) doc.get(k).get("start_time"))
						.append("end_time", (String) doc.get(k).get("end_time"));
		}
	}

	// Get Previous Hour
	public Document getPreviousHour(Document filter, String column) {

		Document dt = new Document();
		dt.put("start_time", -1);
		Document result_doc = new Document();
		List<Document> doc = (List<Document>) collectionhistory.find(filter).sort(dt).into(new ArrayList<Document>());
		// System.out.println("Previous Value : "+doc.get(0).get("start_time"));
		if (doc.isEmpty())
			return result_doc.append("result", "No such userid exist");
		else if(doc.size()==1)
			return result_doc.append("result", "No value before given value");
		else {
			DateFormat df = new SimpleDateFormat(CreateDatabase.getDateTimeFormat());
			Date today = Calendar.getInstance().getTime();
			Calendar cal = Calendar.getInstance();
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int i = 0, k = 0;
			System.out.println("Hour is  "+hour);
			for (Document d : doc) {
				String starttime = (String) d.get("start_time");
				int temp = Integer.parseInt(starttime.substring(11, 13));
				if (temp == (hour + 47) % 24) {
					k = i;
					break;
				}
				i++;
			}
			if (i == doc.size())
				return result_doc.append("result", "No previous hour value exist");
			else
				return result_doc.append(column, doc.get(k).get(column))
						.append("start_time", (String) doc.get(k).get("start_time"))
						.append("end_time", (String) doc.get(k).get("end_time"));
		}
	}

	// Next Value
	public Document getNextVal(Document Val, Document filter, String column) {
		Document dt = new Document();
		dt.put("start_time", 1);
		Document result_doc = new Document();
		List<Document> doc = (List<Document>) collectionhistory.find(filter).sort(dt).into(new ArrayList<Document>());
		if (doc.isEmpty())
			return result_doc.append("result", "No such userid exist");
		else if(doc.size()==1)
			return result_doc.append("result", "No value after given value");
		
		else {
			int i = 0;
			for (Document d : doc) {
				if (Val.get(column).equals(d.get(column))) {
					break;
				}
				i++;
			}
			if (i == doc.size())
				return result_doc.append("result", "No such fieldvalue exist");
			else
				return result_doc.append(column, doc.get(i + 1).get(column))
						.append("start_time", (String) doc.get(i + 1).get("start_time"))
						.append("end_time", (String) doc.get(i + 1).get("end_time"));
		}
	}

	// Previous Value
	public Document getPreviousVal(Document Val, Document filter, String column) {
		Document dt = new Document();
		dt.put("start_time", -1);
		Document result_doc = new Document();
		List<Document> doc = (List<Document>) collectionhistory.find(filter).sort(dt).into(new ArrayList<Document>());
		if (doc.isEmpty())
			return result_doc.append("result", "No such userid exist");
		else {
			int i = 0;
			for (Document d : doc) {
				if (Val.get(column).equals(d.get(column))) {
					break;
				}
				i++;
			}
			if (i == doc.size())
				return result_doc.append("result", "No such fieldvalue exist");
			else
				return result_doc.append(column, doc.get(i + 1).get(column))
						.append("start_time", (String) doc.get(i + 1).get("start_time"))
						.append("end_time", (String) doc.get(i + 1).get("end_time"));
		}
	}

	// Evolution_History (Printing the change history of given column corresponding
	// to a filter
	public List<String> getEvolutionHistory(Document filter, String column) {

		Document dt = new Document();
		dt.put("start_time", 1);

		List<Document> doc = (List<Document>) collectionhistory.find(filter).sort(dt).into(new ArrayList<Document>());
		List<String> al = new ArrayList<>();
		if(doc.isEmpty()) {
			al.add("Userid does not exist.");
			return al;
		}
		else {
		String temp = doc.get(0).get(column).toString();
		
		al.add(doc.get(0).get("start_time").toString());
		for (Document d : doc) {
			boolean flag = temp.equals(d.get(column).toString());
			if (!flag) {
				al.add(d.get("start_time").toString());
				temp = d.get(column).toString();
				}

			}
			return al;
		}
	}

	// returns the date when first time column was updated
	public Document firstEvolution(Document filter, String column) {
		Document dt = new Document();
		dt.put("start_time", 1);
		Document result_doc = new Document();
		List<Document> doc = (List<Document>) collectionhistory.find(filter).sort(dt).into(new ArrayList<Document>());
		if (doc.isEmpty())
			return result_doc.append("result", "No such userid exist");
		else {
			String temp = doc.get(0).get(column).toString();
			String time = "";
			for (Document d : doc) {
				boolean flag = temp.equals(d.get(column).toString());
				if (!flag) {
					temp = d.get(column).toString();
					time = d.get("start_time").toString();
					break;
				}
			}
			// System.out.println("firstEvolution :"+time);
			return result_doc.append("start_time", time);
		}
	}

	// returns the date when last time column was updated
	public Document lastEvolution(Document filter, String column) {
		Document dt = new Document();
		dt.put("start_time", -1);
		Document result_doc = new Document();
		List<Document> doc = (List<Document>) collectionhistory.find(filter).sort(dt).into(new ArrayList<Document>());
		if (doc.isEmpty())
			return result_doc.append("result", "No such userid exist");
		else {
			String temp = doc.get(0).get(column).toString();
			String time = "";
			for (Document d : doc) {
				boolean flag = temp.equals(d.get(column).toString());
				if (!flag) {
					temp = d.get(column).toString();
					time = d.get("start_time").toString();
					break;
				}
			}
			// System.out.println("lastEvolution :"+time);
			return result_doc.append("start_time", time);
		}
	}

	// Evolution va1-val2 col
	public String[] getEvolution(Document filter, String column, Document val1, Document val2) {

		Document dt = new Document();
		dt.put("start_time", 1);
		int flag = 0;
		String[] list = new String[2];
		List<Document> docs = (List<Document>) collectionhistory.find(filter).sort(dt).into(new ArrayList<Document>());
		for (Document d : docs) {
			if (d.get(column).equals(val1.get(column))) {
				flag = 1;
				list[0] = d.get("start_time").toString();
			}
			if (flag == 1) {
				if (d.get(column).equals(val2.get(column))) {
					list[1] = d.get("start_time").toString();
				}
			}
		}
		
		if(list.length == 0)
			list[0] = "No such combination exist.";
		/*
		 * for(Document d: list) { System.out.println("evolution val1 - val2 "+d); }
		 */

		return list;

	}

	// overlap
	public HashMap<Document, Set<Document>> overlap(Document filter1, Document filter2, String starttime,
			String endtime) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat(CreateDatabase.getDateTimeFormat());
		Date start = sdf.parse(starttime);
		Date end = sdf.parse(endtime);

		List<Document> docs1 = (List<Document>) collectionhistory.find(filter1).into(new ArrayList<Document>());
		List<Document> docs2 = (List<Document>) collectionhistory.find(filter2).into(new ArrayList<Document>());
		Set<Document> list1 = new HashSet<Document>();
		Set<Document> list2 = new HashSet<Document>();
		HashMap<Document, Set<Document>> resultMap = new HashMap<>();
		
		for (Document d : docs1) {
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
	
		
		for (Document d : docs2) {
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

				if (end1.after(start1) && start1.before(end2)) {
					al.add(l2);
				}
			}
			
			if (al.size() > 0) {
				resultMap.put(l1, al);
			}
		
		}

	
		return resultMap;

	}

}
