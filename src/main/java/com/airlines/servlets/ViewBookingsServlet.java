package com.airlines.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.airlines.beans.Booking;
import com.airlines.dao.BookingDAO;

@WebServlet("/ViewBookingsServlet")
public class ViewBookingsServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        if (userID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Booking> bookings = BookingDAO.getBookingsByUser(userID);
        request.setAttribute("bookingList", bookings);
        request.getRequestDispatcher("view_bookings.jsp").forward(request, response);
    }
}
