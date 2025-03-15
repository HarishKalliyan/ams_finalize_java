package com.airlines.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.airlines.dao.*;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        if (userID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int flightID = Integer.parseInt(request.getParameter("flightID"));
        int noOfSeats = Integer.parseInt(request.getParameter("noOfSeats"));
        String seatCategory = request.getParameter("seatCategory");
        String dateOfTravel = request.getParameter("dateOfTravel");

        int bookingAmount = BookingDAO.calculateBookingAmount(flightID, seatCategory, noOfSeats);
        boolean success = BookingDAO.insertBooking(flightID, userID, noOfSeats, seatCategory, dateOfTravel, bookingAmount);

        if (success) {
            request.setAttribute("message", "Flight Ticket Booked Successfully!");
            request.setAttribute("messageType", "success");
            request.setAttribute("redirectPage", "user_home.jsp"); // Redirect to View Bookings page
        } else {
            request.setAttribute("message", "Failed to Book Ticket!");
            request.setAttribute("messageType", "error");
            request.setAttribute("redirectPage", "booking.jsp"); // Stay on booking page
        }
        request.getRequestDispatcher("popup.jsp").forward(request, response);
    }
}
