
package com.airlines.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.beans.Carrier;
import com.airlines.dao.CarrierDAO;


@WebServlet("/AddCarrierServlet")
public class AddCarrierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Retrieve parameters from the request
	        String carrierName = request.getParameter("CarrierName");
	        int discount30Days = Integer.parseInt(request.getParameter("Days30AdvanceBooking"));
	        int discount60Days = Integer.parseInt(request.getParameter("Days60AdvanceBooking"));
	        int discount90Days = Integer.parseInt(request.getParameter("Days90AdvanceBooking"));
	        int bulkBookingDiscount = Integer.parseInt(request.getParameter("BulkBooking"));
	        int silverUserDiscount = Integer.parseInt(request.getParameter("SilverUser"));
	        int goldUserDiscount = Integer.parseInt(request.getParameter("GoldUser"));
	        int platinumUserDiscount = Integer.parseInt(request.getParameter("PlatinumUser"));
	        int refund2Days = Integer.parseInt(request.getParameter("Days2BeforeTravelDate"));
	        int refund10Days = Integer.parseInt(request.getParameter("Days10BeforeTravelDate"));
	        int refund20Days = Integer.parseInt(request.getParameter("Days20OrMoreBeforeTravelDate"));

	        // Create Carrier object
	        Carrier carrier = new Carrier();
	        carrier.setCarrierName(carrierName);
	        carrier.setDiscount30Days(discount30Days);
	        carrier.setDiscount60Days(discount60Days);
	        carrier.setDiscount90Days(discount90Days);
	        carrier.setBulkBookingDiscount(bulkBookingDiscount);
	        carrier.setSilverUserDiscount(silverUserDiscount);
	        carrier.setGoldUserDiscount(goldUserDiscount);
	        carrier.setPlatinumUserDiscount(platinumUserDiscount);
	        carrier.setRefund2Days(refund2Days);
	        carrier.setRefund10Days(refund10Days);
	        carrier.setRefund20Days(refund20Days);

	        // Insert carrier into the database
	        CarrierDAO carrierDAO = new CarrierDAO();
	        boolean success = carrierDAO.addCarrier(carrier);

	        // Redirect based on the outcome
	        if (success) {
	            request.setAttribute("message", "Carrier Added Successfully!");
	            request.setAttribute("messageType", "success");
	            request.setAttribute("redirectPage", "admin_home.jsp"); // Redirect to View Bookings page
	        } else {
	            request.setAttribute("message", "Failed to Book Ticket!");
	            request.setAttribute("messageType", "error");
	            request.setAttribute("redirectPage", "add_carrier.jsp"); // Stay on booking page
	        }
	        request.getRequestDispatcher("popup.jsp").forward(request, response);
	    
	    }
  

}

