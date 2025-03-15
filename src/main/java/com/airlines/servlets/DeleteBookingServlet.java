package com.airlines.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.airlines.dao.BookingDAO;

@WebServlet("/DeleteBookingServlet")
public class DeleteBookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));

        boolean success = BookingDAO.deleteBooking(bookingID);

        if (success) {
            request.setAttribute("message", "Booking Deleted Successfully!");
            request.setAttribute("messageType", "success");
            request.setAttribute("redirectPage", "user_home.jsp"); // Redirect back to bookings page
        } else {
            request.setAttribute("message", "Failed to Delete Booking!");
            request.setAttribute("messageType", "error");
            request.setAttribute("redirectPage", "user_home.jsp"); // Stay on bookings page
        }
        request.getRequestDispatcher("popup.jsp").forward(request, response);
    }
}
