
<%@ include file="header.jsp"%>

<%@ include file="admin_menu.jsp"%>

<html>
<head>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    // Redirect to login if session is invalid
    if (session.getAttribute("userID") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<title>Admin Home</title>
<!-- <link rel="stylesheet" type="text/css" href="home.css"> -->
<style>
.container {
	text-align: center;
	margin-top: 50px;
}

button {
	background: dodgerblue;
	color: white;
	padding: 12px 20px;
	border: none;
	width: 20%;
	border-radius: 5px;
	font-size: 18px;
	cursor: pointer;
	margin: 10px;
}

button:hover {
	background: darkblue;
}


#backButton {
  position: fixed;
  bottom: 60px;
  right: 20px;
  width: 5%;
  background-color: crimson;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

#backButton:hover {
  background-color: darkred;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Welcome, Admin</h2>

		<div id="search-section">
			<form action="view_flights.jsp">
				<button type="submit">Get Flight Details</button>
			</form>
			<form action="add_flights.jsp">
			<button type="submit" id='addflights'>Add Flight</button>
			</form>
			<form action="add_carrier.jsp">
			<button type="submit" id='addcarriers'>Add Carrier</button>
			</form>
			<form action="CarrierServlet">
			<button type="submit" id='viewcarrier'>View Carriers</button>
			</form>
			
		</div>
	</div>
	<button id="backButton" onclick="history.back()" >Back</button>
	
</body>

<%@ include file="footer.jsp"%>
</html>


