package com.airlines.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.airlines.beans.Carrier;


public class CarrierDAO {
	 public static List<Carrier> getAllCarriers() {
	        List<Carrier> carriers = new ArrayList<>();
	        String sql = "SELECT * FROM Carriers";

	        try (Connection conn = DatabaseConnection.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	            	carriers.add(new Carrier(rs.getInt("CarrierID"),
	            			rs.getString("CarrierName"),
	            			rs.getInt("Discount30Days"), 
	            			rs.getInt("Discount60Days"),
	            			rs.getInt("Discount90Days"),
	            			rs.getInt("BulkBookingDiscount"), 
	            			rs.getInt("Refund2Days"), 
	            			rs.getInt("Refund10Days"), 
	            			rs.getInt("Refund20Days"), 
	            			rs.getInt("SilverUserDiscount"),
	            			rs.getInt("GoldUserDiscount"),
	            			rs.getInt("PlatinumUserDiscount")
	            			));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return carriers;
	    }
	
	    public static boolean deleteCarrier(int carrierId) {
	    	String deleteFlightSQL = "DELETE FROM Flights Where CarrierID =?";
	        String deleteCarrierSQL = "DELETE FROM Carriers WHERE CarrierID=?";
	       
	        try (Connection conn = DatabaseConnection.getConnection();
	        		PreparedStatement deleteFlightStmt = conn.prepareStatement(deleteFlightSQL);
	        		PreparedStatement deleteCarrierStmt = conn.prepareStatement(deleteCarrierSQL))
	        	 {
	            deleteFlightStmt.setInt(1, carrierId);
	            deleteFlightStmt.executeUpdate();
	            
	        	deleteCarrierStmt.setInt(1, carrierId);
	        	int rowAffected = deleteCarrierStmt.executeUpdate();
	        	
	        	
	            return rowAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    
	    
	    public static Carrier getCarrierByID(int carrierID) {
	        String sql = "SELECT * FROM Carriers WHERE CarrierID=?";
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            stmt.setInt(1, carrierID);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                return new Carrier(rs.getInt("CarrierID"),
	                                   rs.getString("CarrierName"),
	                                   rs.getInt("Discount30Days"),
	                                   rs.getInt("Discount60Days"),
	                                   rs.getInt("Discount90Days"),
	                                   rs.getInt("BulkBookingDiscount"),
	                                   rs.getInt("Refund2Days"),
	                                   rs.getInt("Refund10Days"),
	                                   rs.getInt("Refund20Days"),
	                                   rs.getInt("SilverUserDiscount"),
	                                   rs.getInt("GoldUserDiscount"),
	                                   rs.getInt("PlatinumUserDiscount"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    public static boolean updateCarrier(Carrier carrier) {
	        String sql = "UPDATE Carriers SET CarrierName=?, Discount30Days=?, Discount60Days=?, Discount90Days=?, " +
	                     "BulkBookingDiscount=?, Refund2Days=?, Refund10Days=?, Refund20Days=?, SilverUserDiscount=?, " +
	                     "GoldUserDiscount=?, PlatinumUserDiscount=? WHERE CarrierID=?";

	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, carrier.getCarrierName());
	            stmt.setInt(2, carrier.getDiscount30Days());
	            stmt.setInt(3, carrier.getDiscount60Days());
	            stmt.setInt(4, carrier.getDiscount90Days());
	            stmt.setInt(5, carrier.getBulkBookingDiscount());
	            stmt.setInt(6, carrier.getRefund2Days());
	            stmt.setInt(7, carrier.getRefund10Days());
	            stmt.setInt(8, carrier.getRefund20Days());
	            stmt.setInt(9, carrier.getSilverUserDiscount());
	            stmt.setInt(10, carrier.getGoldUserDiscount());
	            stmt.setInt(11, carrier.getPlatinumUserDiscount());
	            stmt.setInt(12, carrier.getCarrierId());

	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
}
	    public boolean addCarrier(Carrier carrier) {
	        String sql = "INSERT INTO Carriers (CarrierName, Discount30Days, Discount60Days, Discount90Days, " +
	                     "BulkBookingDiscount, Refund2Days, Refund10Days, Refund20Days, SilverUserDiscount, " +
	                     "GoldUserDiscount, PlatinumUserDiscount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            stmt.setString(1, carrier.getCarrierName());
	            stmt.setInt(2, carrier.getDiscount30Days());
	            stmt.setInt(3, carrier.getDiscount60Days());
	            stmt.setInt(4, carrier.getDiscount90Days());
	            stmt.setInt(5, carrier.getBulkBookingDiscount());
	            stmt.setInt(6, carrier.getRefund2Days());
	            stmt.setInt(7, carrier.getRefund10Days());
	            stmt.setInt(8, carrier.getRefund20Days());
	            stmt.setInt(9, carrier.getSilverUserDiscount());
	            stmt.setInt(10, carrier.getGoldUserDiscount());
	            stmt.setInt(11, carrier.getPlatinumUserDiscount());
	            
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
}
