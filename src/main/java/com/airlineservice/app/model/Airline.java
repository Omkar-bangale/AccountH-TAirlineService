package com.airlineservice.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection="airlinedata")
public class Airline {
	

	private String airlineName;
	@Id
	private String airlineId;
	private String airlineAddress;
	private String airlineContact;
	
	private boolean airlinestatus;
	public boolean isAirlinestatus() {
		return airlinestatus;
	}
	public void setAirlinestatus(boolean airlinestatus) {
		this.airlinestatus = airlinestatus;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}
	public String getAirlineAddress() {
		return airlineAddress;
	}
	public void setAirlineAddress(String airlineAddress) {
		this.airlineAddress = airlineAddress;
	}
	public String getAirlineContact() {
		return airlineContact;
	}
	public void setAirlineContact(String airlineContact) {
		this.airlineContact = airlineContact;
	}
	public Airline(String airlineName, String airlineId, String airlineAddress, String airlineContact, boolean airlinestatus) {
		super();
		this.airlineName = airlineName;
		this.airlineId = airlineId;
		this.airlineAddress = airlineAddress;
		this.airlineContact = airlineContact;
		this.airlinestatus=airlinestatus;
	}
	@Override
	public String toString() {
		return "Airline [airlineName=" + airlineName + ", airlineId=" + airlineId + ", airlineAddress=" + airlineAddress
				+ ", airlineContact=" + airlineContact +", airlineStatus ="+airlinestatus +"]";
	}
	
	public Airline() {}
	

}
