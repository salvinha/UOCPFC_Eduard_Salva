package edu.uoc.pfc2012.edusalva.controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
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
		
		logger.info("CONTENT_TYPE = '" + req.getContentType() + "'");
		
		logger.info("JAP = '" + japones + "'");
	
		String msg = null;
		
//		msg = db(req);
		
		msg = my(req);
		logger.info("MSG = '" + msg + "'");
		
		res.setCharacterEncoding("UTF-8");
		
		Writer w = res.getWriter();
		w.write(msg);
		w.flush();
		
	}
	
	private String my(HttpServletRequest req) {
		String msg = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jap","edu", "edu");
//			String sql = "SELECT japones FROM paraula WHERE id = 1";
//			ResultSet rs = connection.createStatement().executeQuery(sql);
//			rs.next();
//			logger.info("BD = '" + rs.getString(1) + "'");
//			msg = rs.getString(1);
//			logger.info("Connected: " + connection);
//			rs.close();
			
			String sql = "INSERT INTO paraula (id, catala, japones) values (NULL, ?, ?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, req.getParameter("text_catala"));
			pst.setString(2, req.getParameter("text_japones"));
			pst.executeUpdate();
			pst.close();
			connection.close();
			msg = ".";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	private String  db(HttpServletRequest req) throws IOException {
		Mongo m = new Mongo("192.168.1.34", 27017);
		DB db = m.getDB("test");
		DBCollection coll = db.getCollection("things");
		
		logger.info("Encoding: " + req.getCharacterEncoding());

		String msg = "No response";
		
//		insert(coll, catala, japones);
//		msg = query(coll);
		
		m.close();
		
		return msg;

	}
	private void insert(DBCollection coll, String catala, String japones) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("text_catala", catala);
		doc.put("text_japones", japones);
		doc.put("audio_catala", null);
		doc.put("audio_japones", null);
		coll.insert(doc);
	}
	
	private String query(DBCollection coll) {
		DBObject doc = coll.findOne();
		return "RESPONSE: " + doc.get("text_japones");
	}
}

