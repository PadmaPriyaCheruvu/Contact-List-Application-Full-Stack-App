package com.dbdesign.model;

import java.util.List;

public class Contact {
	private int id;
	private String fname;
    private String mname;
    private String lname;
    private List<Address> addresses;
    private List<Phone> phones;
    private List<Date> dates;

    
    //Constructor
    public Contact(String fname, String mname, String lname) {
    	this.fname = fname;
        this.mname = mname;
        this.lname = lname;

    }
    
    public Contact() {}
    
    //Getters and Setters
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
	
	
	
}
