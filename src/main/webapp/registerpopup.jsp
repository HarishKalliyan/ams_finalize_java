<%
    String message = (String) request.getAttribute("message");
    String messageType = (String) request.getAttribute("messageType"); // success or error
    if (message != null) {
%>
    <div class="popup-container">
        <div class="popup-box" style="border: 2px solid <%= "success".equals(messageType) ? "green" : "red" %>;">
            <h3 style="color: <%= "success".equals(messageType) ? "green" : "red" %>;">
                <%= message %>
            </h3>
            <button onclick="window.location.href='login.jsp';">OK</button>
        </div>
    </div>
<% } %>



