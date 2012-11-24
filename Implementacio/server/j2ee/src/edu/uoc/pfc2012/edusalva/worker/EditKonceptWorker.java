package edu.uoc.pfc2012.edusalva.worker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class EditKonceptWorker extends AbstractWorker {

	private static final Logger logger = Logger.getLogger(EditKonceptWorker.class.getName());

	
	
	public EditKonceptWorker() {
		super();
	}

	public EditKonceptWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		logger.debug("Here I am.");
	}

	@Override
	public void processRequest() {
	}

}
