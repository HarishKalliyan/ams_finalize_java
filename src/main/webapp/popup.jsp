<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String message = (String) request.getAttribute("message");
    String messageType = (String) request.getAttribute("messageType"); // success or error
    String redirectPage = (String) request.getAttribute("redirectPage"); // Page to redirect after popup

    if (message != null) {
%>
    <link rel="stylesheet" type="text/css" href="popup.css">
    
    <div class="popup-container">
        <div class="popup-box" style="border: 2px solid <%= "success".equals(messageType) ? "green" : "red" %>;">
            <h3 style="color: <%= "success".equals(messageType) ? "green" : "red" %>;">
                <%= message %>
            </h3>
            <button onclick="window.location.href='<%= redirectPage != null ? redirectPage : "login.jsp" %>';">OK</button>
        </div>
    </div>
<% } %>
