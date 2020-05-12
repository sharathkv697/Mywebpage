package com.flightticketbooking.service;

import com.flightticketbooking.dao.FlightsBookingDao;
import com.flightticketbooking.model.FlightSeatDetails;
import com.flightticketbooking.model.Flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User : venkateshs
 * Date : 5/9/2020
 * Time : 9:45 PM
 */
@Service
public class FlightsBookingServiceImpl implements FlightsBookingService {
	
	@Autowired
	private FlightsBookingDao flightsBookingDao;
	
	
	@Override
	public boolean scheduleFlight(Flights flights) {
		return flightsBookingDao.scheduleFlight(flights);
	}
	
	
	@Override
	public List<Flights> getFlights() {
		return flightsBookingDao.getFlights();
	}
	
	@Override
	public int getAvailableSeatsByFlightNo(long flNo) {
		Map<String, Integer> seatDetailsMap = flightsBookingDao.getAvailableSeatsByFlightNo(flNo);
		if (!seatDetailsMap.isEmpty()) {
			int seatCapacity = seatDetailsMap.get("totalSeats");
			int seatsOccupied = seatDetailsMap.get("seatsOccupied");
			return seatCapacity - seatsOccupied;
		}
		return 0;
	}
	
	@Override
	public int bookTicketForFlight(FlightSeatDetails fsd) {
		int seatsOccupied = flightsBookingDao.getFlightSeatDEtailsByFlightNo(fsd.getFlight().getFlightNumber());
		fsd.setSeatNo(seatsOccupied + 1);
		flightsBookingDao.bookTicketForFlight(fsd);
		return seatsOccupied + 1;
	}
	
	@Override
	public boolean isFlightExists(long flightNo) {
		List<Flights> flightsList = flightsBookingDao.getFlightList();
		if (!flightsList.isEmpty()) {
			for (Flights flight : flightsList) {
				if (flight.getFlightNumber() == flightNo) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public Flights getFlightByFlightNo(Long flightNo) {
		return flightsBookingDao.getFlightByFlightNo(flightNo);
	}
}
