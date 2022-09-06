package com.airlineservice.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.airlineservice.app.model.Airline;
import com.airlineservice.app.repository.AirlineServiceRepository;
import com.airlineservice.app.services.AirlneFailedException;;


@Service
public class Airlineservice {

	
	@Autowired
	private AirlineServiceRepository airlinerepo;

	
	
	public boolean getAirlinestatus(String airlineName)
	{
		Airline airline = airlinerepo.findByAirlineNameIn(airlineName);
		return airline.isAirlinestatus();
	}
	
	public List<Airline> getAllAirlines()
	{
		return airlinerepo.findAll();
	}
	
	public boolean postAirline(Airline airline)
	{
		airlinerepo.save(airline);
		return true;
	}
	
	public boolean putAirline(Airline airline, String airlineName)
	{
		try
		{	
			//String airlineId=airlinerepo.findByAirlineName(airline.getAirlineName());
		Airline a1= new Airline();
		a1=airlinerepo.findByAirlineNameIn(airlineName);
		a1.setAirlineAddress(airline.getAirlineAddress());
		a1.setAirlineContact(airline.getAirlineContact());
		a1.setAirlineId(a1.getAirlineId());
		
	//	a1.setAirlineAddress(airline.getAirlineName());
		a1.setAirlinestatus(airline.isAirlinestatus());
//		a1=airlinerepo.findByAirlineNameIn(airlineName)(airline.getAirlineId()).get();
//		a1.setAirlineAddress(airline.getAirlineAddress());
//		a1.setAirlineContact(airline.getAirlineContact());
//		a1.setAirlineName(airline.getAirlineName());
//		\
	
	airlinerepo.save(a1);
	
	return true;
	}
		catch(Exception e) {
			AirlneFailedException.message="Error in updating the Airline data";
			throw new AirlneFailedException();
			
		}
	}
	
	public String deleteAirline(String airlineName)
	{
		try {
//		Airline airline=new Airline();
//		airline= airlinerepo.findByAirlineNameIn(airlineName);
		airlinerepo.deleteAirline(airlineName);
		return "Airline Deleted Successfully"; 
		}
		catch(Exception e)
		{
			AirlneFailedException.message="Error in deleting the Airline";
			throw new AirlneFailedException();
		}
	}
}
