package com.airlines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.airlines.beans.Booking;

public class BookingDAO {

	public static int calculateBookingAmount(int flightID, String seatCategory, int noOfSeats) {
		int basePrice = 0;
		String sql = "SELECT AirFare FROM Flights WHERE FlightID = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, flightID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				basePrice = rs.getInt("AirFare");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int multiplier = switch (seatCategory) {
		case "Executive" -> 5; // 5x Economy Price
		case "Business" -> 2; // 2x Economy Price
		default -> 1; // Economy Price
		};

		return basePrice * multiplier * noOfSeats;
	}

	public static boolean insertBooking(int flightID, int userID, int noOfSeats, String seatCategory,
			String dateOfTravel, int bookingAmount) {
		if (flightID <= 0 || userID <= 0 || noOfSeats <= 0 || bookingAmount <= 0 || seatCategory == null
				|| seatCategory.isEmpty()) {
			System.out.println("Invalid booking data. Booking failed.");
			return false; // Prevents inserting invalid data
		}

		String sql = "INSERT INTO Booking (FlightID, UserID, NoOfSeats, SeatCategory, DateOfTravel, BookingStatus, BookingAmount) VALUES (?, ?, ?, ?, ?, 'Booked', ?)";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, flightID);
			pstmt.setInt(2, userID);
			pstmt.setInt(3, noOfSeats);
			pstmt.setString(4, seatCategory);
			pstmt.setString(5, dateOfTravel);
			pstmt.setInt(6, bookingAmount);
			
			System.out.println(flightID+" "+userID+" "+noOfSeats+" "+seatCategory+" "+dateOfTravel+" " +bookingAmount);
			
			int rowsInserted = pstmt.executeUpdate();
			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<Booking> getBookingsByUser(int userID) {
		List<Booking> bookings = new ArrayList<>();
		String sql = "SELECT * FROM Booking WHERE UserID = ? ORDER BY DateOfTravel DESC";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userID);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Booking booking = new Booking(rs.getInt("BookingID"), rs.getInt("FlightID"), rs.getInt("UserID"),
						rs.getInt("NoOfSeats"), rs.getString("SeatCategory"), rs.getString("DateOfTravel"),
						rs.getString("BookingStatus"), rs.getInt("BookingAmount"));
				bookings.add(booking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookings;
	}

	public static Booking getBookingByID(int bookingID) {
	    Booking booking = null;
	    String sql = "SELECT * FROM Booking WHERE BookingID = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, bookingID);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            booking = new Booking(
	                rs.getInt("BookingID"),
	                rs.getInt("FlightID"),
	                rs.getInt("UserID"),
	                rs.getInt("NoOfSeats"),
	                rs.getString("SeatCategory"),
	                rs.getString("DateOfTravel"),
	                rs.getString("BookingStatus"),
	                rs.getInt("BookingAmount")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return booking;
	}

	public static boolean updateBooking(int bookingID, int bookingAmount,int noOfSeats, String seatCategory, String dateOfTravel) {
	    String sql = "UPDATE Booking SET NoOfSeats = ?, SeatCategory = ?, DateOfTravel = ?, BookingAmount=? WHERE BookingID = ?";
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, noOfSeats);
	        pstmt.setString(2, seatCategory);
	        pstmt.setString(3, dateOfTravel);
	        pstmt.setInt(4,bookingAmount);
	        pstmt.setInt(5, bookingID);

	        int rowsUpdated = pstmt.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public static boolean deleteBooking(int bookingID) {
	    String sql = "DELETE FROM Booking WHERE BookingID = ?";
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, bookingID);
	        
	        int rowsDeleted = pstmt.executeUpdate();
	        return rowsDeleted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


}
