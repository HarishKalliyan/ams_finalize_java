
<%@ page contentType="text/html;charset=UTF-8" language="java"%> 

<%@ include file="registerpopup.jsp" %> 
<html> 
<head> 
<title>Register</title> 
<link rel="stylesheet" type="text/css" href="register.css"> 
<link rel="stylesheet" type="text/css" href="popup.css"> 

<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Expires", "0"); 

    // Retrieve validation error messages (if any)
    String errorMessage = (String) request.getAttribute("errorMessage");
%> 
</head> 
<body> 
	<div class="register-container"> 
		<h2>Registration</h2> 

        <% if (errorMessage != null) { %> 
		    <p id="error-message" style="color: red;"><%= errorMessage %></p> 
		<% } %> 

		<form action="RegisterServlet" method="post"> 
			<label for="userName">User Name:</label> 
			<input type="text" id="userName" name="userName" required> 

			<label for="password">Password:</label> 
			<input type="password" id="password" name="password" required>

			<label for="phone">Phone:</label> 
			<input type="number" id="phone" name="phone" required> 

			<label for="emailId">Email:</label> 
			<input type="email" id="emailId" name="emailId" required> 

			<label for="address">Address:</label> 
			<input type="text" id="address" name="address" required> 

			<label for="city">City:</label> 
			<input type="text" id="city" name="city" required> 

			<label for="state">State:</label> 
			<input type="text" id="state" name="state" required> 

			<label for="country">Country:</label> 
			<input type="text" id="country" name="country" required> 

			<label for="zipCode">Zip Code:</label> 
			<input type="number" id="zipCode" name="zipCode" required> 

			<label for="dob">Date of Birth:</label> 
			<input type="date" id="dob" name="dob" required> 

			<label for="role">Role:</label> 
			<select id="role" name="role" required> 
				<option value="Customer">Customer</option> 
				<option value="Admin">Admin</option> 
			</select> 
			<br><br> 
			<button type="submit" class="register-btn">Register</button> 
			<button type="reset" class="reset-btn">Reset</button> 
		</form> 

		<p>Already have an account? <a href="login.jsp">Login Here</a></p> 
	</div> 
</body> 
</html>


