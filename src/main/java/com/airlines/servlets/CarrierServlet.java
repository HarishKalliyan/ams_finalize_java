
package com.airlines.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.beans.Carrier;
import com.airlines.dao.CarrierDAO;

@WebServlet("/CarrierServlet")
public class CarrierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Carrier> carrierList = CarrierDAO.getAllCarriers();
        request.setAttribute("carrierList", carrierList);
        request.getRequestDispatcher("display_carriers.jsp").forward(request, response);
	}
	

}
