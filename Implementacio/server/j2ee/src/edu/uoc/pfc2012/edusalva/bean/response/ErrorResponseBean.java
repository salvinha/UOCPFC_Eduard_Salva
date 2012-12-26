package edu.uoc.pfc2012.edusalva.bean.response;


public class ErrorResponseBean extends ResponseBean {

	private String errorMessage;
	
	public ErrorResponseBean() {
		super();
		setSuccess(false);
	}
	
	public ErrorResponseBean(String errorMessage) {
		this();
		setErrorMessage(errorMessage);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
