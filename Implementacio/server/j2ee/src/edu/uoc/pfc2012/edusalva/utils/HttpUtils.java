package edu.uoc.pfc2012.edusalva.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.controller.exception.NoPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongMethodException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongPathException;

public abstract class HttpUtils {

	private static final Logger logger = Logger.getLogger(HttpUtils.class.getName());
	
	
	public static final void checkPath(HttpServletRequest req) throws WrongPathException {
		String path = req.getPathInfo();
		if (path == null || path.equals("/")) {
			throw new NoPathException();
		}
		
		if (path.equals("/crear_concepte_paraula")) {
			return;
		}
		
		throw new WrongPathException("Path is not valid: '" + path + "'");
	}
	
	
	public static final void checkRequestMethod(HttpServletRequest req) throws WrongMethodException {
		if (true)
			return;
		
		if (req == null) {
			throw new WrongMethodException("Request is null!");
		}
		
		if (req.getMethod() == null || req.getMethod().trim().length() == 0) {
			throw new WrongMethodException("Request method is null or empty!");
		}
		
		String method = req.getMethod();
		if (!("POST".equalsIgnoreCase(method))) {
			throw new WrongMethodException("Method not accepted: " + method);
		}
	}
}
