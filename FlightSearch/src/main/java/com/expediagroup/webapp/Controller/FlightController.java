package com.expediagroup.webapp.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expediagroup.webapp.model.Flights;
import com.expediagroup.webapp.service.FlightService;

/*
 * Controller class for the flight system.
 * @author Karthik Ramachandran Murugesan
 * @version 1.0
 * 
 */
@RestController
@Scope("session")
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	private Logger logger = LoggerFactory.getLogger(FlightController.class);
	
	private int count;
	
	/*
	 * Creates and send the flight to service object.
	 * @param Flight
	 */
	@PostMapping(path = "/flight")
	public void addFlights(@RequestBody Flights flights) {
		flightService.saveFlights(flights);
	}
	
	/*
	 * Retrieves the flight from the service layer based on the search time
	 * @param flight time
	 * @return ResponseBody with appropriate response
	 */
	@GetMapping(path = "/flight/{flightTime}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> searchFlights(@PathVariable String flightTime) {
		return flightService.findFlights(flightTime);
	}
	
	@GetMapping(path = "/count")
	public String setCount() {
		count += 1;
		return "Count is "+count+" after req";
	}
}

