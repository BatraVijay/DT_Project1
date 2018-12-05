<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
<div class="table-responsive">
<table class="table">	
	<thead>
		<th>Category Name</th>
		<th>Category Desc</th>
		<th>Delete</th>
		<th>Edit</th>
		
	</thead>
	<tbody>
	<c:forEach items="${categories}" var="cObj">
		<tr>
			<td>${cObj.categoryName}</td>
			<td>${cObj.categoryDesc}</td>
			<td>
			<a href="${contextRoot}/deleteCategory/${cObj.categoryId}">
          		<span class="glyphicon glyphicon-trash"></span>
        	</a>
			</td>
			<td>
				<a href="${contextRoot}/editCategory/${cObj.categoryId}">
          			<span class="glyphicon glyphicon-edit"></span>
        		</a>
			</td>
		</tr>  
	</c:forEach>
	</tbody>
</table>	
</div>
</div>	
</body>
</html>