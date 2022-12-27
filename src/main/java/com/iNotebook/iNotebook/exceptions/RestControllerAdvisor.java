package com.iNotebook.iNotebook.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {

	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<Map<String, String>> handleDuplicateUserException(DuplicateUserException due) {
		Map<String, String> response = new HashMap<>();
		response.put("error", due.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidNoteException.class)
	public ResponseEntity<Map<String, String>> handleInvalidNoteException(InvalidNoteException ine) {
		Map<String, String> response = new HashMap<>();
		response.put("error", ine.getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(InternalException.class)
	public ResponseEntity<Map<String, String>> handleInvalidNoteException(InternalException ie) {
		Map<String, String> response = new HashMap<>();
		response.put("error", ie.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
