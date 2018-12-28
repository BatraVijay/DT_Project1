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
<div class="Container">
<h1 align="center">${formLabel}</h1>
<f:form class="form-horizontal" action="${contextRoot}/addProductProcess" method="post" modelAttribute="productObj" enctype="multipart/form-data">


<c:if test="${not empty operation}">
<div class="form-group">
<label class="control-label col-sm-2" for="productId">Product Id </label>
<div class="col-sm-10">
<f:input type="text" class="form-control" id="productId" path="productId" readonly="true"/>
</div>
</div>
</c:if>


<div class="form-group">
<label class="control-label col-sm-2" for="productName"> Product Name :</label>
<div class="col-sm-10">
  <f:input type="text" class="form-control" id="productName" placeholder="Enter the Product name" path="productName"/>
  <f:errors path="productName"/>
</div>
</div>

<div class="form-group">
<label class="control-label col-sm-2" for="productDesc"> Product Description :</label>
<div class="col-sm-10">
  <f:input type="text" class="form-control" id="productDesc" placeholder="Enter the Product Description" path="productDesc"/>
  <f:errors path="productDesc"/>
</div>
</div>

<div class="form-group">
<label class="control-label col-sm-2" for="productDesc"> Select Supplier</label>
<div class="col-sm-10">
 <f:select path="supplierId" class="form-control">
 <f:option value="0" class="form-control">----Select Supplier---</f:option>
 <c:forEach items="${supplierList}" var="pObj">
  <f:option value="${pObj.supplierId}" class="form-control">${pObj.supplierName}</f:option>
 </c:forEach>
 
 </f:select>
<f:errors path="supplierId"/>
 
</div>
</div>

<div class="form-group">
<label class="control-label col-sm-2" for="categoryId"> Select Category:</label>
<div class="col-sm-10">
  <f:select path="categoryId" class="form-control">
  <f:option value="0" class="form-control">----Select category---</f:option>
  <c:forEach items="${categoryList}" var="cObj">
  <f:option value="${cObj.categoryId}" class="form-control">${cObj.categoryName}</f:option>
  </c:forEach>
  </f:select>
<f:errors path="categoryId"/>
    
</div>
</div>

<div class="form-group">
<label class="control-label col-sm-2" for="Stock"> Stock :</label>
<div class="col-sm-10">
  <f:input type="number" class="form-control" id="Stock" placeholder="Enter the Stock" path="stock"/>
  <f:errors path="stock"/>
</div>
</div>

<div class="form-group">
<label class="control-label col-sm-2" for="price"> Price :</label>
<div class="col-sm-10">
  <f:input type="number" class="form-control" id="price" placeholder="Enter the Price" path="price"/>
  <f:errors path="price"/>
</div>
</div>

<div class="form-group">
<label for="pimage" class="control-label col-sm-2"> Upload Image :</label>
<div class="col-sm-10">
<f:input type="file" class="form-control" id="vimage" placeholder="Choose Image" path="vimage"/>
<f:errors path="vimage"/>

</div>
</div>



<div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">${btnLabel}</button>
      </div>
    </div>

</f:form>
</div>

</body>
</html>