package com.flightticketbooking.dao;

import com.flightticketbooking.model.FlightSeatDetails;
import com.flightticketbooking.model.Flights;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface FlightsBookingDao {
	
	public boolean scheduleFlight(Flights flights);
	
	public void update(Flights flights);
	
	public List<Flights> getFlights();
	
	public Map<String,Integer> getAvailableSeatsByFlightNo(long flNo);
	
	public FlightSeatDetails bookTicketForFlight(FlightSeatDetails fsd);
	
	public List<Flights> getFlightList() ;
	
	public Flights getFlightByFlightNo(Long flightNo);
	
	public int getFlightSeatDEtailsByFlightNo(Long flightNum);
}
