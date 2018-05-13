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
        				<li class="active"><a href="#">Temporal Operations</a></li>
        				<li><a href="otherfeatures.jsp">Other Features</a></li>
        			</ul>
        		</div>
			</nav>
		</div>
		
		<div class="row">
			<div class="col-lg-3">
			    <div class="row">
				    <h3><span class="label label-default">Operators</span></h3>
				    <ul class="nav nav-pills nav-stacked">
						  <li role="presentation" onclick="show_getCommon()"><a>First</a></li>
						  <li role="presentation" onclick="show_getCommon()"><a>Last</a></li>
						  <li role="presentation" onclick="show_getPrevNextVal()"><a>Previous Value</a></li>
						  <li role="presentation" onclick="show_getPrevNextVal()"><a>Next Value</a></li>
						  <li role="presentation" onclick="show_getCommon()"><a>Previous Hour</a></li>
						  <li role="presentation" onclick="show_getCommon()"><a>Previous Month</a></li>
						  <li role="presentation" onclick="show_getCommon()"><a>First Evolution</a></li>
						  <li role="presentation" onclick="show_getCommon()"><a>Last Evolution</a></li>
						  <li role="presentation" onclick="show_getCommon()"><a>Evolution History</a></li>
						  <li role="presentation" onclick="show_getEvolution()"><a>Evolution from Val1 to Val2</a></li>
						  <li role="presentation" onclick="show_getOverlap()"><a>Overlap</a></li>
						  <li role="presentation" onclick="show_getJoin()"><a>Join</a></li>
					</ul>
			    </div>
			    <input type="hidden" class="form-control" id="hidden_input" aria-label="...">
			</div>
			
			<div class="col-sm-9">
				<div class="row">
					<div class="col-sm-6" id="userid1_div">
						<div class="form-group">
						    <label for="userid1">UserId1</label>
						    <input id="userid1" name="userid1" type="text" class="form-control" placeholder="Enter UserId1" >
				  		</div>
			  		</div>
			  		<div class="col-sm-6" id="userid2_div">
						<div class="form-group">
						    <label for="userid2">UserId2</label>
						    <input id="userid2" name="userid2" type="text" class="form-control" placeholder="Enter UserId2" >
				  		</div>
			  		</div>
				</div>
				<div class="row" id="fieldname_div">
					<div class="col-sm-4">
						<div class="form-group">
						    <label for="fieldname">Field Name</label>
						    <input id="fieldname" name="fieldname" type="text" class="form-control" placeholder="Enter Field Name" >
				  		</div>
			  		</div>
			  		<div class="col-sm-4" id="fieldvalue1_div" >
						<div class="form-group">
						    <label for="fieldvalue1">Value1</label>
						    <input id="fieldvalue1" name="fieldvalue1" type="text" class="form-control" placeholder="Enter Value1 to be queried" >
				  		</div>
			  		</div>
			  		<div class="col-sm-4" id="fieldvalue2_div">
						<div class="form-group">
						    <label for="fieldvalue2">Value2</label>
						    <input id="fieldvalue2" name="fieldvalue2" type="text" class="form-control" placeholder="Enter Value2 to be queried" >
				  		</div>
			  		</div>
				</div>
				<div class="row" id="time_div">
					<div class="col-sm-6" id="starttime_div">
						<div class="form-group">
						    <label for="start_time">Start Time</label>
						    <input id="start_time" name="strat_time" type="text" class="form-control" placeholder="Enter Start time" >
				  		</div>
			  		</div>
			  		<div class="col-sm-6" id="endtime_div">
						<div class="form-group">
						    <label for="end_time">End Time</label>
						    <input id="end_time" name="end_time" type="text" class="form-control" placeholder="Enter End Time" >
				  		</div>
			  		</div>
				</div>
				<div class="row" id="coll1name_div">
					<label for=coll1>Collection 1</label>
					<input id="coll1_text" name="coll1_text" type="text" class="form-control" placeholder="Enter collection 1 name" >
				</div>
				<div class="row" id="coll2name_div">
					<label for=coll2>Collection 2</label>
					<input id="coll2_text" name="coll2_text" type="text" class="form-control" placeholder="Enter collection 2 name" >
				</div>
				<div style="margin-top: 20 px;"></div>
				<div class="row" id="submit_div">
					<div class="col-sm-4 col-sm-offset-5">
			  				<button type="button" class="btn btn-default" id="submit_btn" name="submit_btn" 
			  					onclick="submit()">Submit</button>
					</div>
				</div>
				<div class="row" id="result_div">
					<label for=result>Result</label>
					<textarea class=form-control rows=10 id=result_text></textarea>
				</div>
				
			</div>
			
			
		</div>

	</div>
		

