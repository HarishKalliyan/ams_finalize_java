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

@WebServlet("/UserDetailsServlet")
public class UserDetailsServlet extends HttpServlet {
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

        User user = UserDAO.getUserByID(userID);
        
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("user_details.jsp").forward(request, response);
        } else {
            response.sendRedirect("user_home.jsp?message=User details not found");
        }
    }
}
