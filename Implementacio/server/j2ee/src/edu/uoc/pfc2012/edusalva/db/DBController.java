package edu.uoc.pfc2012.edusalva.db;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

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
		m.setWriteConcern(WriteConcern.SAFE);
		DB db = m.getDB(props.getProperty("db.mongo.dbName"));
		DBCollection coll = db.getCollection(props.getProperty("db.mongo.collectionName"));
		
		return coll;
	}
	
	
	private static void closeDB() {
		// TODO Auto-generated method stub
		m.close();
	}
	
	public static final boolean konceptExists(KoncepteParaula k) {
		DBCollection col;
		try {
			BasicDBObject query = new BasicDBObject();
			query.put("text_catala", k.getTextCatala());
			query.put("text_japones", k.getTextJapones());
			
			col = getDBCollection();
			
			DBCursor cursor = col.find(query);
			if (cursor != null && cursor.hasNext()) {
				logger.info("Found it!");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		} finally {
			closeDB();
		}
		
		logger.info("Not found.");
		return false;
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
			logger.info("ID = '" + id.toString() + "'");
			
			return id.toString();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeDB();
		}
		
		return null;
	}


	public static KoncepteParaula getKoncept(String text, String idioma) {
		DBCollection coll;
		logger.info("Searching koncept...");
		try {
			coll = getDBCollection();
			logger.info("Collection: " + coll);
			KoncepteParaula k = new KoncepteParaula();
			
			// TODO Cridar funcions del driver MongoDB per trobar un element.
			k.setId("19287398127398");
			k.setTextCatala("El text en català");
			k.setTextJapones("製品を実");
			
			return k;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeDB();
		}
		
		return null;
	}

}
