<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Woodland</title>
</head>
<body>
	
<jsp:include page="./shared/Header.jsp"/>	


<br/>

<div class="container">
	<center><h1>SignUp Form</h1></center><hr/>
	<form class="form-horizontal">
		<div class="form-group">
			<label class="control-label col-sm-2">Email:</label>
			<div class="col-sm-10">
			<input type="email" class="form-control" placeholder="Enter Email" name="email"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">First Name:</label>
			<div class="col-sm-10">
			<input type="text" class="form-control" placeholder="Enter First Name" name="firstName"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">Last Name:</label>
			<div class="col-sm-10">
			<input type="text" class="form-control" placeholder="Enter Last Name" name="lastName"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">Password:</label>
			<div class="col-sm-10">
			<input type="password" class="form-control" placeholder="Enter Password" name="password"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">Confirm Password:</label>
			<div class="col-sm-10">
			<input type="password" class="form-control" placeholder="Enter Password Again" name="password2"/>
			</div>
		</div>
		<div class="form-group">        
      	<div class="col-sm-offset-2 col-sm-10">
        	<button type="submit" class="btn btn-default">Submit</button>
      	</div>
    </div>
		
	</form>	
</div>
	
</body>
</html>