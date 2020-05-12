package com.flightticketbooking.dao;

import com.flightticketbooking.model.FlightSeatDetails;
import com.flightticketbooking.model.Flights;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User : venkateshs
 * Date : 5/9/2020
 * Time : 9:06 PM
 */
@Repository
public class FlightsBookingDaoImpl implements FlightsBookingDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private void setSessionFactory(SessionFactory sf){
		this.sessionFactory =sf;
	}
	
	@Override
	public boolean scheduleFlight(Flights flights) {
		Session session = sessionFactory.getCurrentSession();
		boolean savestatus = false;
		try{
			session.save(flights);
			return flights.isScheduledForBooking();
		}
		catch (Exception e){
		
		}
		return savestatus;
	}
	
	@Override
	public void update(Flights flights) {
	
	}
	
	@Override
	public List<Flights> getFlights() {
		return null;
	}
	
	@Override
	public Map<String,Integer> getAvailableSeatsByFlightNo(long flNo) {
		Session session = sessionFactory.getCurrentSession();
		Map<String,Integer> seatDetails = new LinkedHashMap<>();
		try{
			Flights flight = getFlightByFlightNo(flNo);
			if(flight != null) {
				seatDetails.put("seatsOccupied", flight.getFlightSeatDetailsList().size());
				seatDetails.put("totalSeats", flight.getSeats());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return seatDetails;
	}
	
	@Override
	public FlightSeatDetails bookTicketForFlight(FlightSeatDetails fsd) {
		Session session = sessionFactory.getCurrentSession();
		try{
			Object obj = session.save(fsd);
			return null;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Flights> getFlightList() {
		Session session = sessionFactory.getCurrentSession();
		try{
			List<Flights> flightList = session.createQuery("FROM Flights").list();
			return flightList;
		}
		catch (Exception e){
			return null;
		}
	}
	
	@Override
	public Flights getFlightByFlightNo(Long flightNum) {
	Session session = sessionFactory.getCurrentSession();
	try{
		Criteria criteria = session.createCriteria(Flights.class);
		criteria.add(Restrictions.eq("flightNumber",flightNum));
		List<Flights> fsl =	criteria.list();
		if(!fsl.isEmpty()){
			return fsl.get(0);
		}
		return null;
	}
	catch (Exception e){
		e.printStackTrace();
	}
	return null;
	}
	
	@Override
	public int getFlightSeatDEtailsByFlightNo(Long flightNum) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(Flights.class);
			criteria.add(Restrictions.eq("flightNumber", flightNum));
			List<Flights> fsd = criteria.list();
			return fsd.get(0).getFlightSeatDetailsList().size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
