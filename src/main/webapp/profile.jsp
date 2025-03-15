<%@ page import="com.airlines.beans.User" %>
<%@ include file="header.jsp" %>
<%
    // Get user object from session
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>User Profile</title>
    <style>
        .profile-container {
            width: 50%;
            margin: auto;
            padding: 20px;
            background: white;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
        }
        .input-group {
            margin-bottom: 10px;
        }
        label {
            font-weight: bold;
        }
        input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background: dodgerblue;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background: darkblue;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <h2>My Profile</h2>

        <form action="EditProfileServlet" method="post">
            <div class="input-group">
                <label for="userID">User ID:</label>
                <input type="text" id="userID" name="userID" value="<%= user.getUserID() %>" readonly>
            </div>

            <div class="input-group">
                <label for="userName">User Name:</label>
                <input type="text" id="userName" name="userName" value="<%= user.getUserName() %>" required>
            </div>

            <div class="input-group">
                <label for="emailId">Email:</label>
                <input type="email" id="emailId" name="emailId" value="<%= user.getEmailId() %>" required>
            </div>

            <div class="input-group">
                <label for="phone">Phone:</label>
                <input type="number" id="phone" name="phone" value="<%= user.getPhone() %>" required>
            </div>

            <div class="input-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="<%= user.getAddress() %>" required>
            </div>

            <div class="input-group">
                <label for="city">City:</label>
                <input type="text" id="city" name="city" value="<%= user.getCity() %>" required>
            </div>

            <div class="input-group">
                <label for="state">State:</label>
                <input type="text" id="state" name="state" value="<%= user.getState() %>" required>
            </div>

            <div class="input-group">
                <label for="zipCode">Zip Code:</label>
                <input type="number" id="zipCode" name="zipCode" value="<%= user.getZipCode() %>" required>
            </div>

            <button type="submit">Save Changes</button>
        </form>

        <% String message = request.getParameter("message"); 
           if (message != null) { %>
           <p style="color: green;"><%= message %></p>
        <% } %>
    </div>
</body>
<%@ include file="footer.jsp" %>
</html>
