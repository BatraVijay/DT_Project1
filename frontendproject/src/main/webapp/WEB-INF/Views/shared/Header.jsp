<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NavBar</title>
</head>
<body>

<!-- creation of navbar-->
<nav class="navbar navbar-inverse">
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
    <li class="active"><a href="home">Home</a></li>
    <!-- DropDown bar -->
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Products
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Mens</a></li>
          <li><a href="#">Womens</a></li>
          <li><a href="#">Shoes</a></li>
          <li><a href="#">OutDoorProducts</a></li>
          <li><a href="viewCategory">All Products</a></li>          
        </ul>
      </li>
     
      <li><a href="aboutUs">About us</a></li>
      <li><a href="contactUs">Contact us</a></li>
   	 <!-- DropDown bar -->
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin Options
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="addCategory">Add Category</a></li>
          <li><a href="viewAllCategories">View All Categories</a></li>
          <li><a href="addSupplier">Add Supplier</a></li>
          <li><a href="viewAllSuppliers">View All Suppliers</a></li>
          <li><a href="#">Add Product</a></li>
          <li><a href="#">View All Products</a></li>
                    
        </ul>
      </li>  	
    </ul>
    
    <ul class="nav navbar-nav navbar-right">
      <li><a href="signUpForm"><span class="glyphicon glyphicon-user"></span>SignUp</a></li>
      <li><a href="loginForm"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
  </div>
</nav>

</body>
</html>