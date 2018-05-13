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
<title>Welcome!</title>
</head>
<body>

	<div class=container>
	
	<!-- Navigation Bar -->
		<div class=row>
			<nav class="navbar navbar-default">
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      				<ul class="nav navbar-nav">
        				<li class="active"><a href="#">Home <span class="sr-only">(current)</span></a></li>
        				<li><a href="operators.jsp">Temporal Operations</a></li>
        				<li><a href="otherfeatures.jsp">Other Features</a></li>
        			</ul>
        		</div>
			</nav>
		</div>
		
	<!-- Form Part -->	
		<div class=row>
			<form>
			  <div class="form-group">
			    <label for="userid">UserId</label>
			    <input id="userid" name="userid" type="text" class="form-control" placeholder="Enter UserId" >
			  </div>
			  <button type="button" class="btn btn-default" id="enter_userid_button" name="enter_userid_button" onclick="show_crud()">Enter</button>
			  
			  <div id="crud_part">
			  	  <div id=current_status_div>
			  	  	<div style="margin-top : 20px;"></div>
			  	  	<label>Current Status </label>
			  	  	<div><p id="curr_status"></p></div>
					<div style="margin-top : 15px;"></div>
					<label>Current Location </label>
					<div><p id="curr_location"></p></div>
					<div style="margin-top : 15px;"></div>
			  	  </div>
				  <div class="form-group">
				    <label for="new_status_label">New Status</label>
				    <textarea id="new_status" name="new_status" class="form-control" rows="3" placeholder="Enter new status"></textarea>
				  </div>
				  <div class="form-group">
				    <label for="new_location_label">New Location</label>
				    <input id="new_location" name="new_location" type="text" class="form-control" placeholder="Enter New Location">
				  </div>
			  </div>
				  <button type="button" class="btn btn-default" id="insert_button" name="insert_button" onclick="insertStatus()">Insert</button>
				  <button type="button" class="btn btn-default" id="update_button" name="update_button" onclick="updateStatus()">Update</button>
				  <button type="button" class="btn btn-default" id="delete_button" name="delete_button" onclick="deleteStatus()">Delete</button>
				  <button type="reset" class="btn btn-default" id="reset_button" name="reset_button" onclick="resetStatus()">Reset</button>			  	
			  	</div>
			</form>
		</div>
	</div>
</body>

<script>
$(document).ready(function(){
	$("#crud_part").hide();
	$("#update_button").hide();
	$("#insert_button").hide();
	$("#delete_button").hide();
	$("#reset_button").hide();
})

function resetStatus(){
	$("#crud_part").hide();
	$("#update_button").hide();
	$("#insert_button").hide();
	$("#delete_button").hide();
	document.getElementById('curr_status').innerHTML = "";
	document.getElementById('curr_location').innerHTML = "";
	$("#reset_button").hide();
	
}

function show_crud(){
	var userid_val=$("#userid").val();
	var json_data = JSON.stringify({
		"userid" : userid_val});
	
	 $.ajax(
			{
				type : 'POST',
				contentType : 'application/json',
				url : 'http://localhost:8080/Temporal_DB_Project/webapi/status/retrieve',
				dataType : "json",
				data : json_data , 
				success : function(retr_status){
					var status = retr_status.status;
					var location = retr_status.location;
					console.log(status+" "+location);
					if((status==null)&&(location==null)){
						$("#insert_button").show();
						$("#reset_button").show();
					}
					else{
						$("#update_button").show();
						$("#delete_button").show();
						$("#reset_button").show();
					}
					var data="";
					$('#curr_status').append(status);
					$('#curr_location').append(location);
				},
				error : function()
						{
							alert("Error in retrieving through ajax");;
						}
		});
		
	if($("#userid").val() == ''){
		alert("Please enter valid UserId!");
	}
	else
		$("#crud_part").show();

}

function insertStatus(){
	var userid_val = $("#userid").val();
	var status = $("#new_status").val();
	var location = $("#new_location").val();
	var start_time = null;
	var end_time = null;
	var json_data = JSON.stringify({
		"userid" : userid_val,
		"status" : status,
		"location" : location,
		"start_time": start_time,
		"end_time" : end_time
	});
	
	$.ajax(
			{
				type : 'POST',
				contentType : 'application/json',
				url : 'http://localhost:8080/Temporal_DB_Project/webapi/status/insert',
				data : json_data,
				success : function(){
					alert("Data successfully send by ajax");
				},
				error : function()
						{
							alert("Error in sending through ajax");;
						}
			});
	
}

function updateStatus(){
	var userid_val = $("#userid").val();
	var status = $("#new_status").val();
	var location = $("#new_location").val();
	var start_time = null;
	var end_time = null;
	var json_data = JSON.stringify({
		"userid" : userid_val,
		"status" : status,
		"location" : location,
		"start_time": start_time,
		"end_time" : end_time
	});
	
	$.ajax(
			{
				type : 'POST',
				contentType : 'application/json',
				url : 'http://localhost:8080/Temporal_DB_Project/webapi/status/update',
				data : json_data,
				success : function(){
					alert("Data successfully send by ajax");
				},
				error : function()
						{
							alert("Error in sending through ajax");;
						}
			});
	
}

function deleteStatus(){
	
	var userid_val = $("#userid").val();
	$.ajax(
			{
				type : 'POST',
				contentType : 'text/plain',
				url : 'http://localhost:8080/Temporal_DB_Project/webapi/status/delete',
				data : userid_val,
				success : function(){
					alert("Data successfully send by ajax");
				},
				error : function()
						{
							alert("Error in sending through ajax");;
						}
			});
	
}


</script>
</html>