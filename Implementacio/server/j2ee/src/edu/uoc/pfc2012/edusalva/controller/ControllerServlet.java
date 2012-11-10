package edu.uoc.pfc2012.edusalva.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import edu.uoc.pfc2012.edusalva.controller.exception.NoPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongRequestException;
import edu.uoc.pfc2012.edusalva.utils.HttpUtils;

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
		
		String catala = req.getParameter("text_catala");
		String japones = req.getParameter("text_japones");
		
		logger.info("JAP = '" + japones + "'");
	
		Mongo m = new Mongo("192.168.1.34", 27017);
		DB db = m.getDB("test");
		
		BasicDBObject doc = new BasicDBObject();
		DBCollection coll = db.getCollection("things");
		doc.put("text_catala", catala);
		doc.put("text_japones", japones);
		doc.put("audio_catala", null);
		doc.put("audio_japones", null);
		
		coll.insert(doc);
		
		m.close();
		
		Writer w = res.getWriter();
		w.write("Response OK.");
		w.flush();
		
	}
	
	
}
