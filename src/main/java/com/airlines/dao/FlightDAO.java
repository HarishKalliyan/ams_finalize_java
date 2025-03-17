
package com.airlines.dao;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.airlines.beans.Flight;

public class FlightDAO {

	public static List<Flight> searchFlights(String flightID, String origin, String destination) {
        List<Flight> flights = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT f.*, c.CarrierName FROM Flights f " +
                                              "INNER JOIN Carriers c ON f.CarrierID = c.CarrierID WHERE 1=1 ");

        List<Object> params = new ArrayList<>();

        if (flightID != null && !flightID.isEmpty()) {
            sql.append("AND f.FlightID = ? ");
            params.add(Integer.parseInt(flightID));
        }
        if (origin != null && !origin.isEmpty()) {
            sql.append("AND LOWER(f.Origin) LIKE LOWER(?) ");
            params.add("%" + origin + "%");
        }
        if (destination != null && !destination.isEmpty()) {
            sql.append("AND LOWER(f.Destination) LIKE LOWER(?) ");
            params.add("%" + destination + "%");
        }

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            
            for (int i = 0; i < params.size(); i++) {
                if (params.get(i) instanceof Integer) {
                    ps.setInt(i + 1, (Integer) params.get(i));
                } else {
                    ps.setString(i + 1, (String) params.get(i));
                }
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flights.add(new Flight(
                    rs.getInt("FlightID"),
                    rs.getInt("CarrierID"),
                    rs.getString("Origin"),
                    rs.getString("Destination"),
                    rs.getInt("AirFare"),
                    rs.getInt("EconomySeats"),
                    rs.getInt("BusinessSeats"),
                    rs.getInt("ExecutiveSeats"),
                    rs.getString("CarrierName")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }
	
	
	
	 public static boolean hasActiveBookings(int flightID) {
	        String sql = "SELECT COUNT(*) FROM Booking WHERE FlightID = ? AND BookingStatus = 'Booked'";

	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setInt(1, flightID);
	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next() && rs.getInt(1) > 0) {
	                return true;  // Flight has active bookings, so we cannot delete it
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false; // No active bookings, flight can be deleted
	    }

	    // Delete flight (only if no active bookings)
	    public static boolean deleteFlight(int flightID) {
	        if (hasActiveBookings(flightID)) {
	            return false; // Cannot delete if active bookings exist
	        }

	        String sql = "DELETE FROM Flights WHERE FlightID=?";
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setInt(1, flightID);
	            return pstmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	
	
    // Get all flights
	public static List<Flight> getAllFlights() {
	    List<Flight> flights = new ArrayList<>();
	    try (Connection con = DatabaseConnection.getConnection()) {
	        String query = "SELECT f.*, c.CarrierName FROM Flights f INNER JOIN Carriers c ON f.CarrierID = c.CarrierID";
	        PreparedStatement ps = con.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            flights.add(new Flight(
	                rs.getInt("FlightID"),
	                rs.getInt("CarrierID"),
	                rs.getString("Origin"),
	                rs.getString("Destination"),
	                rs.getInt("AirFare"),
	                rs.getInt("EconomySeats"),
	                rs.getInt("BusinessSeats"),
	                rs.getInt("ExecutiveSeats"),
	                rs.getString("CarrierName")  // New field for Carrier Name
	            ));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return flights;
	}


    // Insert a new flight
    public static boolean addFlight(Flight flight) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Flights (CarrierID, Origin, Destination, AirFare, EconomySeats, BusinessSeats, ExecutiveSeats) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, flight.getCarrierID());
            ps.setString(2, flight.getOrigin());
            ps.setString(3, flight.getDestination());
            ps.setInt(4, flight.getAirFare());
            ps.setInt(5, flight.getEconomySeats());
            ps.setInt(6, flight.getBusinessSeats());
            ps.setInt(7, flight.getExecutiveSeats());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static Flight getFlightById(int flightID) {
        Flight flight = null;
        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Flights WHERE FlightID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, flightID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                flight = new Flight(
                    rs.getInt("FlightID"),
                    rs.getInt("CarrierID"),
                    rs.getString("Origin"),
                    rs.getString("Destination"),
                    rs.getInt("AirFare"),
                    rs.getInt("EconomySeats"),
                    rs.getInt("BusinessSeats"),
                    rs.getInt("ExecutiveSeats")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flight;
    }


    // Update flight
    public static boolean updateFlight(Flight flight) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "UPDATE Flights SET  Origin=?, Destination=?, AirFare=?, EconomySeats=?, BusinessSeats=?, ExecutiveSeats=? WHERE FlightID=?";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, flight.getOrigin());
            ps.setString(2, flight.getDestination());
            ps.setInt(3, flight.getAirFare());
            ps.setInt(4, flight.getEconomySeats());
            ps.setInt(5, flight.getBusinessSeats());
            ps.setInt(6, flight.getExecutiveSeats());
            ps.setInt(7, flight.getFlightID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

   
    public static boolean activeBooking(int flightID) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "SELECT * From Booking WHERE FlightID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, flightID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


