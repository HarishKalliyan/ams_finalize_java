<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="popup.jsp" %>
<html>
<head>
    <title>Login</title>
    
    <%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>
	<link rel="stylesheet" type="text/css" href="popup.css">
    <link rel="stylesheet" type="text/css" href="login.css">
    <script>
    window.onload = function () {
        document.forms[0].reset(); // Clears the form fields
    };
	</script>
    
    <script>
        // Prevent back navigation after logout
        history.pushState(null, null, location.href);
        window.onpopstate = function () {
            history.go(1);
        };
    </script>
</head>
<body>
    <div class="login-container">
        <form action="LoginPageServlet" method="post">
            <label for="userID">User ID:</label>
            <input type="text" id="userID" name="userID" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Login</button>
        </form>
        
    <p>Don't have an account? <a href="register.jsp">Register Here</a></p>
    </div>
    
</body>
</html>