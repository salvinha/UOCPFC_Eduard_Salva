package edu.uoc.pfc2012.edusalva.db;

import java.io.InvalidObjectException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.io.comparator.PathFileComparator;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.QueryBuilder;
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
			query.put(PFCConstants.DB_FIELD_TEXT_CA, k.getTextcat());
			query.put(PFCConstants.DB_FIELD_TEXT_JP, k.getTextjap());

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
			doc.put(PFCConstants.DB_FIELD_TEXT_CA, k.getTextcat());
			doc.put(PFCConstants.DB_FIELD_TEXT_JP, k.getTextjap());
			doc.put(PFCConstants.DB_FIELD_LIST_ID, k.getIdLlista());

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
				k.setTextcat(dbo.get(PFCConstants.DB_FIELD_TEXT_CA).toString());
				k.setTextjap(dbo.get(PFCConstants.DB_FIELD_TEXT_JP).toString());
				k.setPronjap(dbo.get(PFCConstants.DB_FIELD_PRON_JAP).toString());

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
			k.setTextcat(found.get(PFCConstants.DB_FIELD_TEXT_CA) != null ? found.get(PFCConstants.DB_FIELD_TEXT_CA).toString() : null);
			k.setTextjap(found.get(PFCConstants.DB_FIELD_TEXT_JP) != null ? found.get(PFCConstants.DB_FIELD_TEXT_JP).toString() : null);
			k.setPronjap(found.get(PFCConstants.DB_FIELD_PRON_JAP) != null ? found.get(PFCConstants.DB_FIELD_PRON_JAP).toString() : null);
			k.setIdLlista(found.get(PFCConstants.DB_FIELD_LIST_ID) != null ? found.get(PFCConstants.DB_FIELD_LIST_ID).toString() : null);


			String locationAudioCA = found.get("audio_catala") != null ? found.get("audio_catala").toString() : null;
			String locationAudioJP = found.get("audio_japones") != null ? found.get("audio_japones").toString() : null;

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
			newDoc.put(PFCConstants.DB_FIELD_ID, new ObjectId(k.getId()));
			if (k.getTextcat() != null) {
				newDoc.put(PFCConstants.DB_FIELD_TEXT_CA, k.getTextcat());
			}

			if (k.getTextjap() != null) {
				newDoc.put(PFCConstants.DB_FIELD_TEXT_JP, k.getTextjap());
			}

			if (k.getIdLlista() != null) {
				newDoc.put(PFCConstants.DB_FIELD_LIST_ID, k.getIdLlista());
			}

			if (k.getPronjap() != null) {
				newDoc.put(PFCConstants.DB_FIELD_PRON_JAP, k.getPronjap());
			}

			if (k.getAudioCatala() != null) {
				newDoc.put(PFCConstants.DB_FIELD_AUDIO_CA, k.getAudioCatala());
			}

			if (k.getAudioJapones() != null) {
				newDoc.put(PFCConstants.DB_FIELD_AUDIO_JP, k.getAudioJapones());
			}

			col.update(new BasicDBObject().append(PFCConstants.DB_FIELD_ID, new ObjectId(k.getId())), newDoc);
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
			DBObject search = new BasicDBObject(PFCConstants.DB_FIELD_ID, new ObjectId(id));
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

	public static KoncepteParaula getNextWordFromList(String idList, Set<String> forbidden) {
		DBCollection coll = null;

		try {
			coll = getDBCollection();

			// Searching by list ...
			BasicDBObject query = new BasicDBObject();
			query.append(PFCConstants.DB_FIELD_LIST_ID, idList);

			// Excluding forbidden words ...
			if (forbidden != null && forbidden.size() > 0) {
				ObjectId[] forbiddenArray = new ObjectId[forbidden.size()];

				int i = 0;
				for(String s: forbidden) {
					logger.info("Adding '" + s + "'");
					forbiddenArray[i++] = new ObjectId(s);
				}

				query.put(PFCConstants.DB_FIELD_ID, new BasicDBObject("$nin", forbiddenArray));
			}

			DBCursor csr = coll.find(query);

			int n = csr != null ?  csr.count() : 0;

			if (n > 0) {
				int r = (int) (Math.random() * n);
				csr.skip(r);
				DBObject dbo = csr.next();
				if (dbo != null) {
					KoncepteParaula k = new KoncepteParaula();

					if (dbo.get(PFCConstants.DB_FIELD_ID) == null) {
						throw new Exception("Word has no ID!");
					}

					k.setId(dbo.get(PFCConstants.DB_FIELD_ID).toString());

					k.setTextcat(dbo.get(PFCConstants.DB_FIELD_TEXT_CA) != null ? dbo.get(PFCConstants.DB_FIELD_TEXT_CA).toString() : null);
					k.setTextjap(dbo.get(PFCConstants.DB_FIELD_TEXT_JP) != null ? dbo.get(PFCConstants.DB_FIELD_TEXT_JP).toString() : null);
					k.setPronjap(dbo.get(PFCConstants.DB_FIELD_PRON_JAP) != null ? dbo.get(PFCConstants.DB_FIELD_PRON_JAP).toString() : null);
					k.setProncat(null);
					k.setIdLlista(idList);
					return k;
				} else {
					throw new Exception("No data object!");
				}
			} else {
				throw new Exception("No words!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return null;
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

					String id = (dbo.get(PFCConstants.DB_FIELD_ID) != null) ? dbo.get(PFCConstants.DB_FIELD_ID).toString() : null;
					String textCat = (dbo.get(PFCConstants.DB_FIELD_TEXT_CA) != null) ? dbo.get(PFCConstants.DB_FIELD_TEXT_CA).toString() : null;
					String textJap = (dbo.get(PFCConstants.DB_FIELD_TEXT_JP) != null) ? dbo.get(PFCConstants.DB_FIELD_TEXT_JP).toString() : null;
					String pronJap = (dbo.get(PFCConstants.DB_FIELD_PRON_JAP) != null) ? dbo.get(PFCConstants.DB_FIELD_PRON_JAP).toString() : null;
					String llista = (dbo.get(PFCConstants.DB_FIELD_LIST_ID) != null) ? dbo.get(PFCConstants.DB_FIELD_LIST_ID).toString() : null;

					k.setId(id);
					k.setTextcat(textCat);
					k.setTextjap(textJap);
					k.setPronjap(pronJap);
					k.setIdLlista(llista);

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