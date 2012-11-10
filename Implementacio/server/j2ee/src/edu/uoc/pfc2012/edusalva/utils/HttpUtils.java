package edu.uoc.pfc2012.edusalva.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.controller.exception.NoPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongPathException;

public abstract class HttpUtils {

	private static final Logger logger = Logger.getLogger(HttpUtils.class.getName());
	
	
	public static final void checkPath(HttpServletRequest req) throws WrongPathException {
		String path = req.getPathInfo();
		if (path == null || path.equals("/")) {
			throw new NoPathException();
		}
		
		if (path.equals("/crear_concepte_paraula")) {
			logger.info("Path is OK (" + path + ")");
			return;
		}
		
		throw new WrongPathException("Path is not valid: '" + path + "'");
	}
}
