package edu.uoc.pfc2012.edusalva.worker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractWorker {

	private Map<String, String[]> params;
	private String path;
	private HttpServletRequest req;
	private HttpServletResponse res;

	public abstract void processRequest();

	public Map<String, String[]> getParams() {
		return params;
	}

	public void setParams(Map<String, String[]> params) {
		this.params = params;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public HttpServletRequest getReq() {
		return req;
	}
	
	public void setReq(HttpServletRequest req) {
		this.req = req;
	}
	
	public HttpServletResponse getRes() {
		return res;
	}

	public void setRes(HttpServletResponse res) {
		this.res = res;
	}

	
}
