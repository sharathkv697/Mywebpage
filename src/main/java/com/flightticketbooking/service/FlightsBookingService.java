package com.flightticketbooking.service;

import com.flightticketbooking.model.FlightSeatDetails;
import com.flightticketbooking.model.Flights;

import java.util.List;

public interface FlightsBookingService {
	
	public boolean scheduleFlight(Flights flights);
	
	public List<Flights> getFlights();
	
	public int getAvailableSeatsByFlightNo(long flNo);
	
	public int bookTicketForFlight(FlightSeatDetails fsd);
	
	public boolean isFlightExists(long flightNo);
	
	public Flights getFlightByFlightNo(Long flightNo);
}
