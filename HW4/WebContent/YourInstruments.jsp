<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Instruments</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Custom styles for this template -->
    <link href="css/sticky-footer-navbar.css" rel="stylesheet">
    
</head>
<body>

		<%
			String didNotAdd = "";
			Object error = request.getAttribute("didNotAdd");
			if(error!=null) didNotAdd=error.toString();
		%>

	<!-- NAVBAR CODE BEGIN -->
	<div id="navigation"></div>
	<script src="js/nav.js"></script>
	<br>
	<!-- NAVBAR CODE END -->
	
	<div class="text-center">
		<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addInstrumentModal">
			Add an instrument
		</button>
			<%
    		if(error!=null) {
    			JspWriter outWrite = null;
    			out.write("<div class=\"alert alert-danger\" role=\"alert\">");
    			out.write(didNotAdd);
    			out.write("</div>");
    		}
    		%>
	</div>
	
	<div class="container">
		<h2>Here are all instruments you own</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Category</th>
				</tr>
			</thead>
			<c:forEach var="element" items="${instrumentList}">
				<tr>
					<td>${element.name}</td>
					<td>${element.category}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<!-- BEGIN MODAL -->
	<div class="modal fade" id="addInstrumentModal" tabindex="-1" role="dialog" aria-labelledby="albumInstrumentLabel" aria-hidden="true">
  		<div class="modal-dialog modal-dialog-centered" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title" id="modelInstrumentLabel">Add an instrument</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          				<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<div class="container-fluid">
			  		<form class="was-validated" action="./AddInstrumentServlet" method=GET>
			    		<div class="form-group">
			      			<label for="instrumentname">Instrument name:</label>
			      			<input type="text" class="form-control" id="instrumentname" placeholder="Enter instrument name" name="instrumentname" required>
			      			<div class="valid-feedback">Valid.</div>
			      			<div class="invalid-feedback">Please fill out this field.</div>
			    		</div>
			    		<div class="form-group">
			      			<label for="artistname">Category:</label>
							<select class="form-control" id="category" name="category">
								<option>String</option>
								<option>Woodwind</option>
								<option>Brass</option>
								<option>Percussion</option>
								<option>Keyboard</option>
								<option>Digital</option>
							</select>
			    		</div>
			    		<div class="text-center">
			    			<button style="margin-bottom: 10px;" type="submit" class="btn btn-primary">Submit</button>
			    		</div>
			  		</form>
		  		</div>
    		</div>
  		</div>
	</div>
	<!-- END MODAL -->

	<!-- FOOTER CODE BEGIN -->
	<div id="footer"></div>
	<script src="js/footer.js"></script>
	<!-- FOOTER CODE END -->
	
</body>
</html>