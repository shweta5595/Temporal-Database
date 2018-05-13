package com.iiitb.dmproject.developer;

import java.io.IOException;
import java.text.ParseException;

import javax.ws.rs.*;

import org.json.JSONException;


@Path("/status")
public class StatusService {

@POST
@Path("/insert")
@Consumes("application/json")
public void addNew_Status(StatusPojo new_status) throws IOException, JSONException{
	StatusDAO dao = new StatusDAO();
	dao.addNew_Status(new_status);
	}

@POST
@Path("/update")
@Consumes("application/json")
public void updateStatus(StatusPojo update_status) throws IOException, JSONException, ParseException {
	StatusDAO dao = new StatusDAO();
	dao.updateStatus(update_status);	
}

@POST
@Path("/retrieve")
@Consumes("application/json")
@Produces("application/json")
public StatusPojo retrieveStatus(String userid) throws IOException, JSONException {
	StatusDAO dao = new StatusDAO();
	System.out.println(userid+ "in service");
	StatusPojo current_status = dao.retrieveStatus(userid);
	return current_status;
}

@POST
@Path("/delete")
@Consumes("text/plain")
public void deleteStatus(String userid) throws IOException, JSONException {
	StatusDAO dao = new StatusDAO();
	dao.deleteStatus(userid);	
}

}
