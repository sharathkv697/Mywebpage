package com.flightticketbooking.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User : venkateshs
 * Date : 5/9/2020
 * Time : 8:58 PM
 */
@Entity
@Table(name="Flight_Seat_Details")
public class FlightSeatDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String userName;
	
	private int seatNo;
	
	
	private boolean bookingStatus;
	
	@ManyToOne
	@JoinColumn(name = "flightId")
	private Flights flight;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getSeatNo() {
		return seatNo;
	}
	
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	
	
	public boolean isBookingStatus() {
		return bookingStatus;
	}
	
	public void setBookingStatus(boolean bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	
	public Flights getFlight(){
		return flight;
	}
	
	public void setFlight(Flights flight) {
		this.flight = flight;
	}
}
