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
        			</ul>
        		</div>
			</nav>
		</div>
		
		<div class="row">
			<div class="col-lg-3">
			    <div class="row">
				    <h3><span class="label label-default">Operators</span></h3>
				    <ul class="nav nav-pills nav-stacked">
						  <li role="presentation" onclick="show_getFirst()"><a>First</a></li>
						  <li role="presentation" onclick="show_getLast()"><a>Last</a></li>
						  <li role="presentation" onclick="show_getPrevVal()"><a>Previous Value</a></li>
						  <li role="presentation" onclick="show_getPrevHour()"><a>Previous Hour</a></li>
						  <li role="presentation" onclick="show_getPrevMon()"><a>Previous Month</a></li>
						  <li role="presentation" onclick="show_getEvolHis()"><a>Evolution History</a></li>
					</ul>
			    </div>
			</div>
			<div class="col-lg-9">
				<span></span>
				<div class="row">
					<label for="result">Result</label>
					<textarea class="form-control" rows="3"></textarea>
				</div>
			</div>
			
		</div>

	</div>
		

</body>
<script>
	function show_getFirst(){
		
	}
</script>
</html>