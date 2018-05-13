package com.iiitb.dmproject.developer;

import com.iiitb.dmproject.operations.*;

public class DbSpecification {
	
	static String database_name;
	//static String chronon;
	
//	public DbSpecification() {
//		this.database_name = "TempDatabase";
//		this.chronon = "minute";
//		CreateDatabase cd = new CreateDatabase(database_name, chronon);
//	}
	
	public DbSpecification() {
		this.database_name = "TempDatabase";
		CreateDatabase cd = new CreateDatabase(database_name);
	}
	
	
	
}
