<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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



<div class="container">
  <center><h1>${formLabel} of Woods</h1></center><hr/>
  <f:form class="form-horizontal" action="${contextRoot}/addCategoryProcess" method="post" modelAttribute="categoryObj">
    
    <!-- To show the automatic generated id when we go to update any data -->
    
    <c:if test="${operation eq 'Update'}">
	<div class="form-group">
      <label class="control-label col-sm-2" for="categoryId">Category Id:</label>
      <div class="col-sm-10">
      
      <!-- In the path , we will write the name of the variables in the categoryName -->
        <f:input type="text" class="form-control" id="categoryId" placeholder="Enter Category Id" path="categoryId" readonly="true"/>
      </div>
    </div>
    </c:if>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="categoryName">Category Name:</label>
      <div class="col-sm-10">
      
      <!-- In the path , we will write the name of the variables in the categoryName -->
        <f:input type="text" class="form-control" id="categoryName" placeholder="Enter Category Name" path="categoryName"/>
	    <f:errors path="categoryName"/>
    
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="categoryDesc">Category Description:</label>
      <div class="col-sm-10">          
        <f:input type="text" class="form-control" id="categoryDesc" placeholder="Enter Category Description" path="categoryDesc"/>
        <f:errors path="categoryDesc"/>
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