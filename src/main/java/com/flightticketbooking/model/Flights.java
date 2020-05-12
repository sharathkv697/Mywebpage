package com.flightticketbooking.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User : venkateshs
 * Date : 5/9/2020
 * Time : 8:54 PM
 */
/*@NamedQueries({
@NamedQuery(
		name = "flightByFlightNo",
		query= "from Flights f where f.flightNumber = :flightNo"
)
})*/
@Entity
@Table(name="Flights_Available")
public class Flights {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private long flightNumber;
	
	private int seats;
	
	private boolean seatsAvailableStatus;
	
	private boolean isScheduledForBooking;
	
	@OneToMany(targetEntity = FlightSeatDetails.class,mappedBy = "flight",fetch=FetchType.EAGER)
	private List<FlightSeatDetails> flightSeatDetailsList;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public long getFlightNumber() {
		return flightNumber;
	}
	
	public void setFlightNumber(long flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public int getSeats() {
		return seats;
	}
	
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	public boolean isSeatsAvailableStatus() {
		return seatsAvailableStatus;
	}
	
	public void setSeatsAvailableStatus(boolean seatsAvailableStatus) {
		this.seatsAvailableStatus = seatsAvailableStatus;
	}
	
	public boolean isScheduledForBooking() {
		return isScheduledForBooking;
	}
	
	public void setScheduledForBooking(boolean scheduledForBooking) {
		isScheduledForBooking = scheduledForBooking;
	}
	
	public List<FlightSeatDetails> getFlightSeatDetailsList() {
		return flightSeatDetailsList;
	}
	
	public void setFlightSeatDetailsList(List<FlightSeatDetails> flightSeatDetailsList) {
		this.flightSeatDetailsList = flightSeatDetailsList;
	}
	
	
}
