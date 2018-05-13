package com.iiitb.dmproject.operations;

import com.mongodb.MongoClient;
//This will be private to our package
//To establish a connection 
public class CreateConnection {
	MongoClient client ;
	public MongoClient connection(){
		client = new MongoClient( "localhost" , 27017 );
		return client;
	}

}
