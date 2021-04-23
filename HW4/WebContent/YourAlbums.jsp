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
		<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addAlbumModal">
			Add an album
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
		<h2>Here are all albums you own</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Musician</th>
					<th>Genre</th>
					<th>Media</th>
				</tr>
			</thead>
			<c:forEach var="element" items="${albumList}">
				<tr>
					<td>${element.name}</td>
					<td>${element.artist}</td>
					<td>${element.genre}</td>
					<td>${element.media}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<!-- BEGIN MODAL -->
	<div class="modal fade" id="addAlbumModal" tabindex="-1" role="dialog" aria-labelledby="albumModelLabel" aria-hidden="true">
  		<div class="modal-dialog modal-dialog-centered" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title" id="modelAlbumLabel">Add an album</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          				<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<div class="container-fluid">
	  				<form class="was-validated" action="./AddAlbumServlet" method=GET>
	    				<div class="form-group">
	      					<label for="albumname">Album name:</label>
	      					<input type="text" class="form-control" id="albumname" placeholder="Enter album name" name="albumname" required>
	      					<div class="valid-feedback">Valid.</div>
	      					<div class="invalid-feedback">Please fill out this field.</div>
	    				</div>
	    				<div class="form-group">
	      					<label for="artistname">Artist name:</label>
	      					<input type="text" class="form-control" id="artistname" placeholder="Enter artist name" name="artistname" required>
	      					<div class="valid-feedback">Valid.</div>
	      					<div class="invalid-feedback">Please fill out this field.</div>
	    				</div>
	    				<div class="form-group">
	      					<label for="genre">Genre:</label>
								<select class="form-control" id="genre" name="genre">
								<option>Electronica</option>
								<option>Classical</option>
								<option>Rock</option>
								<option>Jazz</option>
								<option>Blues</option>
								<option>Country</option>
								<option>Pop</option>
								<option>Cinema</option>
								<option>Funk</option>
								<option>Hip hop</option>
								<option>Orchestra</option>
								<option>Alternative</option>
							</select>
			    		</div>
			    		<div class="form-group">
			      			<label for="media">Media:</label>
							<select class="form-control" id="media" name="media">
								<option>Digital</option>
								<option>CD</option>
								<option>Tape</option>
								<option>Vinyl</option>
							</select>
			    		</div>
			    		<div class="form-group">
			      			<label for="instrument">Instrument:</label>
			      			<input type="text" class="form-control" id="instrument" placeholder="Enter instrument" name="instrument" required>
			      			<div class="valid-feedback">Valid.</div>
			      			<div class="invalid-feedback">Please fill out this field.</div>
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