</body>
<script>

$(document).ready(function(){
	$("#userid1_div").hide();
	$("#userid2_div").hide();
	$("#fieldname_div").hide();
	$("#fieldvalue1_div").hide();
	$("#fieldvalue2_div").hide();
	$("#time_div").hide();
	$("#submit_div").hide();
	$("#result_div").hide();
	$("#coll1name_div").hide();
	$("#coll2name_div").hide();
})

function show_getCommon(){
	$("#userid1_div").show();
	$("#fieldname_div").show();
	$("#submit_div").show();
	$("#result_div").show();
}

function show_getPrevNextVal(){
	$("#userid1_div").show();
	$("#fieldname_div").show();
	$("#fieldvalue1_div").show();
	$("#submit_div").show();
	$("#result_div").show();
}

function show_getEvolution(){
	$("#userid1_div").show();
	$("#fieldname_div").show();
	$("#fieldvalue1_div").show();
	$("#fieldvalue2_div").show();
	$("#submit_div").show();
	$("#result_div").show();
}

function show_getOverlap(){
	$("#userid1_div").show();
	$("#userid2_div").show();
	$("#time_div").show();
	$("#submit_div").show();
	$("#result_div").show();
}

function show_getJoin(){
	$("#fieldname_div").show();
	$("#coll1name_div").show();
	$("#coll2name_div").show();
	$("#time_div").show();
	$("#submit_div").show();
	$("#result_div").show();
}

$(document).on('click', '.nav-stacked li a', function() {
    $('#hidden_input').val($(this).html());
}); 

function doReset(){
	$("#userid1_div").hide();
	$("#userid2_div").hide();
	$("#fieldname_div").hide();
	$("#fieldvalue1_div").hide();
	$("#fieldvalue2_div").hide();
	$("#time_div").hide();
	//document.getElementById('#hidden_input').innerHTML = "";
	$("#submit_div").hide();
	$("#result_div").hide();
}

function submit(){
	
	var operator_name = $("#hidden_input").val();
	
	switch(operator_name){
	
		case "First":
			getFirst();
			break;
		
		case "Last":
			getLast();
			break;
			
		case "Previous Value":
			getPrevVal();
			break;
			
		case "Next Value":
			getNextVal();
			break;
			
		case "Previous Hour":
			getPrevHour();
			break;
			
		case "Previous Month":
			getPrevMon();
			break;
			
		case "First Evolution":
			getFirstEv();
			break;
			
		case "Last Evolution":
			getLastEv();
			break;
		
		case "Evolution History":
			getEvolHis();
			break;
			
		case "Evolution from Val1 to Val2":
			getEvolution();
			break;
			
		case "Overlap":
			getOverlap();
			break;
			
		case "Join":
			getJoin();
			break;
	}
}

	function getFirst(){
		var userid1 = $("#userid1").val();
		var fieldname = $("#fieldname").val();
		var json_data = JSON.stringify({
			"userid" : userid1 ,
			"fieldname" : fieldname
		});
		
		$.ajax(
				{
					type : 'POST',
					contentType : 'application/json',
					url : 'http://localhost:8080/Temporal_DB_Project/webapi/operation/first',
					dataType : "json",
					data : json_data , 
					success : function(result){
						result = JSON.stringify(result);
						var data = result;
						$("#result_text").append(data);
					},
					error : function()
							{
								alert("Error in retrieving through ajax");
							}
			});
	}
	
