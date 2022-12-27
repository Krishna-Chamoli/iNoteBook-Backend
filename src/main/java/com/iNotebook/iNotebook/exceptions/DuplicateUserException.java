package com.iNotebook.iNotebook.exceptions;

public class DuplicateUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateUserException() {
		super("User with given email already exists");
	}
}
