<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            text-align: center;
            background-color: #f8d7da;
            color: #721c24;
            font-family: Arial, sans-serif;
        }
        .container {
            margin-top: 100px;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            display: inline-block;
        }
        h1 {
            color: #721c24;
        }
        button {
            background-color: dodgerblue;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            margin-top: 10px;
        }
        button:hover {
            background-color: darkblue;
        }
    </style>
    <script>
        // Prevent back button from working
        history.pushState(null, null, location.href);
        window.onpopstate = function () {
            history.go(1);
        };
    </script>
</head>
<body>
    <div class="container">
        <h1>Oops! Something went wrong.</h1>
        <p>We're sorry, but an unexpected error occurred.</p>
        
        <%
            Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
            String errorMessage = (String) request.getAttribute("jakarta.servlet.error.message");

            if (statusCode != null) {
        %>
            <p><strong>Error Code:</strong> <%= statusCode %></p>
            <p><strong>Message:</strong> <%= (errorMessage != null) ? errorMessage : "Unexpected error" %></p>
        <% } %>

        <button onclick="window.location.href='login.jsp'">Go to Login</button>
    </div>
</body>
</html>
