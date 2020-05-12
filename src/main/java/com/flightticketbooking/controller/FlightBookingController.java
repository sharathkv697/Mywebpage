package com.flightticketbooking.controller;

import com.flightticketbooking.model.FlightSeatDetails;
import com.flightticketbooking.model.Flights;
import com.flightticketbooking.service.FlightsBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User : venkateshs
 * Date : 5/9/2020
 * Time : 8:47 PM
 */
@RestController
@RequestMapping("")
public class FlightBookingController {
	
	@Autowired
	private FlightsBookingService flightsBookingService;
	
	
	@RequestMapping(value = "/getAvailableSeats/{flightNumber}", method = {RequestMethod.GET})
	public Map<String, Object> getFlightDetails(@PathVariable("flightNumber") Long flightNo) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			int seatsAvailable = flightsBookingService.getAvailableSeatsByFlightNo(flightNo);
			responseMap.put("status", "success");
			responseMap.put("count", seatsAvailable);
		} catch (Exception e) {
			responseMap.put("status", "failure");
		}
		return responseMap;
	}
	
	
	@RequestMapping(value = "/scheduleFlight/{flightNumber}/{noOfSeats}", method = {RequestMethod.POST})
	public Map<String, Object> scheduleFlight(@PathVariable("flightNumber") long flightNo, @PathVariable("noOfSeats") int noOfSeats) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			boolean saveStatus = false;
			if (!flightsBookingService.isFlightExists(flightNo)) {
				Flights flight = new Flights();
				flight.setFlightNumber(flightNo);
				flight.setSeats(noOfSeats);
				flight.setScheduledForBooking(true);
				flight.setSeatsAvailableStatus(true);
				saveStatus = flightsBookingService.scheduleFlight(flight);
				responseMap.put("status", saveStatus);
			} else {
				responseMap.put("status", saveStatus);
				responseMap.put("message", "flight already exists");
			}
		} catch (Exception e) {
			responseMap.put("status", "failure");
		}
		return responseMap;
	}
	
	
	@RequestMapping(value = "/bookSeat/{flightNumber}/{userName}", method = {RequestMethod.POST})
	public Map<String, Object> scheduleFlight(@PathVariable("flightNumber") long flightNo, @PathVariable("userName") String userName) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			int availableSeats = flightsBookingService.getAvailableSeatsByFlightNo(flightNo);
			if (availableSeats > 0) {
				Flights flight = flightsBookingService.getFlightByFlightNo(flightNo);
				for (FlightSeatDetails eFsd : flight.getFlightSeatDetailsList()) {
					if (eFsd.getUserName().equalsIgnoreCase(userName)) {
						responseMap.put("status", "Failure");
						responseMap.put("message", "User has already booked a seat on this flight");
						return responseMap;
					}
				}
				FlightSeatDetails fsd = new FlightSeatDetails();
				fsd.setUserName(userName);
				fsd.setBookingStatus(true);
				fsd.setFlight(flight);
				int seatNo = flightsBookingService.bookTicketForFlight(fsd);
				responseMap.put("status", "Success");
				responseMap.put("seatNumber", seatNo);
			} else {
				responseMap.put("status", "Failure");
				responseMap.put("message", "Tickets Full");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseMap;
	}
}
