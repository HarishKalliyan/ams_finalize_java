
package com.airlines.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.dao.CarrierDAO;
import com.airlines.beans.Carrier;

@WebServlet("/EditCarrierServlet")
public class EditCarrierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carrierID = Integer.parseInt(request.getParameter("carrierID"));
        String carrierName = request.getParameter("carrierName");
        int discount30Days = Integer.parseInt(request.getParameter("discount30Days"));
        int discount60Days = Integer.parseInt(request.getParameter("discount60Days"));
        int discount90Days = Integer.parseInt(request.getParameter("discount90Days"));
        int bulkBookingDiscount = Integer.parseInt(request.getParameter("bulkBookingDiscount"));
        int refund2Days = Integer.parseInt(request.getParameter("refund2Days"));
        int refund10Days = Integer.parseInt(request.getParameter("refund10Days"));
        int refund20Days = Integer.parseInt(request.getParameter("refund20Days"));
        int silverUserDiscount = Integer.parseInt(request.getParameter("silverUserDiscount"));
        int goldUserDiscount = Integer.parseInt(request.getParameter("goldUserDiscount"));
        int platinumUserDiscount = Integer.parseInt(request.getParameter("platinumUserDiscount"));

        Carrier carrier = new Carrier(carrierID, carrierName, discount30Days, discount60Days, discount90Days, 
                                      bulkBookingDiscount, refund2Days, refund10Days, refund20Days, 
                                      silverUserDiscount, goldUserDiscount, platinumUserDiscount);

        boolean success = CarrierDAO.updateCarrier(carrier);

        if (success) {
            request.setAttribute("message", "Carrier Updated Successfully!");
            request.setAttribute("messageType", "success");
            request.setAttribute("redirectPage", "admin_home.jsp"); // Redirect to View Bookings page
        } else {
            request.setAttribute("message", "Failed to Update Booking!");
            request.setAttribute("messageType", "error");
            request.setAttribute("redirectPage", "edit_carrier.jsp?carrierID=" + carrierID); // Stay on edit page
        }
        request.getRequestDispatcher("popup.jsp").forward(request, response);
   
    }
}

