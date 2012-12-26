package edu.uoc.pfc2012.edusalva.bean.response;


public class KonceptIdResponseBean extends ResponseBean {

	private String id;
	
	public KonceptIdResponseBean() {
		super();
		setSuccess(true);
	}
	
	public KonceptIdResponseBean(String id) {
		this();
		setId(id);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
