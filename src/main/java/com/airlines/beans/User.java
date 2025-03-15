package com.airlines.beans;

import com.airlines.services.UserService;

public class User {
	private int userID;
	private String userPassword;
	private String userName;
	private String role;
	private String customerCategory;
	private long phone;
	private String emailId;
	private String address;
	private String city;
	private String state;
	private String country;
	private long zipCode;
	private String dob;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer userID2, String userName2, String emailId2, long phone2, String address2, String city2,
			String state2, int zipCode2) {
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setUserPassword(String userPassword) {
        this.userPassword = userPassword; // Encrypt before setting
    }

    public String getUserPassword() {
        return userPassword;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getZipCode() {
		return zipCode;
	}

	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
}
