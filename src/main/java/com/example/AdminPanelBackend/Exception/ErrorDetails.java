package com.example.AdminPanelBackend.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
	  private Date timestamp;
	    private String message;
	    private String details;
	    private HttpStatus status;

}
