<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" scope="session"/>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Woodland</title>
</head>
<body>

<!-- creation of navbar-->
<nav class="mainNav navbar navbar-expand-md justify-content-center navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand" href="home">WoodS</a>
    </div>	
    <div class="collapse navbar-collapse" id="myNavbar">
    <ul class="nav navbar-nav">
    <li class="active"><a href="${contextRoot}/home">Home</a></li>
    <!-- DropDown bar -->
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Shop By Category
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
        
          <c:forEach items="${categories}" var="category">
          <li><a href="${contextRoot}/getProductsByCategory/${category.categoryId}">${category.categoryName}</a></li>
          </c:forEach>
                    
        </ul>
      </li>
      
     
      <li><a href="${contextRoot}/aboutUs">About us</a></li>
      <li><a href="${contextRoot}/contactUs">Contact us</a></li>
   	 <!-- DropDown bar -->
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin Options
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="${contextRoot}/addCategory">Add Category</a></li>
          <li><a href="${contextRoot}/viewAllCategories">View All Categories</a></li>
          <li><a href="${contextRoot}/addSupplier">Add Supplier</a></li>
          <li><a href="${contextRoot}/viewAllSuppliers">View All Suppliers</a></li>
          <li><a href="${contextRoot}/addProduct">Add Product</a></li>
          <li><a href="${contextRoot}/viewProducts">View All Products</a></li>
                    
        </ul>
      </li>  	
    </ul>
    
    <ul class="nav navbar-nav navbar-right">
    	<sec:authorize access="isAnonymous()">
      <li><a href="${contextRoot}/signUpForm"><span class="glyphicon glyphicon-user"></span>SignUp</a></li>
      <li><a href="${contextRoot}/loginForm"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </sec:authorize>
           	
           	
        <li><a href="#"><span class="glyphicon glyphicon-search"></span></a></li>
         
         
         <sec:authorize access="isAuthenticated()">
         	<li  style="color:white">Welcome : ${sessionScope.userObject.name}</li>
         	<li><a href="${contextRoot}/perform-logout">Logout</a></li>
       		<li><a href="${contextRoot}/viewCart"><span class="glyphicon glyphicon-shopping-cart"></span>Cart</a></li>
       	</sec:authorize>	 
      
    </ul>
  </div>
  </div>
</nav>

</body>
</html>