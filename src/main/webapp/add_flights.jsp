<%@ page import="java.util.List, com.airlines.beans.Flight, com.airlines.dao.FlightDAO, com.airlines.beans.Carrier, com.airlines.dao.CarrierDAO" %>
<%@ include file="popup.jsp"%>
<html>
<head>
<title>Add Flights Details</title>
<link rel="stylesheet" type="text/css" href="add_flights.css">
<style type="text/css">
<style>
        .container {
            text-align: center;
            margin-top: 50px;
        }

        select, input {
            padding: 10px;
            margin: 10px;
            margin-bottom: 15px;
        }

        button {
            background: dodgerblue;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
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
            width: 5%;
        }

        #backButton:hover {
            background-color: darkred;
        }
</style>

</head>
<body>
<div class="add-flight-container">
<h2>Add Flights Details</h2>

	<form action="AddFlightServlet" method="post">
		<label>Carrier:</label>
		<br>
        <select name="carrierID" required>
            <option value="">-- Select Carrier --</option>
            <% 
                List<Carrier> carriers = CarrierDAO.getAllCarriers();
                for (Carrier carrier : carriers) { 
            %>
                <option value="<%= carrier.getCarrierId() %>">
                    <%= carrier.getCarrierName() %>
                </option>
            <% } %>
        </select>
        <br>
			<label>Origin:</label> <input type="text"	name="origin" required> 
			<label>Destination:</label> <input	type="text" name="destination" required> 
			<label>AirFare:</label> <input type="number" name="airFare" required> 
			<label>Economy	Seats:</label> <input type="number" name="economySeats" required> 
			<label>Business	Seats:</label> <input type="number" name="businessSeats" required> 
			<label>Executive Seats:</label> <input type="number" name="executiveSeats" required>
		<button type="submit">Add Flight</button>
	</form>
</div>
	<button id="backButton" onclick="history.back()">Back</button>
</body>
</html>
