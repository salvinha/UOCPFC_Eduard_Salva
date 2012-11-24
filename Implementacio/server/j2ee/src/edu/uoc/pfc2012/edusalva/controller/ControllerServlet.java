package edu.uoc.pfc2012.edusalva.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import edu.uoc.pfc2012.edusalva.worker.AbstractWorker;
import edu.uoc.pfc2012.edusalva.worker.WorkerFactory;

/**
 * This is the main and only entry point to the server application.
 * <p>
 * This servlet receives any request and dispatches it appropriately.
 * </p>
 * <p>
 * Requests received by this Servlet have already been checked (ie only valid requests are
 * allowed through) by the <code>ServerActionFilter</code> Filter.
 * </p>
 * <p>
 * All requests are encoded in UTF-8, this is ensured by the <code>EncodingFilter</code> Filter.
 * </p>
 * 
 * @author Eduard Capell Brufau (<a href="mailto:ecapell@uoc.edu">ecapell@uoc.edu</a>)
 * @author Salvador Lorca sans (<a href="mailto:salvinha@uoc.edu">salvinha@uoc.edu</a>)
 * @see edu.uoc.pfc2012.edusalva.filter.ServerActionFilter
 * @see edu.uoc.pfc2012.edusalva.filter.EncodingFilter
 */
public class ControllerServlet extends HttpServlet {
	
	/**
	 * Long value with the servial version UID for this class.
	 */
	private static final long serialVersionUID = 1866281077747194045L;
	
	/**
	 * Logger object.
	 */
	private static final Logger logger = Logger.getLogger(ControllerServlet.class.getName());


	/**
	 * This is the javadoc for the service() method.
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}
	

	private void processRequest(HttpServletRequest req, HttpServletResponse res) {
		AbstractWorker worker = WorkerFactory.getWorker(req, res);
		worker.processRequest();
	}

}

