
package com.airlines.servlets;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.airlines.dao.UserDAO;
import com.airlines.beans.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
 // Caesar cipher encryption method
    
    public static String encryptPassword(String password, int shift) {
    	
        StringBuilder encrypted = new StringBuilder();
        for (char ch : password.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                char shifted = (char) (ch + shift);
                encrypted.append(shifted);
            } else {
                encrypted.append(ch);
            }
        }
        return encrypted.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
    	String userName = request.getParameter("userName").trim();
        String password = request.getParameter("password").trim();
        String emailId = request.getParameter("emailId").trim();
        String dob = request.getParameter("dob").trim();
        String role = request.getParameter("role");
        String customerCategory = role.equals("Customer") ? "" : null;
        String phone = request.getParameter("phone").trim(); // Store as String for validation
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String zipCode = request.getParameter("zipCode").trim(); // Store as String for validation

        // Validation Patterns
        Pattern usernamePattern = Pattern.compile("^[A-Za-z ]+$");
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])[^\\s]{8,}$");
        Pattern phonePattern = Pattern.compile("^[0-9]{10}$"); // Ensures exactly 10 digits
        Pattern zipPattern = Pattern.compile("^[0-9]{5,10}$"); // Ensures valid zip code (5-10 digits)

        // Validate Username (Only alphabets)
        if (!usernamePattern.matcher(userName).matches()) {
            request.setAttribute("message", "Username should contain only alphabets.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        
        if (!phonePattern.matcher(phone).matches()) {
            request.setAttribute("message", "Phone Number Should be 10 digits.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Validate Password
        if (!passwordPattern.matcher(password).matches()) {
            request.setAttribute("message", "Password must have at least 8 characters, one uppercase, one lowercase, one special character, and no spaces.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Validate Email Format
        if (!emailPattern.matcher(emailId).matches()) {
            request.setAttribute("message", "Invalid email format.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Validate Date of Birth (Must be after 1924-01-01)
        if (dob.compareTo("1924-01-01") <= 0) {
            request.setAttribute("message", "Date of Birth must be after 1924-01-01.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        
     
        
        // Create User object
        User user = new User();
        
        String encryptedPassword = encryptPassword(password, 3);
        

        
        user.setUserName(userName);
        user.setRole(role); // Store the selected role
        user.setUserPassword(encryptedPassword);
        user.setCustomerCategory(customerCategory);
        user.setPhone(Long.parseLong(phone)); 
        user.setEmailId(emailId);
        user.setAddress(address);
        user.setCity(city);
        user.setState(state);
        user.setCountry(country);
        user.setZipCode(Long.parseLong(zipCode)); // Convert to long
        user.setDob(dob);

        // Register user and get UserID and Password
        UserDAO userDAO = new UserDAO();
        String result = userDAO.registerUser(user); 

        if (result.startsWith("User Registered Successfully")) {
            request.setAttribute("message", result);
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "Registration Failed!");
            request.setAttribute("messageType", "error");
        }
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }    
}

