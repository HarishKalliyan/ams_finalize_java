<%@ page session="true" %>
<%
    if (session.getAttribute("userID") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Header</title>
</head>
<body>
    <div style="background: teal; color: white; padding: 10px;">
        <h2>Welcome, <%= (session.getAttribute("userName") != null) ? session.getAttribute("userName") : "Guest" %>!</h2>
        
    </div>
    <hr>
</body>
</html>
