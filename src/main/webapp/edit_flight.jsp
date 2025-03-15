<%@ page import="com.airlines.beans.Flight, com.airlines.dao.FlightDAO, com.airlines.beans.Carrier, com.airlines.dao.CarrierDAO" %>
<%@ include file="popup.jsp" %>
<html>
<head>
    <title>Edit Flight</title>
    <link rel="stylesheet" type="text/css" href="edit_flight.css">
</head>
<body>

<div class="edit-carrier-container">
	<h2>Edit Flight</h2>

    <%
        int flightID = Integer.parseInt(request.getParameter("flightID"));
        Flight flight = FlightDAO.getFlightById(flightID);
    %>

    <form action="EditFlightServlet" method="post">
        

        
		<div class="input-group">
		<label>Flight ID:</label> <input name="flightID" value="<%= flight.getFlightID() %>" readonly="readonly">
        
		</div>
		
		<div class="input-group">
		<label>Origin:</label> <input type="text" name="origin" value="<%= flight.getOrigin() %>" required>
        
		</div>
		
		<div class="input-group">
		<label>Destination:</label> <input type="text" name="destination" value="<%= flight.getDestination() %>" required>
        
		</div>
		
		<div class="input-group">
		<label>Air Fare:</label> <input type="number" name="airFare" value="<%= flight.getAirFare() %>" required>
        
		</div>
		
		<div class="input-group">
		<label>Economy Seats:</label> <input type="number" name="economySeats" value="<%= flight.getEconomySeats() %>" required>
        
		</div>
		
		<div class="input-group">
		<label>Business Seats:</label> <input type="number" name="businessSeats" value="<%= flight.getBusinessSeats() %>" required>
        
		</div>
		
		<div class="input-group">
		<label>Executive Seats:</label> <input type="number" name="executiveSeats" value="<%= flight.getExecutiveSeats() %>" required>

		</div>
		
        
        <button type="submit" id="saveCarrier">Update Flight</button>
        <button id="cancelEdit" type="button" onclick="history.back();">Cancel</button>
    </form>
</div>
    
</body>
</html>
