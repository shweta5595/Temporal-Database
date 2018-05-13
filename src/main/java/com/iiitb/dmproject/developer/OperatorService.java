package com.iiitb.dmproject.developer;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.*;

import org.bson.Document;
import org.json.JSONException;

@Path("/operation")
public class OperatorService {

	@POST
	@Path("/first")
	@Consumes("application/json")
	@Produces("application/json")
	public String getFirst(String input) throws IOException, JSONException {
		OperatorDAO dao = new OperatorDAO();
		String result = dao.getFirstDAO(input);
		return result;
	}
	
	@POST
	@Path("/last")
	@Consumes("application/json")
	@Produces("application/json")
	public String getLast(String input) throws IOException, JSONException {
		OperatorDAO dao = new OperatorDAO();
		String result = dao.getLastDAO(input);
		return result;
	}
	
	@POST
	@Path("/previousmonth")
	@Consumes("application/json")
	@Produces("application/json")
	public String getPrevMonth(String input) throws IOException, JSONException {
		OperatorDAO dao = new OperatorDAO();
		String result = dao.getPrevMonthDAO(input);
		return result;
	}
	
	@POST
	@Path("/previoushour")
	@Consumes("application/json")
	@Produces("application/json")
	public String getPrevHour(String input) throws IOException, JSONException {
		OperatorDAO dao = new OperatorDAO();
		String result = dao.getPrevHourDAO(input);
		return result;
	}
	
	@POST
	@Path("/firstevolution")
	@Consumes("application/json")
	@Produces("application/json")
	public String getFirstEv(String input) throws IOException, JSONException {
		OperatorDAO dao = new OperatorDAO();
		String result = dao.getFirstEvDAO(input);
		return result;
	}
	
	@POST
	@Path("/lastevolution")
	@Consumes("application/json")
	@Produces("application/json")
	public String getLastEv(String input) throws IOException, JSONException {
		OperatorDAO dao = new OperatorDAO();
		String result = dao.getLastEvDAO(input);
		return result;
	}
	
	@POST
	@Path("/prevalue")
	@Consumes("application/json")
	@Produces("application/json")
	public String getPrevVal(String input) throws IOException, JSONException {
		OperatorDAO dao = new OperatorDAO();
		String result = dao.getPrevValDAO(input);
		return result;
	}
	
	@POST
	@Path("/nextvalue")
	@Consumes("application/json")
	@Produces("application/json")
	public String getNextVal(String input) throws IOException, JSONException {
		OperatorDAO dao = new OperatorDAO();
		String result = dao.getNextValDAO(input);
		return result;
	}
	
	@POST
	@Path("/evolhistory")
	@Consumes("application/json")
	@Produces("application/json")
	public List<String> getEvolHistory(String input) throws IOException, JSONException {
		OperatorDAO dao = new OperatorDAO();
		List<String> result = dao.getEvolHistoryDAO(input);
		return result;
	}
	
	@POST
	@Path("/evolution")
	@Consumes("application/json")
	@Produces("application/json")
	public String[] getEvolution(String input) throws IOException, JSONException {
		OperatorDAO dao = new OperatorDAO();
		String[] result = dao.getEvolDAO(input);
		return result;
	}
	
	@POST
	@Path("/overlap")
	@Consumes("application/json")
	@Produces("text/plain")
	public String getOverlap(String input) throws IOException, JSONException, ParseException {
		OperatorDAO dao = new OperatorDAO();
		String result = dao.getOverlapDAO(input);
		return result;
	}
	
	@POST
	@Path("/join")
	@Consumes("application/json")
	@Produces("text/plain")
	public String getJoin(String input) throws IOException, JSONException, ParseException {
		System.out.println("inside service");
		OperatorDAO dao = new OperatorDAO();
		String result = dao.getJoinDAO(input);
		return result;
	}
	
}
