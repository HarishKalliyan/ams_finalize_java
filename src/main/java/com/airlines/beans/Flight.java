package com.airlines.beans;

public class Flight {
    private int flightID;
    private int carrierID;
    private String origin;
    private String destination;
    private String carrierName;
    private int airFare;
	private int economySeats;
    private int businessSeats;
    private int executiveSeats;

    // Constructor
    public Flight(int flightID, int carrierID, String origin, String destination, int airFare, int economySeats, int businessSeats, int executiveSeats, String carrierName) {
        this.flightID = flightID;
        this.carrierID = carrierID;
        this.origin = origin;
        this.destination = destination;
        this.airFare = airFare;
        this.economySeats = economySeats;
        this.businessSeats = businessSeats;
        this.executiveSeats = executiveSeats;
        this.carrierName = carrierName;
    }
    
    public Flight(int flightID, int carrierID, String origin, String destination, int airFare, int economySeats, int businessSeats, int executiveSeats) {
        this.flightID = flightID;
        this.carrierID = carrierID;
        this.origin = origin;
        this.destination = destination;
        this.airFare = airFare;
        this.economySeats = economySeats;
        this.businessSeats = businessSeats;
        this.executiveSeats = executiveSeats;
        
    }
    

    // Default Constructor
    public Flight() {}

    public Flight(int flightID, String origin, String destination, int airFare, int economySeats, int businessSeats,
			int executiveSeats) {
		super();
		this.flightID = flightID;
		this.origin = origin;
		this.destination = destination;
		this.airFare = airFare;
		this.economySeats = economySeats;
		this.businessSeats = businessSeats;
		this.executiveSeats = executiveSeats;
	}

	// Getters and Setters
    public int getFlightID() { return flightID; }
    public void setFlightID(int flightID) { this.flightID = flightID; }

    public int getCarrierID() { return carrierID; }
    public void setCarrierID(int carrierID) { this.carrierID = carrierID; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public int getAirFare() { return airFare; }
    public void setAirFare(int airFare) { this.airFare = airFare; }

    public int getEconomySeats() { return economySeats; }
    public void setEconomySeats(int economySeats) { this.economySeats = economySeats; }

    public int getBusinessSeats() { return businessSeats; }
    public void setBusinessSeats(int businessSeats) { this.businessSeats = businessSeats; }

    public int getExecutiveSeats() { return executiveSeats; }
    public void setExecutiveSeats(int executiveSeats) { this.executiveSeats = executiveSeats; }
    
    
    
    public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
}
