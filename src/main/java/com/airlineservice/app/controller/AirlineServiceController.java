package com.airlineservice.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.airlineservice.app.model.Airline;
import com.airlineservice.app.services.Airlineservice;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@RestController
public class AirlineServiceController {

	@Autowired
	protected static RestTemplate restTemplate;
	
	private static boolean validated=false;
	
	@Autowired
	private static HttpHeaders httpheader;
	
	@Autowired
	private Airlineservice airlineservice;
	
	@GetMapping("/airline/status/{airlineName}")
	public boolean getAirlineStatus(@PathVariable String airlineName)
	{
		return airlineservice.getAirlinestatus(airlineName);
	}
	
	
	@GetMapping("/airline/all")
	public List<Airline> GetAllAirlines(@RequestHeader Map<String, String> header )
	{
		AirlineServiceController.validateToken(header);
		
		if(validated)
		return airlineservice.getAllAirlines();
		else
			throw new RuntimeException("Error in validating the token");
	}
	
	@PostMapping("/airline/add")
	public String addAirline(@RequestHeader Map<String,String> headers, @RequestBody Airline airline)
	{
AirlineServiceController.validateToken(headers);
		
		if(validated)
		{
			if(airlineservice.postAirline(airline))
					return "Airline Added successfully";
			else
				throw new RuntimeException("Something went wrong while adding the Airline");
		}
		else {
			throw new RuntimeException("Token Validation failed");
		}
		
	}
	
	@PutMapping("/airline/update/{airlineName}")
	public String updateAirline(@RequestHeader Map<String,String> headers, @RequestBody Airline airline, @PathVariable String airlineName)
	{
AirlineServiceController.validateToken(headers);
		
		if(validated)
		{
			
			if(airlineservice.putAirline(airline, airlineName))
			{
				return "Airline data updated successfully";
			}
			else
			{
				throw new RuntimeException("Something went wrong in updating the airline");
			}
		}
		else {
			throw new RuntimeException("Token is not valid");
		}
	}
	
	@DeleteMapping("/airline/delete/{airlineName}")
	public String removeAirline(@RequestHeader Map<String,String> headers, @PathVariable String airlineName)
	{
AirlineServiceController.validateToken(headers);
		
		if(validated)
		{
			return airlineservice.deleteAirline(airlineName);
		}
		else {
			throw new RuntimeException("Token invalid or error in delete service");
		}
		
	}
	
	

	
	
	
	
	
	
	
	
	
	public  static void validateToken (Map<String, String> header)
	{
	
		String token="";
		for(String key : header.keySet())
		{
			if(key.equals("authorization"))
				token=header.get(key);
		}
		HttpHeaders httpheader = new HttpHeaders();
		httpheader.set("Authorization", token);
		HttpEntity<Void> requestentity = new HttpEntity<>(httpheader);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:9004/validatejwt",HttpMethod.GET, requestentity,boolean.class);
		validated=response.getBody().booleanValue();
	}

}

