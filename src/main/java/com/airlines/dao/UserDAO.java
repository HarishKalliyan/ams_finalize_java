
package com.airlines.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.airlines.beans.User;

public class UserDAO {
	
	
	
	
    public String validateUserRole(int userID, String password) {
        String role = null;
        String sql = "SELECT Role FROM users WHERE UserID = ? AND UserPassword = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.setString(2, encryptPassword(password,3));
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("Role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
    
 // Caesar cipher decryption method
    public  String decryptPassword(String encryptedPassword, int shift) {
        StringBuilder decrypted = new StringBuilder();
        for (char ch : encryptedPassword.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                char shifted = (char) (ch - shift);
                decrypted.append(shifted);
            } else {
                decrypted.append(ch);
            }
        }
        return decrypted.toString();
    }


    public String getUserPassword(int userID) {
        String encryptedPassword = null;
        String sql = "SELECT UserPassword FROM users WHERE UserID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                encryptedPassword = rs.getString("UserPassword");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return encryptedPassword; // Return the encrypted password from DB
    }

    
    
    
    public String registerUser(User user) {
        String sql = "INSERT INTO users (UserPassword, UserName, Role, CustomerCategory, Phone, EmailId, Address, City, State, Country, ZipCode, DOB) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        int generatedUserID = -1; // To store the newly generated UserID
         // Generate password

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            // Set parameters for the INSERT query
            pstmt.setString(1, user.getUserPassword());  // Use generated password
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getRole());
            pstmt.setString(4, user.getCustomerCategory());
            pstmt.setLong(5, user.getPhone());
            pstmt.setString(6, user.getEmailId());
            pstmt.setString(7, user.getAddress());
            pstmt.setString(8, user.getCity());
            pstmt.setString(9, user.getState());
            pstmt.setString(10, user.getCountry());
            pstmt.setLong(11, user.getZipCode());
            pstmt.setString(12, user.getDob());

            // Execute update and retrieve generated keys
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedUserID = rs.getInt(1);  // Retrieve the auto-incremented UserID
                    }
                }
                return "User Registered Successfully!<br>User ID: " + generatedUserID + "<br>Password: " + decryptPassword(user.getUserPassword(),3);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User registration failed.";
    }

    public static User getUserByID(int userID) {
        User user = null;
        String sql = "SELECT * FROM users WHERE UserID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setEmailId(rs.getString("EmailId"));
                user.setPhone(rs.getLong("Phone"));
                user.setRole(rs.getString("Role"));
                user.setCustomerCategory(rs.getString("CustomerCategory"));
                user.setAddress(rs.getString("Address"));
                user.setCity(rs.getString("City"));
                user.setCountry(rs.getString("Country"));
                user.setZipCode(rs.getLong("ZipCode"));
                user.setDob(rs.getString("DOB"));
                user.setState(rs.getString("State"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean updateProfile(User user) {
        String sql = "UPDATE users SET UserName = ?, EmailId = ?, Phone = ?, Address = ?, City = ?, State = ?, ZipCode = ? WHERE UserID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getEmailId());
            pstmt.setLong(3, user.getPhone());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getCity());
            pstmt.setString(6, user.getState());
            pstmt.setLong(7, user.getZipCode());
            pstmt.setInt(8, user.getUserID());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
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
    
    public String getUserName(int userID, String password) {
        String userName = null;
        String sql = "SELECT UserName FROM users WHERE UserID = ? AND UserPassword = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.setString(2, encryptPassword(password,3));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userName = rs.getString("UserName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userName;
    }

}



