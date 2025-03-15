package com.airlines.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.airlines.dao.BookingDAO;

@WebServlet("/EditBookingServlet")
public class EditBookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        int noOfSeats = Integer.parseInt(request.getParameter("noOfSeats"));
        int flightID = Integer.parseInt(request.getParameter("flightID"));
        String seatCategory = request.getParameter("seatCategory");
        String dateOfTravel = request.getParameter("dateOfTravel");
        int bookingAmount = BookingDAO.calculateBookingAmount(flightID, seatCategory, noOfSeats);

        boolean success = BookingDAO.updateBooking(bookingID, bookingAmount, noOfSeats, seatCategory, dateOfTravel);

        if (success) {
            request.setAttribute("message", "Booking Updated Successfully!");
            request.setAttribute("messageType", "success");
            request.setAttribute("redirectPage", "user_home.jsp"); // Redirect to View Bookings page
        } else {
            request.setAttribute("message", "Failed to Update Booking!");
            request.setAttribute("messageType", "error");
            request.setAttribute("redirectPage", "edit_booking.jsp?bookingID=" + bookingID); // Stay on edit page
        }
        request.getRequestDispatcher("popup.jsp").forward(request, response);
    }
}
