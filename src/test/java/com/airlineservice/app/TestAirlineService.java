package com.airlineservice.app;


import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.airlineservice.app.model.Airline;
import com.airlineservice.app.repository.AirlineServiceRepository;
import com.airlineservice.app.services.Airlineservice;
import com.airlineservice.app.services.AirlneFailedException;

@SpringBootTest
public class TestAirlineService  extends MockitoExtension{
	
	@InjectMocks
	Airlineservice airlineservice;
	@Mock
	private Airline airline;
	
	@Mock
	AirlineServiceRepository airlinerepo;
	
	@BeforeEach
	public void setup()
	{
		this.airline=new Airline("Vistara","A-123", "New Delhi", "897654782",false);
	}
	
	@DisplayName("Unit test for adding the airline")
	@Test
	public void TestaddAirline()
	{
		boolean result =airlineservice.postAirline(airline);
		int i=0;
		if(result)
			i=1;
		Assertions.assertEquals(1, i);
	
	}
	@DisplayName("Unit test get all airlines")
	@Test
	public void TestGetAirlines()
	{
		
		List<Airline> l = airlineservice.getAllAirlines();
		System.out.println(" list is "+l);
		assertThat(l).isNotNull();
 	}
	
	@DisplayName("Unit test to update airline")
	@Test
	public void TestPutAirline()
	{
		Airline airline1 = new Airline("Vistara","A-123", "New Delhi", "897654782",false);
		given(airlinerepo.save(airline1)).willReturn(airline1);
		
		airline1.setAirlineContact("123456789");
		airline1.setAirlinestatus(true);
		
		given(airlinerepo.findByAirlineNameIn(airline1.getAirlineName())).willReturn(airline1);
		boolean b =airlineservice.putAirline(airline1, airline1.getAirlineName());
		int i=0;
		if(b)
			i=1;
		Assertions.assertEquals(1, i);
		
//		org.junit.jupiter.api.Assertions.assertThrows(AirlneFailedException.class,()->airlineservice.putAirline(airline,airline.getAirlineName()));
//	
//		verify(airlinerepo,never()).save(any(Airline.class));
	}
	
	@DisplayName("Unit test fr removing airline service")
	@Test
	public void TestDeleteAirline()
	{
		String airlineName ="Vistara";
		
		willDoNothing().given(airlinerepo).deleteAirline(airlineName);
		
		String a =airlineservice.deleteAirline(airlineName);
		
		Assertions.assertEquals("Airline Deleted Successfully",a);
		
	}
	

}
