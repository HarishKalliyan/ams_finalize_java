
package com.airlines.dao;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.airlines.beans.Booking;

public class BookingDAO {

	
	
	public static boolean isSeatAvailable(int flightID, String seatCategory, int noOfSeats, String dateOfTravel) {
        String sql = "SELECT EconomySeats, BusinessSeats, ExecutiveSeats FROM Flights WHERE FlightID = ?";
        String scheduleSql = "SELECT EconomyClassBookedCount, BusinessClassBookedCount, ExecutiveClassBookedCount FROM FlightSchedule WHERE FlightID = ? AND DateOfTravel = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement flightStmt = conn.prepareStatement(sql);
             PreparedStatement scheduleStmt = conn.prepareStatement(scheduleSql)) {

            flightStmt.setInt(1, flightID);
            ResultSet flightRs = flightStmt.executeQuery();

            scheduleStmt.setInt(1, flightID);
            scheduleStmt.setString(2, dateOfTravel);
            ResultSet scheduleRs = scheduleStmt.executeQuery();

            if (flightRs.next()) {
                int totalEconomy = flightRs.getInt("EconomySeats");
                int totalBusiness = flightRs.getInt("BusinessSeats");
                int totalExecutive = flightRs.getInt("ExecutiveSeats");

                int bookedEconomy = 0, bookedBusiness = 0, bookedExecutive = 0;

                if (scheduleRs.next()) {
                    bookedEconomy = scheduleRs.getInt("EconomyClassBookedCount");
                    bookedBusiness = scheduleRs.getInt("BusinessClassBookedCount");
                    bookedExecutive = scheduleRs.getInt("ExecutiveClassBookedCount");
                }

                switch (seatCategory) {
                    case "Economy":
                        return bookedEconomy + noOfSeats <= totalEconomy;
                    case "Business":
                        return bookedBusiness + noOfSeats <= totalBusiness;
                    case "Executive":
                        return bookedExecutive + noOfSeats <= totalExecutive;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


	 public static void updateFlightSchedule(int flightID, String dateOfTravel, String seatCategory, int noOfSeats, boolean isBooking) {
	        String checkSql = "SELECT * FROM FlightSchedule WHERE FlightID = ? AND DateOfTravel = ?";
	        String updateSql = "UPDATE FlightSchedule SET EconomyClassBookedCount = ?, BusinessClassBookedCount = ?, ExecutiveClassBookedCount = ? WHERE FlightID = ? AND DateOfTravel = ?";
	        String insertSql = "INSERT INTO FlightSchedule (FlightID, DateOfTravel, EconomyClassBookedCount, BusinessClassBookedCount, ExecutiveClassBookedCount) VALUES (?, ?, ?, ?, ?)";

	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
	             PreparedStatement updateStmt = conn.prepareStatement(updateSql);
	             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

	            checkStmt.setInt(1, flightID);
	            checkStmt.setString(2, dateOfTravel);
	            ResultSet rs = checkStmt.executeQuery();

	            int bookedEconomy = 0, bookedBusiness = 0, bookedExecutive = 0;

	            if (rs.next()) {
	                bookedEconomy = rs.getInt("EconomyClassBookedCount");
	                bookedBusiness = rs.getInt("BusinessClassBookedCount");
	                bookedExecutive = rs.getInt("ExecutiveClassBookedCount");

	                if (isBooking) {
	                    switch (seatCategory) {
	                        case "Economy": bookedEconomy += noOfSeats; break;
	                        case "Business": bookedBusiness += noOfSeats; break;
	                        case "Executive": bookedExecutive += noOfSeats; break;
	                    }
	                } else { // If cancellation, subtract seats
	                    switch (seatCategory) {
	                        case "Economy": bookedEconomy -= noOfSeats; break;
	                        case "Business": bookedBusiness -= noOfSeats; break;
	                        case "Executive": bookedExecutive -= noOfSeats; break;
	                    }
	                }

	                updateStmt.setInt(1, bookedEconomy);
	                updateStmt.setInt(2, bookedBusiness);
	                updateStmt.setInt(3, bookedExecutive);
	                updateStmt.setInt(4, flightID);
	                updateStmt.setString(5, dateOfTravel);
	                updateStmt.executeUpdate();
	            } else {
	                // If no schedule exists, create a new one
	                insertStmt.setInt(1, flightID);
	                insertStmt.setString(2, dateOfTravel);
	                insertStmt.setInt(3, seatCategory.equals("Economy") ? noOfSeats : 0);
	                insertStmt.setInt(4, seatCategory.equals("Business") ? noOfSeats : 0);
	                insertStmt.setInt(5, seatCategory.equals("Executive") ? noOfSeats : 0);
	                insertStmt.executeUpdate();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
	 public static boolean insertBooking(int flightID, int userID, int noOfSeats, String seatCategory, String dateOfTravel, int bookingAmount) {
	        if (!isSeatAvailable(flightID, seatCategory, noOfSeats, dateOfTravel)) {
	            return false; // Prevent overbooking
	        }
	        
	        System.out.println(123);

	        String sql = "INSERT INTO Booking (FlightID, UserID, NoOfSeats, SeatCategory, DateOfTravel, BookingStatus, BookingAmount) VALUES (?, ?, ?, ?, ?, 'Booked', ?)";

	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, flightID);
	            pstmt.setInt(2, userID);
	            pstmt.setInt(3, noOfSeats);
	            pstmt.setString(4, seatCategory);
	            pstmt.setString(5, dateOfTravel);
	            pstmt.setInt(6, bookingAmount);

	            int rowsInserted = pstmt.executeUpdate();
	            if (rowsInserted > 0) {
	                updateFlightSchedule(flightID, dateOfTravel, seatCategory, noOfSeats, true);
	            }
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

	public static String cancelBooking(int bookingID) {
    String selectSql = "SELECT b.FlightID, b.NoOfSeats, b.SeatCategory, b.DateOfTravel, f.CarrierID, b.BookingAmount " +
                       "FROM Booking b INNER JOIN Flights f ON b.FlightID = f.FlightID WHERE b.BookingID = ?";
    String updateSql = "UPDATE Booking SET BookingStatus = 'Cancelled', BookingAmount = ? WHERE BookingID = ?";
    String carrierSql = "SELECT Refund2Days, Refund10Days, Refund20Days FROM Carriers WHERE CarrierID = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement selectStmt = conn.prepareStatement(selectSql);
         PreparedStatement updateStmt = conn.prepareStatement(updateSql);
         PreparedStatement carrierStmt = conn.prepareStatement(carrierSql)) {

        selectStmt.setInt(1, bookingID);
        ResultSet rs = selectStmt.executeQuery();

        if (rs.next()) {
            int flightID = rs.getInt("FlightID");
            int carrierID = rs.getInt("CarrierID");
            int noOfSeats = rs.getInt("NoOfSeats");
            String seatCategory = rs.getString("SeatCategory");
            String dateOfTravel = rs.getString("DateOfTravel");
            int bookingAmount = rs.getInt("BookingAmount");

            // Calculate days before travel
            LocalDate travelDate = LocalDate.parse(dateOfTravel);
            LocalDate today = LocalDate.now();
            int daysBeforeTravel = (int) ChronoUnit.DAYS.between(today, travelDate);

            // Get refund percentage
            carrierStmt.setInt(1, carrierID);
            ResultSet carrierRs = carrierStmt.executeQuery();
            int refundPercentage = 0;

            if (carrierRs.next()) {
                if (daysBeforeTravel >= 20) {
                    refundPercentage = carrierRs.getInt("Refund20Days");
                } else if (daysBeforeTravel >= 10) {
                    refundPercentage = carrierRs.getInt("Refund10Days");
                } else if (daysBeforeTravel >= 2) {
                    refundPercentage = carrierRs.getInt("Refund2Days");
                }
            }

            int refundAmount = (bookingAmount * refundPercentage) / 100;

            // Update booking status and refund amount
            updateStmt.setInt(1, refundAmount);
            updateStmt.setInt(2, bookingID);
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Add seats back to flight schedule
                updateFlightSchedule(flightID, dateOfTravel, seatCategory, noOfSeats, false);
                return "Booking Cancelled! Refund Amount: $" + refundAmount;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return "Failed to cancel booking!";
}

	public static int calculateBookingAmount(int flightID, String seatCategory, int noOfSeats, String bookingDate) {
    int basePrice = 0;
    String sqlFetch = "SELECT f.AirFare, f.CarrierID, c.Discount30Days, c.Discount60Days, c.Discount90Days, c.BulkBookingDiscount, c.SilverUserDiscount, c.GoldUserDiscount, c.PlatinumUserDiscount FROM Flights f INNER JOIN Carriers c ON f.CarrierID = c.CarrierID WHERE f.FlightID = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmtFetch = conn.prepareStatement(sqlFetch)) {

        pstmtFetch.setInt(1, flightID);
        ResultSet rs = pstmtFetch.executeQuery();

        if (rs.next()) {
            basePrice = rs.getInt("AirFare");
            int discount30Days = rs.getInt("Discount30Days");
            int discount60Days = rs.getInt("Discount60Days");
            int discount90Days = rs.getInt("Discount90Days");
            int bulkDiscount = rs.getInt("BulkBookingDiscount");
            int silverDiscount = rs.getInt("SilverUserDiscount");
            int goldDiscount = rs.getInt("GoldUserDiscount");
            int platinumDiscount = rs.getInt("PlatinumUserDiscount");

            // Determine seat category multiplier
            int multiplier = switch (seatCategory) {
                case "Executive" -> 5;  // Executive = 5x Economy Price
                case "Business" -> 2;   // Business = 2x Economy Price
                default -> 1;           // Economy = Base Price
            };

            // Calculate total price before discounts
            int totalAmount = basePrice * multiplier * noOfSeats;

            // Calculate days before travel
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate travelDate = LocalDate.parse(bookingDate, formatter);
            LocalDate currentDate = LocalDate.now();
            long daysBefore = ChronoUnit.DAYS.between(currentDate, travelDate);

            // Apply discount based on days before travel
            int discountPercentage = 0;
            if (daysBefore >= 90) {
                discountPercentage = discount90Days;
            } else if (daysBefore >= 60) {
                discountPercentage = discount60Days;
            } else if (daysBefore >= 30) {
                discountPercentage = discount30Days;
            }

            // Apply bulk booking discount
            if (noOfSeats >= 10) {
                discountPercentage += bulkDiscount;
            }

            // Apply user category discount (fetch from session)
			/*
			 * String userCategory = getUserCategory(); // Assume a method fetching user
			 * category switch (userCategory) { case "Silver" -> discountPercentage +=
			 * silverDiscount; case "Gold" -> discountPercentage += goldDiscount; case
			 * "Platinum" -> discountPercentage += platinumDiscount; }
			 */

            // Apply final discount
            totalAmount -= (totalAmount * discountPercentage) / 100;
            return totalAmount;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return basePrice * noOfSeats; // Fallback in case of DB error
}



}


