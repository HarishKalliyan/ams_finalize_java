<%@ page import="com.airlines.beans.User" %>
<%@ include file="user_header.jsp" %>
<%@ include file="user_menu.jsp" %>
<html>
<%
    User user = (User) request.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>User Details</title>
    <style>
        .container {
            width: 50%;
            margin: auto;
            padding: 20px;
            margin-bottom: 100px;
            background: white;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
        }
        h2 {
            text-align: center;
        }
        .info {
            font-size: 18px;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .info strong {
            color: #333;
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
        <h2>Logged-in User Details</h2>

        <div class="info"><strong>User ID:</strong> <%= user.getUserID() %></div>
        <div class="info"><strong>Name:</strong> <%= user.getUserName() %></div>
        <div class="info"><strong>Email:</strong> <%= user.getEmailId() %></div>
        <div class="info"><strong>Phone:</strong> <%= user.getPhone() %></div>
        <div class="info"><strong>Role:</strong> <%= user.getRole() %></div>
        <div class="info"><strong>Address:</strong> <%= user.getAddress() %></div>
        <div class="info"><strong>City:</strong> <%= user.getCity() %></div>
        <div class="info"><strong>State:</strong> <%= user.getState() %></div>
        <div class="info"><strong>Country:</strong> <%= user.getCountry() %></div>
        <div class="info"><strong>ZipCode:</strong> <%= user.getZipCode() %></div>
        <div class="info"><strong>Date of Birth:</strong> <%= user.getDob() %></div>

       <%--  <% if ("Customer".equals(user.getRole())) { %>
            <div class="info"><strong>Customer Category:</strong> <%= user.getCustomerCategory() %></div>
        <% } %> --%>
    </div>
    <button id="backButton" onclick="history.back()" 	>Back</button>
</body>
<%@ include file="user_footer.jsp" %>
</html>
