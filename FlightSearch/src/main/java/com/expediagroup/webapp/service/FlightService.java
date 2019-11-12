package com.expediagroup.webapp.service;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.expediagroup.webapp.Controller.FlightController;
import com.expediagroup.webapp.DAO.FlightRepository;
import com.expediagroup.webapp.model.Flights;

/*
 * Service class to perform processing of data from Repository
 * @author Karthik Ramachandran Murugesan
 * @version 1.0
 */
@Service
public class FlightService {
	
	@Autowired
	private FlightRepository flightRepository;
	
	private Logger logger = LoggerFactory.getLogger(FlightService.class);

	/*
	 * Saves the flight to the database
	 * @param Flights
	 */
	public void saveFlights(Flights flights) {
		logger.info("Flight info is saved");
		flightRepository.save(flights);
	}

	/*
	 * Find the flight based on the search criteria
	 * @param flightTime
	 * @return ResponseEntity<Object> which returns the flights found along with the status of the operation or the error message.
	 */
	public ResponseEntity<Object> findFlights(String flightTime) {
		
		LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
		
		/*try {
			time = LocalTime.parse(flightTime, formatter);
			
		} catch (Exception e) {
			logger.error("Enter time in the following format 6:00 AM (h:mm a Format)");
			throw e;
			//return new ResponseEntity<Object>("Enter time in the following format 6:00 AM (h:mm a Format)", HttpStatus.BAD_REQUEST);
		} */
		
		time = LocalTime.parse(flightTime, formatter);
		
		LocalTime maxTime = time.plusHours(5);
		LocalTime minTime = time.minusHours(5);
		
		
		List<Flights> list = flightRepository.findAll();
		
		List<Flights>flightFound = list.stream().filter(e -> (e.getDeparture().isAfter(minTime) && e.getDeparture().isBefore(maxTime)))
											.collect(Collectors.toList());

		if (flightFound.size() == 0) {
				logger.info("No flights available in the given search criteria");
				//throw NullException;
				return new ResponseEntity<Object>("No flights available in the given search criteria", HttpStatus.OK);
			} 
		logger.info("Flights are fetched successfully");
		return new ResponseEntity<Object>(flightFound, HttpStatus.OK);
		
	}
}
