<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
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
	<f:form class="form-horizontal" action="${contextRoot}/registerUser" method="post" modelAttribute="userObj">
    
    <!-- To show the automatic generated id when we go to update any data -->

    
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Email:</label>
      <div class="col-sm-10">
      
      <!-- In the path , we will write the name of the variables in the categoryName -->
        <f:input type="email" class="form-control" id="email" placeholder="Enter Email" path="email"/>
        <f:errors path="email"/>
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="name">Name:</label>
      <div class="col-sm-10">
      
      <!-- In the path , we will write the name of the variables in the categoryName -->
        <f:input type="text" class="form-control" id="name" placeholder="Enter Name" path="name"/>
        <f:errors path="name"/>
     
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="password">Password:</label>
      <div class="col-sm-10">          
        <f:input type="password" class="form-control" id="password" placeholder="Enter Password" path="password"/>
        <f:errors path="password"/>
     
      </div>
     </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="password2">Confirm Password:</label>
      <div class="col-sm-10">          
        <f:input type="password" class="form-control" id="password2" placeholder="Enter Password" path="password2"/>
        <f:errors path="password2"/>
     
      </div>
     </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="phone">Enter Phoneno:</label>
      <div class="col-sm-10">          
        <f:input type="tel" class="form-control" id="phone" placeholder="Enter Phone number" path="phone"/>
        <f:errors path="phone"/>
     
      </div>
    </div> 
    
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">SignUp</button>
      </div>
    </div>
    
  </f:form>
</div>

</body>

</html>