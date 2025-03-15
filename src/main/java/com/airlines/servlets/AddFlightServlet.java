package com.airlines.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.beans.Flight;
import com.airlines.dao.FlightDAO;

/**
 * Servlet implementation class AddFlightServlet
 */
@WebServlet("/AddFlightServlet")
 public class AddFlightServlet extends HttpServlet {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int carrierID = Integer.parseInt(request.getParameter("carrierID"));
	        String origin = request.getParameter("origin");
	        String destination = request.getParameter("destination");
	        int airFare = Integer.parseInt(request.getParameter("airFare"));
	        int economySeats = Integer.parseInt(request.getParameter("economySeats"));
	        int businessSeats = Integer.parseInt(request.getParameter("businessSeats"));
	        int executiveSeats = Integer.parseInt(request.getParameter("executiveSeats"));

	        Flight flight = new Flight(0, carrierID, origin, destination, airFare, economySeats, businessSeats, executiveSeats);
	        boolean success = FlightDAO.addFlight(flight);

	        if (success) {
	            request.setAttribute("message", "Flight Added Successfully!");
	            request.setAttribute("messageType", "success");
	            request.setAttribute("redirectPage", "admin_home.jsp"); // Redirect to View Bookings page
	        } else {
	            request.setAttribute("message", "Failed to Book Ticket!");
	            request.setAttribute("messageType", "error");
	            request.setAttribute("redirectPage", "add_flights.jsp"); // Stay on booking page
	        }
	        request.getRequestDispatcher("popup.jsp").forward(request, response);
	    }
	}
