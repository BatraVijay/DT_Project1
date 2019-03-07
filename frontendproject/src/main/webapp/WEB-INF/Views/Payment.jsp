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
	<center><h1>Payment Mode</h1></center><hr/>
	<f:form class="form-horizontal" action="${contextRoot}/addToCart/processOrder" method="post" modelAttribute="orderObj">
    
    <!-- To show the automatic generated id when we go to update any data -->

    
    <div class="form-group">
      <label class="control-label col-sm-2" for="cardNumber">Card Number:</label>
      <div class="col-sm-10">
      
        <f:input type="number" class="form-control" id="cardNumber" placeholder="Enter card no " path="cardNumber"/>
       
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="nameOnCard">Name on card:</label>
      <div class="col-sm-10">
      
        <f:input type="text" class="form-control" id="nameOnCard" placeholder="Enter Ur Card Name" path="nameOnCard"/>
       
     
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="month">Month:</label>
      <div class="col-sm-10">          
        <f:input type="text" class="form-control" id="month" placeholder="Enter month" path="month"/>
       
     
      </div>
     </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="year">Year:</label>
      <div class="col-sm-10">          
        <f:input type="number" class="form-control" id="year" placeholder="Enter year" path="year"/>
       
     
      </div>
     </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="cvv">CVV:</label>
      <div class="col-sm-10">          
        <f:input type="password" class="form-control" id="cvv" placeholder="Enter your cvv no" path="cvv"/>
      
      </div>
     </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="totalPrice">Price:</label>
      <div class="col-sm-10">          
        <f:input type="number" class="form-control" id="totalPrice" placeholder="Enter total price" path="totalPrice"/>
      
      </div>
     </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Proceed</button>
      </div>
    </div>
    
  </f:form>
</div>

</body>

</html>