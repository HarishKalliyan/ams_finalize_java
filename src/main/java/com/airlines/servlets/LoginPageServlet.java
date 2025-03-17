
package com.airlines.servlets;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.airlines.dao.UserDAO;

@WebServlet("/LoginPageServlet")
public class LoginPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
		String userName = userDAO.getUserName(userID, password); 
        String role = userDAO.validateUserRole(userID, password);
        
        
        String storedEncryptedPassword = userDAO.getUserPassword(userID);
        String decryptedPassword = userDAO.decryptPassword(storedEncryptedPassword, 3);

        
        if (decryptedPassword.equals(password)) {
        	HttpSession session = request.getSession();
        	session.setMaxInactiveInterval(180); // 3 minutes timeout
            session.setAttribute("userID", userID);
            session.setAttribute("userName", userName);
            session.setAttribute("role", role);

            request.setAttribute("message", "Login Successful!");
            request.setAttribute("messageType", "success");

            // Redirect based on role
            if (role.equals("Admin")) {
                request.setAttribute("redirectPage", "admin_home.jsp");
            } else {
                request.setAttribute("redirectPage", "user_home.jsp");
            }

            request.getRequestDispatcher("popup.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Invalid User ID or Password!");
            request.setAttribute("messageType", "error");
            request.setAttribute("redirectPage", "login.jsp");
            request.getRequestDispatcher("popup.jsp").forward(request, response);
        }
    }
}

