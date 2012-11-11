package edu.uoc.pfc2012.edusalva.db;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;

public class DBController {

	private static final Logger logger = Logger.getLogger(DBController.class.getName());
	private static Properties props = null;
	private static Mongo m = null;
	
	private static DBCollection getDBCollection() throws Exception {
		if (props == null) {
			props = new Properties();
			try {
				props.load(DBController.class.getClassLoader().getResourceAsStream("/db.properties"));
			} catch (Exception e) {
				logger.error("Cannot load properties!");
			}
			
			logger.info("HOST = " + props.getProperty("db.mongo.host"));
		}
		m = new Mongo(props.getProperty("db.mongo.host"), Integer.parseInt(props.getProperty("db.mongo.port")));
		DB db = m.getDB(props.getProperty("db.mongo.dbName"));
		DBCollection coll = db.getCollection(props.getProperty("db.mongo.collectionName"));
		
		return coll;
	}
	
	
	private static void closeDB() {
		// TODO Auto-generated method stub
		m.close();
	}
	
	
	
	public static final String createKoncept(KoncepteParaula k) {
		DBCollection coll;
		try {
			coll = getDBCollection();
			
			BasicDBObject doc = new BasicDBObject();
			doc.put("text_catala", k.getTextCatala());
			doc.put("text_japones", k.getTextJapones());
			doc.put("audio_catala", null);
			doc.put("audio_japones", null);
			coll.insert(doc);
			ObjectId id = (ObjectId)doc.get( "_id" );
			
			return id.toString();
			
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		} finally {
			closeDB();
		}
		
		return null;
	}

}
