<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Custom styles for this template -->
    <link href="css/sticky-footer-navbar.css" rel="stylesheet">
    <link href="css/profile.css" rel="stylesheet">
    
</head>
<body>

	<!-- NAVBAR CODE BEGIN -->
	<div id="navigation"></div>
	<script src="js/nav.js"></script>
	<br>
	<!-- NAVBAR CODE END -->

	<div class="container-fluid w-50 p-3">
		<form class="form-signin was-validated" action="./UpdateProfileServlet" method=POST>
				<label for="username">Username:</label>
				<input type="text" id="username" class="form-control" value="${userProfile.username}" disabled>
			<div class="form-group">
				<label for="password">Password:</label>
				<input name="password" type="text" id="password" class="form-control" value="${userProfile.password}" required>
				<div class="valid-feedback">Valid.</div>
	      		<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			<div class="form-group">
				<label for="fname">First name:</label>
				<input name="fname" type="text" id="fname" class="form-control" value="${userProfile.fname}" required>
				<div class="valid-feedback">Valid.</div>
	      		<div class="invalid-feedback">Please fill out this field.</div>
			</div>
				<label for="mname">Middle name:</label>
				<input name="mname" type="text" id="mname" class="form-control" value="${userProfile.mname}">
			<div class="form-group">	
				<label for="lname">Last name:</label>
				<input name="lname" type="text" id="lname" class="form-control" value="${userProfile.lname}" required>
				<div class="valid-feedback">Valid.</div>
	      		<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			<div class="text-center">
				<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modifyAccountModal">
					Modify account
				</button>
				<button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#deleteAccountModal">
					Delete account
				</button>
				<!-- Modal -->
				<div class="modal fade" id="modifyAccountModal" tabindex="-1" role="dialog" aria-labelledby="modifyAccountLabel" aria-hidden="true">
			  		<div class="modal-dialog modal-dialog-centered" role="document">
			    		<div class="modal-content">
			      			<div class="modal-header">
			        			<h5 class="modal-title" id="modifyAccountLabel">Modify account</h5>
			        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          				<span aria-hidden="true">&times;</span>
			        			</button>
			      			</div>
			      			<div class="modal-body">
			        			Please select 'save' to save changes made to profile.
			      			</div>
			      			<div class="modal-footer">
			        			<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        			<button type="submit" class="btn btn-primary">Save</button>
			      			</div>
			    		</div>
			  		</div>
				</div>
				<!-- Modal -->
			</div>
		</form>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="deleteAccountModal" tabindex="-1" role="dialog" aria-labelledby="deleteAccountLabel" aria-hidden="true">
  		<div class="modal-dialog modal-dialog-centered" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title" id="deleteAccountLabel">Delete account</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          				<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<div class="modal-body">
        			Are you sure you want to delete your account? All data will be deleted upon confirmation.
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        			<a href="./DeleteProfileServlet"><button type="button" class="btn btn-danger">DELETE</button></a>
      			</div>
    		</div>
  		</div>
	</div>
	<!-- Modal -->

	<!-- FOOTER CODE BEGIN -->
	<div id="footer"></div>
	<script src="js/footer.js"></script>
	<!-- FOOTER CODE END -->
	
</body>
</html>