function getLast(){
	var userid1 = $("#userid1").val();
	var fieldname = $("#fieldname").val();
	var json_data = JSON.stringify({
		"userid" : userid1 ,
		"fieldname" : fieldname
	});
	
	$.ajax(
			{
				type : 'POST',
				contentType : 'application/json',
				url : 'http://localhost:8080/Temporal_DB_Project/webapi/operation/last',
				dataType : "json",
				data : json_data , 
				success : function(result){
					result = JSON.stringify(result);
					var data = result;
					$("#result_text").append(data);
				},
				error : function()
						{
							alert("Error in retrieving through ajax");
						}
		});
}

function getPrevMon(){
	var userid1 = $("#userid1").val();
	var fieldname = $("#fieldname").val();
	var json_data = JSON.stringify({
		"userid" : userid1 ,
		"fieldname" : fieldname
	});
	
	$.ajax(
			{
				type : 'POST',
				contentType : 'application/json',
				url : 'http://localhost:8080/Temporal_DB_Project/webapi/operation/previousmonth',
				dataType : "json",
				data : json_data , 
				success : function(result){
					result = JSON.stringify(result);
					var data = result;
					$("#result_text").append(data);
				},
				error : function()
						{
							alert("Error in retrieving through ajax");
						}
		});
}

function getPrevHour(){
	var userid1 = $("#userid1").val();
	var fieldname = $("#fieldname").val();
	var json_data = JSON.stringify({
		"userid" : userid1 ,
		"fieldname" : fieldname
	});
	
	$.ajax(
			{
				type : 'POST',
				contentType : 'application/json',
				url : 'http://localhost:8080/Temporal_DB_Project/webapi/operation/previoushour',
				dataType : "json",
				data : json_data , 
				success : function(result){
					result = JSON.stringify(result);
					var data = result;
					$("#result_text").append(data);
				},
				error : function()
						{
							alert("Error in retrieving through ajax");
						}
		});
}

		function getFirstEv(){
			var userid1 = $("#userid1").val();
			var fieldname = $("#fieldname").val();
			var json_data = JSON.stringify({
				"userid" : userid1 ,
				"fieldname" : fieldname });
			
			$.ajax(
					{
						type : 'POST',
						contentType : 'application/json',
						url : 'http://localhost:8080/Temporal_DB_Project/webapi/operation/firstevolution',
						dataType : "json",
						data : json_data , 
						success : function(result){
							result = JSON.stringify(result);
							var data = result;
							$("#result_text").append(data);
						},
						error : function()
								{
									alert("Error in retrieving through ajax");
								}
				});
		}
			
	function getLastEv(){
		var userid1 = $("#userid1").val();
		var fieldname = $("#fieldname").val();
		var json_data = JSON.stringify({
			"userid" : userid1 ,
			"fieldname" : fieldname
		});
		
		$.ajax(
				{
					type : 'POST',
					contentType : 'application/json',
					url : 'http://localhost:8080/Temporal_DB_Project/webapi/operation/lastevolution',
					dataType : "json",
					data : json_data , 
					success : function(result){
						result = JSON.stringify(result);
						var data = result;
						$("#result_text").append(data);
					},
					error : function()
							{
								alert("Error in retrieving through ajax");
							}
			});
	}
	
	function getPrevVal(){
		var userid1 = $("#userid1").val();
		var fieldname = $("#fieldname").val();
		var fieldvalue1 = $("#fieldvalue1").val();
		var json_data = JSON.stringify({
			"userid" : userid1 ,
			"fieldname" : fieldname , 
			"fieldvalue1" : fieldvalue1
		});
		
		$.ajax(
				{
					type : 'POST',
					contentType : 'application/json',
					url : 'http://localhost:8080/Temporal_DB_Project/webapi/operation/prevalue',
					dataType : "json",
					data : json_data , 
					success : function(result){
						result = JSON.stringify(result);
						var data = result;
						$("#result_text").append(data);
					},
					error : function()
							{
								alert("Error in retrieving through ajax");
							}
			});
	}
	
	function getNextVal(){
		var userid1 = $("#userid1").val();
		var fieldname = $("#fieldname").val();
		var fieldvalue1 = $("#fieldvalue1").val();
		var json_data = JSON.stringify({
			"userid" : userid1 ,
			"fieldname" : fieldname , 
			"fieldvalue1" : fieldvalue1
		});
		
		$.ajax(
				{
					type : 'POST',
					contentType : 'application/json',
					url : 'http://localhost:8080/Temporal_DB_Project/webapi/operation/nextvalue',
					dataType : "json",
					data : json_data , 
					success : function(result){
						result = JSON.stringify(result);
						var data = result;
						$("#result_text").append(data);
					},
					error : function()
							{
								alert("Error in retrieving through ajax");
							}
			});
	}
	
	function getEvolHis(){
		var userid1 = $("#userid1").val();
		var fieldname = $("#fieldname").val();
		var json_data = JSON.stringify({
			"userid" : userid1 ,
			"fieldname" : fieldname
		});
		
		$.ajax(
				{
					type : 'POST',
					contentType : 'application/json',
					url : 'http://localhost:8080/Temporal_DB_Project/webapi/operation/evolhistory',
					dataType : "json",
					data : json_data , 
					success : function(result){
						result = JSON.stringify(result);
						var data = result;
						$("#result_text").append(data);
					},
					error : function()
							{
								alert("Error in retrieving through ajax");
							}
			});
	}
	
	function getEvolution(){
		var userid1 = $("#userid1").val();
		var fieldname = $("#fieldname").val();
		var fieldvalue1 = $("#fieldvalue1").val();
		var fieldvalue2 = $("#fieldvalue2").val();
		var json_data = JSON.stringify({
			"userid" : userid1 ,
			"fieldname" : fieldname , 
			"fieldvalue1" : fieldvalue1 ,
			"fieldvalue2" : fieldvalue2 
		});
		
		$.ajax(
				{
					type : 'POST',
					contentType : 'application/json',
					url : 'http://localhost:8080/Temporal_DB_Project/webapi/operation/evolution',
					dataType : "json",
					data : json_data , 
					success : function(result){
						result = JSON.stringify(result);
						var data = result;
						$("#result_text").append(data);
					},
					error : function()
							{
								alert("Error in retrieving through ajax");
							}
			});
	}
	
	function getOverlap(){
		var userid1 = $("#userid1").val();
		var userid2 = $("#userid2").val();
		var start_time = $("#start_time").val();
		var end_time = $("#end_time").val();
		var json_data = JSON.stringify({
			"userid1" : userid1 ,
			"userid2" : userid2 , 
			"start_time" : start_time ,
			"end_time" : end_time 
		});
		
		$.ajax(
				{
					type : 'POST',
					contentType : 'application/json',
					url : 'http://localhost:8080/Temporal_DB_Project/webapi/operation/overlap',
					dataType : "text",
					data : json_data , 
					success : function(result){
						var data = result;
						$("#result_text").append(data);
					},
					error : function()
							{
								alert("This combination does not has any overlap!!!");
							}
			});
	}
	
	function getJoin(){
		var fieldname = $("#fieldname").val();
		var coll1 = $("#coll1_text").val();
		var coll2 = $("#coll2_text").val();
		var start_time = $("#start_time").val();
		var end_time = $("#end_time").val();
		var json_data = JSON.stringify({
			"fieldname" : fieldname ,
			"coll2" : coll2 ,
			"coll1" : coll1 ,
			"start_time" : start_time ,
			"end_time" : end_time
		});
		
		$.ajax(
				{
					type : 'POST',
					contentType : 'application/json',
					url : 'http://localhost:8080/Temporal_DB_Project/webapi/operation/join',
					dataType : "text",
					data : json_data , 
					success : function(result){
						var data = result;
						$("#result_text").append(data);
					},
					error : function()
							{
								alert("This combination does not has any overlap!!!");
							}
			});
	}
	
</script>
</html>