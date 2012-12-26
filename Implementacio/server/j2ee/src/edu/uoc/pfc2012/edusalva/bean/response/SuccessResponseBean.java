package edu.uoc.pfc2012.edusalva.bean.response;

public class SuccessResponseBean extends ResponseBean {

	public SuccessResponseBean() {
		this(false);
	}
	
	public SuccessResponseBean(boolean success) {
		setSuccess(success);
	}
}
