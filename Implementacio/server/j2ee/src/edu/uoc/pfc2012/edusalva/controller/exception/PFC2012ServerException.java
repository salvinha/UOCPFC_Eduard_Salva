package edu.uoc.pfc2012.edusalva.controller.exception;

public abstract class PFC2012ServerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1564217814641349930L;

	public PFC2012ServerException() {
	}

	public PFC2012ServerException(String message) {
		super(message);
	}

}
