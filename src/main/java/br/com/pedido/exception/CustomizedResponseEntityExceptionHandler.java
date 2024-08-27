package br.com.pedido.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	  public final ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        //request.getDescription(false),HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
	    		request.getDescription(false),HttpStatus.NOT_FOUND.getReasonPhrase());

	    //return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
	    return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(EntityInvalidException.class)
	  public final ResponseEntity<ExceptionResponse> handleNotFoundException(EntityInvalidException ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        //request.getDescription(false),HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
	    		request.getDescription(false),HttpStatus.BAD_REQUEST.getReasonPhrase());

	    //return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
	    return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	  }
}
