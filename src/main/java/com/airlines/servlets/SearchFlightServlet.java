
package com.airlines.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.airlines.beans.Flight;
import com.airlines.dao.FlightDAO;

@WebServlet("/SearchFlightServlet")
public class SearchFlightServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flightID = request.getParameter("flightID");
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");

        List<Flight> flights = FlightDAO.searchFlights(flightID, origin, destination);
        request.setAttribute("flightList", flights);
        request.getRequestDispatcher("user_flight_display.jsp").forward(request, response);
    }
}
