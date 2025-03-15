
<%@ page import="java.util.List, com.airlines.beans.Carrier" %>
<%@ include file="header.jsp" %>
<%@ include file="admin_menu.jsp"%>
<html>
<head>
<title>Carrier Details</title>
    <link rel="stylesheet" type="text/css" href="trips.css">
    <style type="text/css">
    .delete , .edit{
	width: 80px;
	text-align: center;
	padding-right: 100px;
	font-weight: bold;
}

.delete:hover{
	background-color: maroon;
	
}
    </style>
</head>
<body>
    <h2>Carrier Details</h2>
    <%
        Object obj = request.getAttribute("carrierList");
        List<Carrier> carriers = null;
        if (obj instanceof List<?>) {
            carriers = (List<Carrier>) obj;
        }
        if (carriers != null && !carriers.isEmpty()) {
    %>
 
    <table style="margin-bottom: 130px">
        <tr id="header-back" style="background: #007bff;">
            <th>CarrierID</th>
            <th>CarrierName</th>
            <th>Discount For 30Days</th>
            <th>Discount For 60Days</th>
            <th>Discount For 90Days</th>
            <th>Bulk Booking Discount</th>
            <th>Refund For 2Days</th>
            <th>Refund For 10Days</th>
            <th>Refund For 20Days</th>
            <th>Silver User Discount</th>
            <th>Gold User Discount</th>
            <th>Platinum UserDiscount</th>
            <th>Actions</th>
           
        </tr>
        <%
            for (Carrier carrier : carriers) {
        %>
        <tr>
            <td><%= carrier.getCarrierId()%></td>
            <td><%= carrier.getCarrierName() %></td>
            <td><%= carrier.getDiscount30Days() %></td>
            <td><%= carrier.getDiscount60Days() %></td>
            <td><%= carrier.getDiscount90Days() %></td>
            <td><%= carrier.getBulkBookingDiscount() %></td>
            <td><%= carrier.getRefund2Days() %></td>
            <td><%= carrier.getRefund10Days()%></td>
            <td><%= carrier.getRefund20Days()%></td>
            <td><%= carrier.getSilverUserDiscount()%></td>
            <td><%= carrier.getGoldUserDiscount()%></td>
            <td><%= carrier.getPlatinumUserDiscount() %></td>
              <td>
            <a  href="edit_carrier.jsp?carrierID=<%= carrier.getCarrierId() %>">
                <button class="edit">Edit</button>
            </a>
            <br>
            
            <a href="DeleteCarrierServlet?carrierID=<%= carrier.getCarrierId() %>" 
               onclick="return confirm('Are you sure you want to delete this carrier?');">
                <button class="delete"style="background: red;">Delete</button>
            </a>
        </td>
        </tr>
        <%
            }
        %>
    </table>

    <% } else { %>
        <p class="no-data">No carrier details available.</p>
    <% } %>

    <button id="backButton" onclick="history.back()" 	>Back</button>

</body>


<%@ include file="footer.jsp"%>
</html>

 
 