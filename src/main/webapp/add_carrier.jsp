
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Carrier Details</title>
    <link rel="stylesheet" type="text/css" href="add_carrier.css">
</head>
<body>
    <div class="add-carrier-container">
        <h2>Add Carrier Details</h2>
        <form action="AddCarrierServlet" method="post" class="form-container">
            <!-- Hidden field to indicate add operation -->
            <input type="hidden" name="action" value="new" />
            
            <!-- Carrier Name -->
            <label for="CarrierName">Carrier Name:</label>
            <input type="text" id="CarrierName" name="CarrierName" maxlength="50" required/><br/>
          	
          	
          	<h3>Discount Percentage</h3><hr>
            <label for="Days30AdvanceBooking">30 Days Advance Booking:</label>
            <input type="number" id="Days30AdvanceBooking" name="Days30AdvanceBooking" required/><br/>
            
            <label for="Days60AdvanceBooking">60 Days Advance Booking:</label>
            <input type="number" id="Days60AdvanceBooking" name="Days60AdvanceBooking" required/><br/>
            
            <label for="Days90AdvanceBooking">90 Days Advance Booking:</label>
            <input type="number" id="Days90AdvanceBooking" name="Days90AdvanceBooking" required/><br/>
            
            <label for="BulkBooking">Bulk Booking:</label>
            <input type="number" id="BulkBooking" name="BulkBooking" required/><br/>
            
            <label for="SilverUser">Silver User:</label>
            <input type="number" id="SilverUser" name="SilverUser" required/><br/>
            
            <label for="GoldUser">Gold User:</label>
            <input type="number" id="GoldUser" name="GoldUser" required/><br/>
            
            <label for="PlatinumUser">Platinum User:</label>
            <input type="number" id="PlatinumUser" name="PlatinumUser" required/><br/>
            
 			<h3>Refund Percentage</h3><hr>
            <label for="Days2BeforeTravelDate">2 Days Before Travel Date:</label>
            <input type="number" id="Days2BeforeTravelDate" name="Days2BeforeTravelDate" required/><br/>
            
            <label for="Days10BeforeTravelDate">10 Days Before Travel Date:</label>
            <input type="number" id="Days10BeforeTravelDate" name="Days10BeforeTravelDate" required/><br/>
            
            <label for="Days20BeforeTravelDate">20 Days Before Travel Date:</label>
            <input type="number" id="Days20OrMoreBeforeTravelDate" name="Days20OrMoreBeforeTravelDate" required/><br/>
            
            <div class="button-container">
                <input type="submit" value="Add Carrier" />
                <button id="backButton" onclick="history.back()">Back</button>
            </div>
        </form>
        
        <!-- Display success message if available -->
        <%
           String message = (String) request.getAttribute("message");
           if(message != null) {
        %>
            <h3><%= message %></h3>
        <%
           }
        %>
    </div>
</body>
</html>