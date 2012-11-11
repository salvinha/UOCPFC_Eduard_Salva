package edu.uoc.pfc2012.edusalva.db;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;

public class DBController {

	private static final Logger logger = Logger.getLogger(DBController.class.getName());
	private static Mongo m = null;
	
	private static DBCollection getDBCollection() throws Exception {
		m = new Mongo("localhost", 27017);
		DB db = m.getDB("test");
		DBCollection coll = db.getCollection("things");
		
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
