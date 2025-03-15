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
 * Servlet implementation class EditFlightServlet
 */
@WebServlet("/EditFlightServlet")
public class EditFlightServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flightID = Integer.parseInt(request.getParameter("flightID"));
        
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        int airFare = Integer.parseInt(request.getParameter("airFare"));
        int economySeats = Integer.parseInt(request.getParameter("economySeats"));
        int businessSeats = Integer.parseInt(request.getParameter("businessSeats"));
        int executiveSeats = Integer.parseInt(request.getParameter("executiveSeats"));

        Flight flight = new Flight(flightID, origin, destination, airFare, economySeats, businessSeats, executiveSeats);
        boolean success = FlightDAO.updateFlight(flight);

        if (success) {
            request.setAttribute("message", "Flight Updated Successfully!");
            request.setAttribute("messageType", "success");
            request.setAttribute("redirectPage", "admin_home.jsp"); // Redirect to View Bookings page
        } else {
            request.setAttribute("message", "Failed to Update Flight!");
            request.setAttribute("messageType", "error");
            request.setAttribute("redirectPage", "edit_flight.jsp?flightID=" + flightID); // Stay on edit page
        }
        request.getRequestDispatcher("popup.jsp").forward(request, response);
    }
}
