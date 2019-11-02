package com.eduardopena.magneto.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Exception Handler
 * @author edpena
 *
 */

public class ExceptionHandler {

	  @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
	  public final ResponseEntity<String> handleBadRequest(BadRequestException ex) {
		  String message = ex.getMessage();
	    return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	  }
}
