<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
    <%@include file="bootstrap/css/bootstrap.css" %>
    <%@include file="bootstrap/css/bootstrap-theme.css" %>
</style>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="bootstrap/js/jquery-3.2.1.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
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
        				<li><a onclick="show_temp_oper()">Temporal Operations</a></li>
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
				  <div class="form-group">
				    <label for="new_status_label">New Status</label>
				    <textarea id="new_status" name="new_status" class="form-control" rows="3" placeholder="Enter new status"></textarea>
				  </div>
				  <div class="form-group">
				    <label for="new_location_label">New Location</label>
				    <input id="new_location" name="new_location" type="text" class="form-control" placeholder="Enter New Location">
				  </div>
				  <button type="button" class="btn btn-default" id="insert_button" name="insert_button" onclick="insertStatus()">Insert</button>
				  <button type="button" class="btn btn-default" id="update_button" name="update_button">Update</button>
				  <button type="button" class="btn btn-default" id="delete_button" name="delete_button">Delete</button>
			  	</div>
			</form>
		</div>
	</div>
</body>

<script>
$(document).ready(function(){
	$("#crud_part").hide();
	
})

function show_crud(){
	var userid_val=$("#userid").val();
	//var msg="{\"userid\":" +userid_val+"\"}";
	if($("#userid").val() == ''){
		alert("Please enter valid UserId!");
	}
	else
		$("#crud_part").show();
	//alert(msg);
	<%--var ctxPath = "http://localhost:8080/Temporal_DB_Project";
	$.ajax(
	{
		type : 'POST',
		contentType : 'text/plain',
		url : ctxPath + "/webapi/user/",
//		dataType : "json", // data type of response
		success : 
			function(data)
			{
				alert("data send from ajax");
			},
    	error:
    		function() 
    		{
        	alert("error occurred");
    		}
	});--%>
}

function show_temp_oper(){
	if($("#userid").val() == ''){
		alert("Please enter valid UserId!");
	}
	else
		window.location.href= 'operators.jsp';
}

function insertStatus(){
	var userid_val = $("#userid").val();
	var status = $("#new_status").val();
	var location = $("#new_location").val();
	var start_time = null;
	var end_time = null;
	console.log("User Id: "+userid_val+" status: "+status+" location: "+location);
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




</script>
</html>