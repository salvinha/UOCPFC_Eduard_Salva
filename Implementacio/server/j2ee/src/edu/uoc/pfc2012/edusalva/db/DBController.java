package edu.uoc.pfc2012.edusalva.db;

import java.io.InvalidObjectException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

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

			if (k.getAudioCatala() != null) {
				doc.put(PFCConstants.DB_FIELD_AUDIO_CA, null);				
			}

			if (k.getAudioJapones() != null) {
				doc.put(PFCConstants.DB_FIELD_AUDIO_JP, null);				
			}

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
			} else if (PFCConstants.LANG_JAP.equals(idioma)) {
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
				
				if (dbo.get(PFCConstants.DB_FIELD_AUDIO_CA) != null) {
					k.setAudioCatala(PFCUtils.getBase64FromFile(dbo.get(PFCConstants.DB_FIELD_AUDIO_CA).toString()));
				}
				
				if (dbo.get(PFCConstants.DB_FIELD_AUDIO_JP) != null) {
					k.setAudioJapones(PFCUtils.getBase64FromFile(dbo.get(PFCConstants.DB_FIELD_AUDIO_JP).toString()));
				}
				
				return k;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return null;
	}


	public static KoncepteParaula findById(String id) throws Exception {
		DBCollection col = null;
		KoncepteParaula k = null;
		
		try {
			col = getDBCollection();
			DBObject search = new BasicDBObject("_id", new ObjectId(id));
			DBObject found = col.findOne(search);

			if(found == null) {
				throw new IllegalArgumentException("No objects found with ID " + id);
			}
			
			k = new KoncepteParaula();
			k.setId(id);
			k.setTextCatala(found.get("text_catala").toString());
			k.setTextJapones(found.get("text_japones").toString());
			
			String locationAudioCA = found.get("audio_catala").toString();
			String locationAudioJP = found.get("audio_japones").toString();
			
			if (locationAudioCA != null) {
				k.setAudioCatala(PFCUtils.getBase64FromFile(locationAudioCA));
			}
			
			if (locationAudioJP != null) {
				k.setAudioJapones(PFCUtils.getBase64FromFile(locationAudioJP));
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.info("Not found!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error searching for object with id '" + id + "'");
		} finally {
			closeDB();
		}
		
		return k;
	}


	public static void update(KoncepteParaula k) {
		DBCollection col = null;
		
		try {
			col = getDBCollection();
			BasicDBObject newDoc = new BasicDBObject();
			newDoc.put("_id", new ObjectId(k.getId()));
			if (k.getTextCatala() != null) {
				newDoc.put("text_catala", k.getTextCatala());				
			}

			if (k.getTextJapones() != null) {
				newDoc.put("text_japones", k.getTextJapones());				
			}

			if (k.getAudioCatala() != null) {
				newDoc.put("audio_catala", k.getAudioCatala());				
			}

			if (k.getAudioJapones() != null) {
				newDoc.put("audio_japones", k.getAudioJapones());				
			}
			
			col.update(new BasicDBObject().append("_id", new ObjectId(k.getId())), newDoc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}

	public static boolean deleteKoncept(String id) {
		DBCollection coll = null;
		try {
			coll = getDBCollection();
			DBObject search = new BasicDBObject("_id", new ObjectId(id));
			DBObject found = coll.findOne(search);
			if (found != null) {
				WriteResult res =  coll.remove(found);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
	}

	public static List<KoncepteParaula> getWordList(int maxResults) {
		DBCollection coll = null;
		try {
			coll = getDBCollection();
			List<KoncepteParaula> list = new Vector<KoncepteParaula>();
			
			BasicDBObject query = new BasicDBObject();
			DBCursor cursor = coll.find(query).batchSize(maxResults);
			if (cursor != null) {
				while (cursor.hasNext()) {
					DBObject dbo = cursor.next();
					KoncepteParaula k = new KoncepteParaula();
					
					k.setId(dbo.get(PFCConstants.DB_FIELD_ID).toString());
					k.setTextCatala(dbo.get(PFCConstants.DB_FIELD_TEXT_CA).toString());
					k.setTextJapones(dbo.get(PFCConstants.DB_FIELD_TEXT_JP).toString());
					if (dbo.get(PFCConstants.DB_FIELD_AUDIO_CA) != null) {
						k.setAudioCatala(PFCUtils.getBase64FromFile(dbo.get(PFCConstants.DB_FIELD_AUDIO_CA).toString()));
					}
					if (dbo.get(PFCConstants.DB_FIELD_AUDIO_JP) != null) {
						k.setAudioJapones(PFCUtils.getBase64FromFile(dbo.get(PFCConstants.DB_FIELD_AUDIO_JP).toString()));
					}
					
					list.add(k);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return null;
	}

}