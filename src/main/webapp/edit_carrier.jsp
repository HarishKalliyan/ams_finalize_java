
<%@ page import="com.airlines.dao.CarrierDAO, com.airlines.beans.Carrier" %>
<%
    int carrierID = Integer.parseInt(request.getParameter("carrierID"));
Carrier carrier = CarrierDAO.getCarrierByID(carrierID);
%>

<html>
<head>
    <title>Edit Carrier</title>
    <link rel="stylesheet" type="text/css" href="edit_carrier.css">
</head>
<body>  
	<div class="edit-carrier-container">
    <h2>Edit Carrier</h2>
    <form action="EditCarrierServlet" method="post">
        <input type="hidden" name="carrierID" value="<%= carrier.getCarrierId() %>">
         <div class="input-group">
        <label>Carrier Name:</label>
        <input type="text" name="carrierName" value="<%= carrier.getCarrierName() %>" required><br>
  		</div>
  		<div class="input-group">
        <label>Discount for 30 Days:</label>
        <input type="number" name="discount30Days" value="<%= carrier.getDiscount30Days() %>" required><br>
		</div>
		<div class="input-group">
        <label>Discount for 60 Days:</label>
        <input type="number" name="discount60Days" value="<%= carrier.getDiscount60Days() %>" required><br>
		</div>
		<div class="input-group">
        <label>Discount for 90 Days:</label>
        <input type="number" name="discount90Days" value="<%= carrier.getDiscount90Days() %>" required><br>
		</div>
		<div class="input-group">
        <label>Bulk Booking Discount:</label>
        <input type="number" name="bulkBookingDiscount" value="<%= carrier.getBulkBookingDiscount() %>" required><br>
		</div>
		<div class="input-group">
        <label>Refund for 2 Days:</label>
        <input type="number" name="refund2Days" value="<%= carrier.getRefund2Days() %>" required><br>
		</div>
		<div class="input-group">
        <label>Refund for 10 Days:</label>
        <input type="number" name="refund10Days" value="<%= carrier.getRefund10Days() %>" required><br>
		</div>
		<div class="input-group">
        <label>Refund for 20 Days:</label>
        <input type="number" name="refund20Days" value="<%= carrier.getRefund20Days() %>" required><br>
		</div>
		<div class="input-group">
        <label>Silver User Discount:</label>
        <input type="number" name="silverUserDiscount" value="<%= carrier.getSilverUserDiscount() %>" required><br>
		</div>
		<div class="input-group">
        <label>Gold User Discount:</label>
        <input type="number" name="goldUserDiscount" value="<%= carrier.getGoldUserDiscount() %>" required><br>
		</div>
		<div class="input-group">
        <label>Platinum User Discount:</label>
        <input type="number" name="platinumUserDiscount" value="<%= carrier.getPlatinumUserDiscount() %>" required><br>
		</div>
	
        <button id="saveCarrier" type="submit">Save Changes</button>
        <button id="cancelEdit" type="button" onclick="history.back();">Cancel</button>
    </form>
	</div>
</body>

</html>

