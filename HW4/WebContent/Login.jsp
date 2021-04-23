<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
    
    <!-- Custom JavaScript for this page -->
    <script src="js/createUser.js"></script>

</head>
<body class="text-center">

	<div class="container">
	<!-- NAVBAR CODE END -->
	
		<%
		String badLogin = "";
		Object error = request.getAttribute("error");
		if(error!=null) badLogin=error.toString();
		%>
	
    	<form class="form-signin" action="./LoginServlet" method=POST>
    		<h1>Welcome to Musicians' Music</h1>
      		<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      		<label for="inputUsername" class="sr-only">Username</label>
      		<input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
      		<label for="inputPassword" class="sr-only">Password</label>
      		<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
      		<button class="btn btn-primary" type="submit">Sign in</button>
    	</form>
    	<a href="" data-toggle="modal" data-target="#createAccountModal">Create an account</a>
    	<%
    		if(error!=null) {
    			JspWriter outWrite = null;
    			out.write("<div class=\"alert alert-danger\" role=\"alert\">");
    			out.write(badLogin);
    			out.write("</div>");
    		}
    		%>
	</div>
	
	<!-- BEGIN MODAL -->
	<div class="modal fade" id="createAccountModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  		<div class="modal-dialog modal-dialog-centered" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title" id="createaccountlLabel">Create an account</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          				<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<form class="form-signin" action="./CreateAccountServlet" method=POST>
      				<label for="newUsername" class="sr-only">Username</label>
      				<input type="text" id="newUser" name="newUser" class="form-control" placeholder="Username" required autofocus>
      				
      				<label for="inputFirstName" class="sr-only">First name</label>
      				<input type="text" id="fName" name="fName" class="form-control" placeholder="First name" required autofocus>
      				
      				<label for="inputMiddleName" class="sr-only">First name</label>
      				<input type="text" id="mName" name="mName" class="form-control" placeholder="Middle name" autofocus>
      				
      				<label for="inputLastName" class="sr-only">First name</label>
      				<input type="text" id="lName" name="lName" class="form-control" placeholder="Last name" required autofocus>
      				
      				<label for="newPassword" class="sr-only">Password</label>
      				<input type="text" id="newPassword" name="newPassword" class="form-control" placeholder="Password" required>
      				
      				<label for="confirmPassword" class="sr-only">Confirm password</label>
      				<input type="text" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Confirm password" required>
      				
      				<div class="modal-footer">
        				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        				<button id="createAccount" type="submit" class="btn btn-primary">Create account</button>
      				</div>
      			</form>
      			<div id="divCheckPassword"></div>
    		</div>
  		</div>
	</div>
	<!-- END MODAL -->
</body>
</html>