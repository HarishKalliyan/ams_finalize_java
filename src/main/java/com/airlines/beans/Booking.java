package com.airlines.beans;

public class Booking {
    private int bookingID;
    private int flightID;
    private int userID;
    private int noOfSeats;
    private String seatCategory;
    private String dateOfTravel;
    private String bookingStatus;
    private int bookingAmount;

    // Constructor
    public Booking(int bookingID, int flightID, int userID, int noOfSeats, String seatCategory, String dateOfTravel, String bookingStatus, int bookingAmount) {
        this.bookingID = bookingID;
        this.flightID = flightID;
        this.userID = userID;
        this.noOfSeats = noOfSeats;
        this.seatCategory = seatCategory;
        this.dateOfTravel = dateOfTravel;
        this.bookingStatus = bookingStatus;
        this.bookingAmount = bookingAmount;
    }

    // Getters and Setters
    public int getBookingID() { return bookingID; }
    public void setBookingID(int bookingID) { this.bookingID = bookingID; }

    public int getFlightID() { return flightID; }
    public void setFlightID(int flightID) { this.flightID = flightID; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public int getNoOfSeats() { return noOfSeats; }
    public void setNoOfSeats(int noOfSeats) { this.noOfSeats = noOfSeats; }

    public String getSeatCategory() { return seatCategory; }
    public void setSeatCategory(String seatCategory) { this.seatCategory = seatCategory; }

    public String getDateOfTravel() { return dateOfTravel; }
    public void setDateOfTravel(String dateOfTravel) { this.dateOfTravel = dateOfTravel; }

    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }

    public int getBookingAmount() { return bookingAmount; }
    public void setBookingAmount(int bookingAmount) { this.bookingAmount = bookingAmount; }
}
