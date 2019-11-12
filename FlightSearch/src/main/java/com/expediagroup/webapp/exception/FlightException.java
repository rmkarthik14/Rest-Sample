package com.expediagroup.webapp.exception;

import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.expediagroup.webapp.service.FlightService;

@ControllerAdvice
public class FlightException extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> somethingWentWrong(Exception exception){
		
		ErrorMessage otherException = new ErrorMessage(exception.getMessage(), "Something Went Wrong");
		
		return new ResponseEntity<ErrorMessage>(otherException, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
	
	@ExceptionHandler(DateTimeParseException.class)
	public final ResponseEntity<ErrorMessage> parseError(Exception exception){
		logger.error("Date Time parse error");
		ErrorMessage parseException = new ErrorMessage(exception.getMessage(), "Enter time in the following format 6:00 AM (h:mm a Format)");
		
		return new ResponseEntity<ErrorMessage>(parseException, HttpStatus.BAD_REQUEST);
	}
}
