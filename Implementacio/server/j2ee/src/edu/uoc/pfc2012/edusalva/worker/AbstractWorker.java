package edu.uoc.pfc2012.edusalva.worker;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;

public abstract class AbstractWorker {
	private static final Logger logger = Logger.getLogger(AbstractWorker.class.getName());
	
	
	private Map<String, String[]> params;
	private String path;
	private HttpServletRequest req;
	private HttpServletResponse res;

	public abstract void processRequest();
	
	public void writeResponse(ResponseBean rb) {
		if (rb == null) {
			return;
		}
		
		ObjectMapper m = new ObjectMapper();
//		Set<String> set = new HashSet<String>();
//		set.add("{id}");
//		FilterProvider filters = new SimpleFilterProvider().addFilter("id_only", SimpleBeanPropertyFilter.filterOutAllExcept(set));
//		m.setFilters(filters);
//		
		try {
			Writer w = getRes().getWriter();
			m.writeValue(w, rb);
			w.flush();
			w.close();
		} catch (IOException e) {
			logger.error("Error writing response...");
		}
	}

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
