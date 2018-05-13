package com.iiitb.dmproject.developer;

import java.io.IOException;

import javax.ws.rs.*;

@Path("/features")
public class FeaturesService {
	
	@POST
	@Path("/temporalise")
	@Consumes("application/json")
	public void temporaliseCollection(String temp_input) {
		FeaturesDAO dao = new FeaturesDAO();
		dao.temporaliseDAO(temp_input);
	}
	
	@POST
	@Path("/upload")
	@Consumes("application/json")
	public void uploadCSV(String file_input) throws IOException {
		System.out.println("inside service");
		FeaturesDAO dao = new FeaturesDAO();
		dao.uploadcsvDAO(file_input);
	}

}
