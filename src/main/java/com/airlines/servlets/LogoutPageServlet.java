package com.airlines.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutPageServlet")
public class LogoutPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Destroy session
        }

        // Prevent caching so user cannot go back
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        // Show logout success pop-up
        request.setAttribute("message", "You have been logged out successfully!");
        request.setAttribute("messageType", "success");
        request.setAttribute("redirectPage", "login.jsp");

        request.getRequestDispatcher("popup.jsp").forward(request, response);
    }
}
