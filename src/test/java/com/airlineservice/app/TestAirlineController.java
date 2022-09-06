package com.airlineservice.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TestAirlineController {
	
	@Autowired
	private MockMvc mvc;

	public void testGetAllAirlines() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.get("/airline/all"))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

}
