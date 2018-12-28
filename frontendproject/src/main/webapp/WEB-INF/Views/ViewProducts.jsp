<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Woodland</title>
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
       <th>Product Name</th>
       <th>Product Desc</th>
       <!-- <th>Cat Name</th>
       <th>Supplier Name</th>
        --><th>Stock</th>
       <th>Price</th>
       <th>Product Image</th>
       <th>Delete</th>
       <th>Edit</th>
       <th>Cart</th>
</tr>
</thead>
 <tbody>
 <c:forEach items="${products}" var="pObj">
 <tr>
    <td>${pObj.productName}</td>
    <td>${pObj.productDesc}</td>
    <%-- <td>${pObj.category.categoryName}</td>
    <td>${pObj.supplier.supplierName}</td>
     --%><td>${pObj.stock}</td>
    <td>${pObj.price}</td>
    <td><img src="${contextRoot}/resources/images/${pObj.img}" style="height:150px;width:150px"></td>
    
    <td>
    <a href="${contextRoot}/deleteProduct/${pObj.productId}">
    <span class="glyphicon glyphicon-trash"></span>
    </a>
    </td>
    <td>
    <a href="${contextRoot}/editProduct/${pObj.productId}">
    <span class="glyphicon glyphicon-edit"></span>
    </a>
    </td>
    <td>
    <a href="${contextRoot}/addToCart/${pObj.productId}">
    <span class="glyphicon glyphicon-shopping-cart"></span>
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
