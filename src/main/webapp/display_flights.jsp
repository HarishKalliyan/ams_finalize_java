
<%@ page import="java.util.List, com.airlines.beans.Flight"%>



<%@ include file="header.jsp"%>
<%@ include file="user_menu.jsp"%>
<html>
<head>
<title>Flight Details</title>
<style type="text/css">
/* Flight Search Form */
.search-form {
    width: 80%;
    margin: 20px auto;
    padding: 15px;
    background: #f8f9fa;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: space-around;
    align-items: center;
    flex-wrap: wrap;
}

.search-form label {
    font-weight: bold;
    margin-right: 10px;
}

.search-form input {
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    width: 200px;
}

.search-form button {
    background-color: #007bff;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
}

.search-form button:hover {
    background-color: #0056b3;
}

/* Flight Table Styling */
.flight-table {
    width: 90%;
    margin-bottom: 120px;
    margin-left: 80px;
    border-collapse: collapse;
    background: white;
    color: black;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    border-radius: 8px;
    overflow: hidden;
}

.flight-table th,
.flight-table td {
    padding: 12px;
    text-align: center;
    border-bottom: 1px solid #ddd;
}

.flight-table th {
    background-color: teal;
    color: white;
    font-weight: bold;
}

.flight-table tr:hover {
    background-color: #f1f1f1;
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
	
<!-- Flight Search Form -->
<form action="SearchFlightServlet" method="GET" class="search-form">
    <label for="flightID">Flight ID:</label>
    <input type="text" id="flightID" name="flightID" placeholder="Enter Flight ID...">

    <label for="origin">Origin:</label>
    <input type="text" id="origin" name="origin" placeholder="Enter Origin...">

    <label for="destination">Destination:</label>
    <input type="text" id="destination" name="destination" placeholder="Enter Destination...">

    <button type="submit">Search</button>
</form>

<!-- Flight Results Table -->
<table class="flight-table">
    <tr>
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
        if (request.getAttribute("flightList") != null) {
            List<Flight> flights = (List<Flight>) request.getAttribute("flightList");
            if (flights.isEmpty()) { 
    %>
        <tr><td colspan="9" style="text-align: center; color: red;">No flights found!</td></tr>
    <% 
            } else { 
                for (Flight flight : flights) { 
    %>
        <tr>
            <td><%= flight.getFlightID() %></td>
            <td><%= flight.getCarrierID() %></td>
            <td><%= flight.getCarrierName() %></td>
            <td><%= flight.getOrigin() %></td>
            <td><%= flight.getDestination() %></td>
            <td>$<%= flight.getAirFare() %></td>
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
    <% 
                }
            }
        }
else { %>
        
        
        <p class="no-data">No flight details available.</p>
    
    
    <% } %>

</table>

<!-- Back Button -->
<button id="backButton" onclick="history.back()">Back</button>

</body>


<%@ include file="footer.jsp"%>
</html>




