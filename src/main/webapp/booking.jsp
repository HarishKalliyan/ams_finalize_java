<%@ page import="java.util.List, com.airlines.beans.Flight, com.airlines.dao.FlightDAO, java.time.LocalDate" %>
<%@ include file="user_header.jsp" %>
<%@ include file="user_menu.jsp" %>

<%@ include file="user_popup.jsp" %>
<html>
<head>
    <title>Book a Ticket</title>
    <style>
        .container {
            text-align: center;
            margin-top: 50px;
        }

        select, input {
            padding: 10px;
            margin: 10px;
            width: 200px;
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
        }

        #backButton:hover {
            background-color: darkred;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="popup.css">
</head>
<body>
    <div class="container">
        <h2>Book Your Flight</h2>

        <%
            // Get the current date in "YYYY-MM-DD" format using Java (No JavaScript)
            LocalDate today = LocalDate.now();
        %>

        <form action="BookingServlet" method="post">
            <label for="flightID">Select Flight:</label>
            <select name="flightID" required>
                <option value="">-- Select Flight --</option>
                <%
                    List<Flight> flights = FlightDAO.getAllFlights();
                    for (Flight flight : flights) {
                %>
                <option value="<%= flight.getFlightID() %>">
                    <%= flight.getOrigin() %> to <%= flight.getDestination() %> - $<%= flight.getAirFare() %>
                </option>
                <% } %>
            </select>
            <br>

            <label for="noOfSeats">Number of Seats:</label>
            <input type="number" name="noOfSeats" min="1" required>
            <br>

            <label for="seatCategory">Seat Category:</label>
            <select name="seatCategory" required>
                <option value="Economy">Economy</option>
                <option value="Executive">Executive</option>
                <option value="Business">Business</option>
            </select>
            <br>

            <label for="dateOfTravel">Date of Travel:</label>
            <!-- Using Java's LocalDate to set the min date -->
            <input type="date" name="dateOfTravel" min="<%= today %>" required>
            <br>

            <button type="submit">Book Ticket</button>
        </form>
    </div>
    <button id="backButton" onclick="history.back()">Back</button>
</body>
<%@ include file="user_footer.jsp" %>
</html>
