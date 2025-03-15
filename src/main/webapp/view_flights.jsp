<%@ page import="java.util.List, com.airlines.beans.Flight, com.airlines.dao.FlightDAO, com.airlines.beans.Carrier, com.airlines.dao.CarrierDAO" %>
<%@ include file="header.jsp" %>
<%@ include file="admin_menu.jsp"%>
<%@ include file="popup.jsp" %>
<html>
<head>
    <title>View Flights</title>    <link rel="stylesheet" type="text/css" href="trips.css">
    <style type="text/css">
    
    .delete , .edit{
	width: 150px;
	text-align: center;
	
	font-weight: bold;
}

.delete:hover{
	background-color: maroon;
	
}

#backButton {
  position: fixed;
  bottom: 60px;
  right: 2px;
  background-color: crimson;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}
style</style>
</head>
<body>
    <h2>Flight Management</h2>

    <h3>All Flights</h3>
    <table border="1" style="margin-bottom: 130px; width:90%;">
        <tr id="header-back" style="background: #007bff;">
            <th>Flight ID</th>
            <th>Carrier ID</th>
            <th>Carrier Name</th>
            <th>Origin</th>
            <th>Destination</th>
            <th>Air Fare ($)</th>
            <th>Economy Seats</th>
            <th>Business Seats</th>
            <th>Executive Seats</th>
            <th>Actions</th>
        </tr>
        <% 
            List<Flight> flights = FlightDAO.getAllFlights();
            for (Flight flight : flights) { 
        %>
        
        <tr>
            <td><%= flight.getFlightID() %></td>
            <td><%= flight.getCarrierID() %></td>
            <td><%= flight.getCarrierName() %></td>
            <td><%= flight.getOrigin() %></td>
            <td><%= flight.getDestination() %></td>
            <td><%= flight.getAirFare() %></td>
            <td><%= flight.getEconomySeats() %></td>
            <td><%= flight.getBusinessSeats() %></td>
            <td><%= flight.getExecutiveSeats() %></td>
            <td>
                <a href="edit_flight.jsp?flightID=<%= flight.getFlightID() %>"><button class="edit">Edit</button></a>
                <a href="DeleteFlightServlet?flightID=<%= flight.getFlightID() %>"  onclick="return confirm('Are you sure you want to delete this flight?');">
                    <button class="delete" style="background: red;">Delete</button>
                </a>
            </td>
        </tr>
        <% } %>
    </table>
    <button id="backButton" onclick="history.back()" 	>Back</button>
</body>
<%@ include file="footer.jsp"%>
</html>
