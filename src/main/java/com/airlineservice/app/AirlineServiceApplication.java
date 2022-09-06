package com.airlineservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AirlineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public static RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	@Bean
	public static HttpHeaders getHttpHeader()
	{
	return new HttpHeaders();
	}
}
