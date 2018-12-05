<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>WoodLand</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<jsp:include page="./shared/Header.jsp"/>	
  
<style>
	
	
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: black;
   color: white;
   text-align: right;
}
</style>

<center>
<h1><b>WOODS</b></h1></center>

<div class="container">
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
        <img src="resources\images\h.jpg" alt="Los Angeles">
      </div>

      <div class="item">
        <img src="resources\images\h1.jpg" alt="Los Angeles">      </div>
    
      <div class="item">
            <img src="resources\images\h2.jpg" alt="Los Angeles">  </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>





<br/>



<!-- Photo Grid -->
<div class="row">
  <div class="col-sm-6"> <img src="resources\images\h3.jpg" style="height:550px;width:600px"></div>
  <div class="col-sm-6"> <img src="resources\images\h4.jpg" style="height:550px;width:600px" class="rounded-circle"></div>
</div>
</div>

<br/>
<img src="resources\images\h5.jpg" style="height:800px;width:100%">


<div class="footer" >
  <h6>COPYRIGHT 2018,WoodS to VijayBatra</h6>
</div>
</body>
</html>