package com.example.time.exception;

public class AuthException extends RuntimeException {


	private static final long serialVersionUID = 1969956251683817406L;

	public AuthException(Throwable cause) {
	    super("Unexpected error", cause);
	  }

	public AuthException(String notAuthorized) {
		super(notAuthorized);
	}
	
}