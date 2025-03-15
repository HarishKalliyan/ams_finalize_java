<%@ page import="com.airlines.beans.Booking, com.airlines.dao.BookingDAO, java.time.LocalDate" %>
<%@ include file="user_popup.jsp" %>
 
<%
    int bookingID = Integer.parseInt(request.getParameter("bookingID"));
    Booking booking = BookingDAO.getBookingByID(bookingID);

    // Get the current date (YYYY-MM-DD format) to restrict past dates
    LocalDate today = LocalDate.now();
%>

<html>
<head>
    <title>Edit Booking</title>
    <link rel="stylesheet" type="text/css" href="edit_booking.css">
    <link rel="stylesheet" type="text/css" href="popup.css">
</head>
<body>
    <div class="edit-booking-container">
        <h2>Edit Booking</h2>
        <form action="EditBookingServlet" method="post">
            <input type="hidden" name="bookingID" value="<%= booking.getBookingID() %>">

            <div class="input-group">
                <label>Flight ID:</label>
                <input type="text" name="flightID" value="<%= booking.getFlightID() %>" readonly>
            </div>

            <div class="input-group">
                <label>No. of Seats:</label>
                <input type="number" name="noOfSeats" min="1" value="<%= booking.getNoOfSeats() %>" required>
            </div>

            <div class="input-group">
                <label>Seat Category:</label>
                <select name="seatCategory">
                    <option value="Economy" <%= booking.getSeatCategory().equals("Economy") ? "selected" : "" %>>Economy</option>
                    <option value="Business" <%= booking.getSeatCategory().equals("Business") ? "selected" : "" %>>Business</option>
                    <option value="Executive" <%= booking.getSeatCategory().equals("Executive") ? "selected" : "" %>>Executive</option>
                </select>
            </div>

            <div class="input-group">
                <label>Date of Travel:</label>
                <input type="date" name="dateOfTravel" value="<%= booking.getDateOfTravel() %>" min="<%= today %>" required>
            </div>

            <button id="saveBooking" type="submit">Save Changes</button>
            <button id="cancelEdit" type="button" onclick="history.back();">Cancel</button>
        </form>
    </div>
</body>
</html>
