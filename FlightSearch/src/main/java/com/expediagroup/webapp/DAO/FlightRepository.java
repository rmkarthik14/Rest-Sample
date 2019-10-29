package com.expediagroup.webapp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expediagroup.webapp.model.Flights;

public interface FlightRepository extends JpaRepository <Flights, Integer>{ 

}
