package com.airlines.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.dao.CarrierDAO;


@WebServlet("/DeleteCarrierServlet")
public class DeleteCarrierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  int carrierID = Integer.parseInt(request.getParameter("carrierID"));

		  boolean success = CarrierDAO.deleteCarrier(carrierID);

		  if (success) {
	            request.setAttribute("message", "Carrier Deleted Successfully!");
	            request.setAttribute("messageType", "success");
	            request.setAttribute("redirectPage", "admin_home.jsp"); 
	        } else {
	            request.setAttribute("message", "Failed to Delete Carrier!");
	            request.setAttribute("messageType", "error");
	            request.setAttribute("redirectPage", "admin_home.jsp"); 
	        }
	        request.getRequestDispatcher("popup.jsp").forward(request, response);
	    
	}

}
