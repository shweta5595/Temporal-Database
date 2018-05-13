<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
    <%@include file="bootstrap-3.3.7-dist/css/bootstrap.css" %>
    <%@include file="bootstrap-3.3.7-dist/css/bootstrap-theme.css" %>
</style>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="bootstrap-3.3.7-dist/js/jquery-3.2.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<title>Temporal Operators</title>
</head>
<body>

<div class=container>
	
	<!-- Navigation Bar -->
		<div class="row">
			<nav class="navbar navbar-default">
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      				<ul class="nav navbar-nav">
        				<li><a href="index.jsp">Home <span class="sr-only">(current)</span></a></li>
        				<li><a href="operators.jsp">Temporal Operations</a></li>
        				<li class="active"><a href="#">Other Features</a></li>
        			</ul>
        		</div>
			</nav>
		</div>
		
		<div class="row">
		
			<div class="col-sm-3">
			    <div class="row">
				    <h3><span class="label label-default">Operators</span></h3>
				    <ul class="nav nav-pills nav-stacked">
						  <li role="presentation" onclick="show_temporalise()"><a>Temporalise Existing Database</a></li>
						  <li role="presentation" onclick="show_upload()"><a>Upload CSV file</a></li>
					</ul>
			    </div>
			</div>
			
			<div class="col-sm-9">
			
				<div class="row" id="temporalise">
					<div class="form-group">
					    <label for="dbname">Database Name</label>
					    <input id="dbname" name="dbname" type="text" class="form-control" placeholder="Enter Database Name" >
			  		</div>
			  		<div class="form-group">
					    <label for="collname">Collection Name</label>
					    <input id="collname" name="collname" type="text" class="form-control" placeholder="Enter Collection Name" >
			  		</div>
			  		<div class="input-group">
				      <div class="input-group-btn">
				        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" 
				        	aria-expanded="false">Select Chronon <span class="caret"></span></button>
				        <ul class="dropdown-menu">
				          <li><a href="#">Minute</a></li>
				          <li><a href="#">Hour</a></li>
				          <li><a href="#">Day</a></li>
				        </ul>
				      </div>
				      <input type="text" class="form-control" id="chronon_input" aria-label="...">
				    </div>
				    <div style="margin-top : 20px;"></div>
					<div class="col-sm-4 col-sm-offset-4">
			  				<button type="button" class="btn btn-default" id="temporalise_button" name="temporalise_button" 
			  					onclick="temporalise()">Temporalise!!</button>
					</div>
				</div>

				<div class="row" id="upload">
					<div class="form-group">
					    <label for="up_dbname">Database Name</label>
					    <input id="up_dbname" name="up_dbname" type="text" class="form-control" placeholder="Enter Database Name" >
			  		</div>
			  		<div class="form-group">
					    <label for="up_collname">Collection Name</label>
					    <input id="up_collname" name="up_collname" type="text" class="form-control" placeholder="Enter Collection Name" >
			  		</div>
					<div class="form-group">
					    <label for="filepath">File Path</label>
					    <input id="filepath" name="filepath" type="text" class="form-control" placeholder="Enter the file path" >
			  		</div>
					<div class="col-sm-4 col-sm-offset-4">
			  				<button type="button" class="btn btn-default" id="upload_btn" name="upload_btn" 
			  					onclick="upload()">Upload</button>
					</div>
				</div>				
			</div>
		</div>
		
		
</div>

</body>

<script>

$(document).ready(function(){
	$("#temporalise").hide();
	$("#upload").hide();
})

function show_temporalise(){
	$("#upload").hide();
	$("#temporalise").show();
}

function show_upload(){
	$("#temporalise").hide();
	$("#upload").show();
}

$(document).on('click', '.dropdown-menu li a', function() {
    $('#chronon_input').val($(this).html());
}); 

function temporalise(){
	var dbname = $("#dbname").val();
	var collname = $("#collname").val();
	var chronon = $("#chronon_input").val();
	var json_data = JSON.stringify({
		"dbname" : dbname , 
		"collname" : collname ,
		"chronon" : chronon,
	});
	
	$.ajax(
			{
				type : 'POST',
				contentType : 'application/json',
				url : 'http://localhost:8080/Temporal_DB_Project/webapi/features/temporalise',
				data : json_data,
				success : function(){
					alert("Collection is temporalised successfully!!");
				},
				error : function()
						{
							alert("Error in temporalising!!");;
						}
			});
	
}

function upload(){
	
	var dbname = $("#up_dbname").val();
	var collname = $("#up_collname").val();
	var filepath = $("#filepath").val();
	var json_data = JSON.stringify({
		"dbname" : dbname , 
		"collname" : collname ,
		"filepath" : filepath,
	});
	
	$.ajax(
			{
				type : 'POST',
				contentType : 'application/json',
				url : 'http://localhost:8080/Temporal_DB_Project/webapi/features/upload',
				data : json_data,
				success : function(){
					alert("CSV uploaded temporally!!");
				},
				error : function()
						{
							alert("Successfully Uploaded!!");;
						}
			});
}



</script>
</html>