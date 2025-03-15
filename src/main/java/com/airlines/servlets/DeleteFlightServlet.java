package com.airlines.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.dao.FlightDAO;

/**
 * Servlet implementation class DeleteFlightServlet
 */
@WebServlet("/DeleteFlightServlet")
public class DeleteFlightServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flightID = Integer.parseInt(request.getParameter("flightID"));
        boolean success = FlightDAO.deleteFlight(flightID);
        
        
        if (success) {
            request.setAttribute("message", "Flight Deleted Successfully!");
            request.setAttribute("messageType", "success");
            request.setAttribute("redirectPage", "admin_home.jsp"); // Redirect back to bookings page
        } else {
            request.setAttribute("message", "Failed to Delete Flight!");
            request.setAttribute("messageType", "error");
            request.setAttribute("redirectPage", "admin_home.jsp"); // Stay on bookings page
        }
        request.getRequestDispatcher("popup.jsp").forward(request, response);
        
    }
}
