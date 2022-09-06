package com.airlineservice.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.airlineservice.app.model.Airline;

public interface AirlineServiceRepository extends MongoRepository<Airline, String> {

	@Query(value="{airlineName:'?0'}")
	Airline findByAirlineNameIn(String airlineName);
	
	@Query(value="{airlineName:'?0'}", delete=true)
	void deleteAirline(String airlineName);
	
	
}
