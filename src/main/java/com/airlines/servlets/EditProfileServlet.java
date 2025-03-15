package com.airlines.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.airlines.beans.User;
import com.airlines.dao.UserDAO;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        if (userID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userName = request.getParameter("userName");
        String emailId = request.getParameter("emailId");
        long phone = Long.parseLong(request.getParameter("phone"));
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        int zipCode = Integer.parseInt(request.getParameter("zipCode"));

        User user = new User(userID, userName, emailId, phone, address, city, state, zipCode);

        boolean updated = UserDAO.updateProfile(user);

        if (updated) {
            session.setAttribute("user", user);
            response.sendRedirect("profile.jsp?message=Profile Updated Successfully");
        } else {
            response.sendRedirect("profile.jsp?message=Update Failed");
        }
    }
}
