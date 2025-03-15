<%@ include file="user_header.jsp" %>
<%@ include file="user_menu.jsp" %>
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

    <title>User Home</title>
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
        <h2>Welcome to the Airline Management System</h2>
        <p>Please choose an option below:</p>

        <a href="User_FlightServlet"><button>View Flight Details</button></a>
        <a href="booking.jsp"><button>Book a Ticket</button></a>
    </div>
     <button id="backButton" onclick="history.back()" 	>Back</button>
	
</body>
<%@ include file="user_footer.jsp" %>
</html>
