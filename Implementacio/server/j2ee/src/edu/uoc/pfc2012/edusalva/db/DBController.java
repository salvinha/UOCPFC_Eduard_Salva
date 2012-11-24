package edu.uoc.pfc2012.edusalva.db;

import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;
import edu.uoc.pfc2012.edusalva.utils.PFCUtils;

public class DBController {

	private static final Logger logger = Logger.getLogger(DBController.class.getName());
	private static Properties props = null;
	private static Mongo m = null;
	
	private static DBCollection getDBCollection() throws Exception {
		if (props == null) {
			try {
				props = PFCUtils.getProperties(PFCConstants.KEY_PROPERTIES_DB_FILE);
			} catch (Exception e) {
				throw new Exception("Unable to load DB properties file.");
			}
		}
		try {
			m = new Mongo(
				props.getProperty(PFCConstants.PROPERTY_DB_MONGO_HOST), 
				Integer.parseInt(props.getProperty(PFCConstants.PROPERTY_DB_MONGO_PORT)));
		} catch (NumberFormatException e) {
			// Wrong port number. TODO Handle
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// Problems with DB host. TODO Handle
			e.printStackTrace();
		}
		
		m.setWriteConcern(WriteConcern.SAFE);
		DB db = m.getDB(props.getProperty(PFCConstants.PROPERTY_DB_MONGO_DBNAME));
		DBCollection coll = db.getCollection(props.getProperty(PFCConstants.PROPERTY_DB_MONGO_COLLECTION_NAME));
		
		return coll;
	}
	
	
	private static void closeDB() {
		m.close();
	}
	
	public static final boolean konceptExists(KoncepteParaula k) {
		DBCollection col;
		try {
			BasicDBObject query = new BasicDBObject();
			query.put(PFCConstants.DB_FIELD_TEXT_CA, k.getTextCatala());
			query.put(PFCConstants.DB_FIELD_TEXT_JP, k.getTextJapones());
			
			col = getDBCollection();
			
			DBCursor cursor = col.find(query);
			if (cursor != null && cursor.hasNext()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		} finally {
			closeDB();
		}
		
		return false;
	}
	
	
	public static final String createKoncept(KoncepteParaula k) {
		DBCollection coll;
		try {
			coll = getDBCollection();
			
			BasicDBObject doc = new BasicDBObject();
			doc.put(PFCConstants.DB_FIELD_TEXT_CA, k.getTextCatala());
			doc.put(PFCConstants.DB_FIELD_TEXT_JP, k.getTextJapones());
			doc.put(PFCConstants.DB_FIELD_AUDIO_CA, null);
			doc.put(PFCConstants.DB_FIELD_AUDIO_JP, null);
			coll.insert(doc);
			ObjectId id = (ObjectId)doc.get(PFCConstants.DB_FIELD_ID);
			
			return id.toString();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeDB();
		}
		
		return null;
	}


	public static KoncepteParaula getKoncept(String text, String idioma) {
		DBCollection col;
		try {
			col = getDBCollection();
			BasicDBObject query = new BasicDBObject();
			String fieldText = null;
			if (PFCConstants.LANG_CAT.equals(idioma)) {
				fieldText = PFCConstants.DB_FIELD_TEXT_CA;
			} else if (PFCConstants.LANG_CAT.equals(idioma)) {
				fieldText = PFCConstants.DB_FIELD_TEXT_JP;
			} else {
				return null;
			}
			query.put(fieldText, text);
			col = getDBCollection();
			
			DBCursor cursor = col.find(query);
			if (cursor != null && cursor.hasNext()) {
				DBObject dbo = cursor.next();
				KoncepteParaula k = new KoncepteParaula();
				k.setId(dbo.get(PFCConstants.DB_FIELD_ID).toString());
				k.setTextCatala(dbo.get(PFCConstants.DB_FIELD_TEXT_CA).toString());
				k.setTextJapones(dbo.get(PFCConstants.DB_FIELD_TEXT_JP).toString());
				k.setAudioCatala(null);
				k.setAudioJapones(null);

				return k;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeDB();
		}
		
		return null;
	}

}
