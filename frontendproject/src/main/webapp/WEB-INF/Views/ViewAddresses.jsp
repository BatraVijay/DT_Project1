<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<sp:url value="/resources/images" var="images"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Bag Store</title>
</head>
<body>

<jsp:include page="./shared/Header.jsp"/>

<br>
<c:if test="${not empty msg}">
<div class="alert alert-success">
  <strong>${msg}</strong>
</div>
</c:if>

<div class="container">
<div class="table-responsive">

<table class="table">
<thead>
<tr> 
	  <th>Choose</th>
	   <th>House Number</th>
       <th>Locality</th>
       <th>City</th>
       <th>State</th>
        <th>Pincode</th>
        <th>Country</th>
       <th>Delete</th>
       <th>Edit</th>
       
</tr>
</thead>
 <tbody>
 <c:forEach items="${addressList}" var="aObj">
 <tr>
 	<td>
 		<a href="${contextRoot}/showOrder/${aObj.id}">Select</a>
 	</td>
    <td>${aObj.houseNumber}</td>
    <td>${aObj.locality}</td>
    <td>${aObj.city}</td>
    <td>${aObj.state}</td>
    <td>${aObj.pinCode}</td>
    <td>${aObj.country}</td>
    <td>
               <a href="${contextRoot}/deleteAddress/${aObj.id}">
               <span class="glyphicon glyphicon-trash"></span>
               </a>
            </td>
            <td>
               <a href="${contextRoot}/editAddress/${aObj.id}">
               <span class="glyphicon glyphicon-edit"></span>
               </a>
           </td>
    
    
 </tr>
 </c:forEach>
 </tbody>
</table>

<br/>
<a href="${contextRoot}/addAddress">Add New Address</a>

</div>
</div>
</body>
</html>