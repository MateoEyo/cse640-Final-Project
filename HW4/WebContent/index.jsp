<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Albums</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Custom styles for this template -->
    <link href="css/sticky-footer-navbar.css" rel="stylesheet">
    <link href="css/carousel.css" rel="stylesheet">
    
</head>
<body>

	<!-- NAVBAR CODE BEGIN -->
	<div id="navigation"></div>
	<script src="js/nav.js"></script>
	<br>
	<!-- NAVBAR CODE END -->
	
	<!-- CAROUSEL BEGIN -->
	<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  		<ol class="carousel-indicators">
    		<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    		<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    		<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  		</ol>
  		<div class="carousel-inner">
    		<div class="carousel-item active">
      			<img class="d-block w-100" src="images/guys.jpg" alt="First slide">
    		</div>
    		<div class="carousel-item">
      			<img class="d-block w-100" src="images/purple.jpg" alt="Second slide">
    		</div>
    		<div class="carousel-item">
      			<img class="d-block w-100" src="images/mic.jpg" alt="Third slide">
    		</div>
  		</div>
  		<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    		<span class="carousel-control-prev-icon" aria-hidden="true"></span>
    		<span class="sr-only">Previous</span>
  		</a>
  		<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    		<span class="carousel-control-next-icon" aria-hidden="true"></span>
    		<span class="sr-only">Next</span>
  		</a>
	</div>
	<!-- CAROUSEL END -->
	
	<div class="container">
		<h2>Here's a list of all albums in our database</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Musician</th>
					<th>Genre</th>
					<th>Instrument</th>
				</tr>
			</thead>
			<c:forEach var="element" items="${albumList}">
				<tr>
					<td>${element.name}</td>
					<td>${element.artist}</td>
					<td>${element.genre}</td>
					<td>${element.instrument}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<!-- FOOTER CODE BEGIN -->
	<div id="footer"></div>
	<script src="js/footer.js"></script>
	<!-- FOOTER CODE END -->
	
</body>
</html>