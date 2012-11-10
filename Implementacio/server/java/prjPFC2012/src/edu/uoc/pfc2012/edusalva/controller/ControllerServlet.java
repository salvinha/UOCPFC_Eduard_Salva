package edu.uoc.pfc2012.edusalva.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.controller.exception.NoPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongRequestException;

/**
 * Controller servlet, this is the main and only entry point to the server application.
 * This servlet receives any request and dispatches it appropriately.
 * @author Eduard Capell Brufau (ecapell@uoc.edu)
 * @author Salvador Lorca sans (salvinha@uoc.edu)
 */
public class ControllerServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1866281077747194045L;
	
	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(ControllerServlet.class.getName());

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			String path = getPath(req);
			logger.info("PATH = '" + path + "'");
		} catch (NoPathException e) {
			// TODO Handle.
			logger.error("No path!");
		} catch (WrongPathException e) {
			// TODO Handle.
			logger.error("Wrong path!");
		} catch (Exception e) {
			// TODO Handle.
			logger.error("Unknown!!!");
		}
	}

	
	
	private String getPath(HttpServletRequest req) throws WrongPathException {
		String path = req.getPathInfo();
		if (path == null || path.equals("/")) {
			throw new NoPathException();
		}
		
		return path;
	}
	
}
