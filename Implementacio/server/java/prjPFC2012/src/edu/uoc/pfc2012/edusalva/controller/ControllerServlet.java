package edu.uoc.pfc2012.edusalva.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
		logger.info("OK.");
	}
	
	
}
