package com.example.AdminPanelBackend.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.example.AdminPanelBackend.util.ResponseMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = ErrorDetails.builder().timestamp(new Date()).message(ex.getMessage())
				.details(request.getDescription(false)).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	 public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
	    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
	  }

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = ErrorDetails.builder().timestamp(new Date()).message(ex.getMessage())
				.details(request.getDescription(false)).status(HttpStatus.BAD_REQUEST).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

}
