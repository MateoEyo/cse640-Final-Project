<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Users</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Custom styles for this template -->
    <link href="css/sticky-footer-navbar.css" rel="stylesheet">
    
</head>
<body>

	<!-- NAVBAR CODE BEGIN -->
	<div id="navigation"></div>
	<script src="js/nav.js"></script>
	<br>
	<!-- NAVBAR CODE END -->
	
	<div class="container">
		<h2>Here's a list of all users in our database</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Username</th>
					<th>First name</th>
					<th>Middle name</th>
					<th>Last name</th>
				</tr>
			</thead>
			<c:forEach var="element" items="${userList}">
				<tr>
					<td>${element.username}</td>
					<td>${element.fname}</td>
					<td>${element.mname}</td>
					<td>${element.lname}</td>
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