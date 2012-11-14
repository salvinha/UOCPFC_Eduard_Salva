package edu.uoc.pfc2012.edusalva.controller.exception;

public class WrongRequestException extends PFC2012ServerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4077920785287480105L;

	public WrongRequestException() {
	}

	public WrongRequestException(String message) {
		super(message);
	}

}
