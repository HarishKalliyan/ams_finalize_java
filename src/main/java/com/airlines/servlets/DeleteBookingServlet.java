
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
        String message = BookingDAO.cancelBooking(bookingID);

        request.setAttribute("message", message);
        request.setAttribute("messageType", message.startsWith("✅") ? "success" : "error");
        request.setAttribute("redirectPage", "view_bookings.jsp");
        request.getRequestDispatcher("popup.jsp").forward(request, response);
    }






}
