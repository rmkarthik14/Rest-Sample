package com.expediagroup.webapp.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
/*
 * Represents Flight.
 * @author Karthik Ramachandran Murugesan
 * @version 1.0
 */
@Entity
public class Flights {
	@Id
	@NotNull(message="Flight should not be Empty")
	private String flight;
	
	@JsonFormat(pattern = "h:mm a")
	@NotNull(message="Departure time should not be Empty")
	private LocalTime departure;
	
	/*
	 * Creates flights with the given information
	 * @param flight Name of the flight
	 * @param departure Departure time of the flight
	 */
	public Flights(String flight, LocalTime departure) {
		super();
		this.flight = flight;
		this.departure = departure;
	}
	
	/*
	 * Creates flight without any parameter.
	 */
	public Flights() {
	}

	/*
	 * Gets flight name
	 *@return flight
	 */
	public String getFlight() {
		return flight;
	}

	
	/*
	 * Sets flight Name
	 * @param flight
	 */
	public void setFlight(String flight) {
		this.flight = flight;
	}

	/*
	 * Gets flight departure time
	 * @return departure
	 */
	public LocalTime getDeparture() {
		return departure;
	}

	/*
	 * Sets flight departure time
	 * @param departure
	 */
	public void setDeparture(LocalTime departure) {
		this.departure = departure;
	}
	
	@Override
	public String toString() {
		return "Flights [flight=" + flight + ", departure=" + departure + "]";
	}

	
}
