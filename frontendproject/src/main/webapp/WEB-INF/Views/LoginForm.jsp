	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

	<c:if test="${not empty message}">
	<div class="alert alert-success">
	${message}
	</div>
	</c:if>

  <center><h1>Login Form</h1></center><hr/>
  <form class="form-horizontal" action="${contextRoot}/login" method="post">  
  <div class="form-group">
      <label class="control-label col-sm-2" for="email">Email:</label>
      <div class="col-sm-10">
        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" required/>
        
     
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required/>
        
      </div>
    </div>
    <div class="row">
  <div class="col-sm-12">
  
  
    <div class="text-center">
    <input type="hidden"
    name="${_csrf.parameterName}"
    value="${_csrf.token}"/>
      <button class="btn btn-default"id="singlebutton" style="background-color:#E65FAC;color:white">Login</button>
    </div>
  </div>
</div>
  </form>

</div>

	
</body>
</html>