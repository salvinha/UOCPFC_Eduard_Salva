package edu.uoc.pfc2012.edusalva.bean.response;

public abstract class ResponseBean {

	private boolean success;
	
	public ResponseBean() {
		setSuccess(false);
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
