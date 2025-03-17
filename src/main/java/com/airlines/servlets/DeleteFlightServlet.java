
package com.airlines.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.dao.FlightDAO;

@WebServlet("/DeleteFlightServlet")
public class DeleteFlightServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flightID = Integer.parseInt(request.getParameter("flightID"));

        // Check if there are active bookings
        if (FlightDAO.hasActiveBookings(flightID)) {
            request.setAttribute("message", "Failed to Delete Flight! Active bookings exist.");
            request.setAttribute("messageType", "error");
            request.setAttribute("redirectPage", "admin_home.jsp"); // Redirect back to admin panel
        } else {
            boolean success = FlightDAO.deleteFlight(flightID);
            
            if (success) {
                request.setAttribute("message", "Flight Deleted Successfully!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to Delete Flight!");
                request.setAttribute("messageType", "error");
            }
            request.setAttribute("redirectPage", "admin_home.jsp"); // Redirect back to admin panel
        }

        request.getRequestDispatcher("popup.jsp").forward(request, response);
    }
}