package edu.uoc.pfc2012.edusalva.db;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
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
import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;
import edu.uoc.pfc2012.edusalva.utils.PFCUtils;

/**
 * Classe que cont&eacute; m&egrave;todes est&agrave;tics per l'acc&eacute;s a la Base de Dades.
 *
 * <p>
 * La base de dades que s'utilitza &eacute;s MongoDB:
 * <a href="http://www.mongodb.org">http://www.mongodb.org</a>
 * .
 * </p>
 *
 * <p>
 * Projecte Final de Carrera - Desenvolupament d'aplicacions m&#242;bils en HTML5
 * </p>
 *
 * <p>
 * Data: Gener de 2013
 * </p>
 *
 * @author Eduard Capell Brufau (<a href="mailto:ecapell@uoc.edu">ecapell@uoc.edu</a>)
 * @author Salvador Lorca Sans (<a href="salvinha@uoc.edu">salvinha@uoc.edu</a>)
 *
 * @version 1.0
 *
 */
public class DBController {

	/**
	 * Objecte Logger.
	 */
	private static final Logger logger = Logger.getLogger(DBController.class.getName());

	/**
	 * Objecte de tipus
	 * <code>java.util.Properties</code>
	 * , que s'utilitza per tal d'obtenir la configuraci&oacute; per l'acc&eacute;s
	 * a la base de dades.
	 *
	 * @see java.util.Properties
	 */
	private static Properties props = null;

	/**
	 * Objecte que representa la inst&agrave;ncia de la base de dades amb la qual
	 * es connecta l'aplicaci&oacute;.
	 * @see Mongo
	 */
	private static Mongo m = null;

	/**
	 * M&egrave;tode que serveix per establir una connexi&oacute; amb la base de dades.
	 * @return Un objecte de tipus
	 * <code>DBCollection</code>
	 * que representa la connexi&oacute; amb la base de dades.
	 * @throws Exception Si es produeix algun error en la connexió amb la
	 * base de dades (no disponible, usuari/contrasenya erronis, etc.).
	 */
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


	/**
	 * M&egrave;tode que tanca la connexi&oacute; amb la base de dades.
	 */
	private static void closeDB() {
		m.close();
	}


	/**
	 * M&egrave;tode que verifica si una paraula (koncepte) existeix a la base de dades. Es
	 * considera que un koncepte
	 * <code>k</code>
	 * ja existeix si i nom&eacute;s si tant la paraula en catal&agrave; com la paraula en
	 * japon&egrave;s son simult&agrave;niament a un element de la base de dades.
	 * @param k El koncepte a comprovar.
	 * @return Cert si el koncepte existeix a la base de dades, fals en
	 * cas contrari.
	 */
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


	/**
	 * Mètode que crea un nou koncepte a la base de dades.
	 * Un cop creat el koncepte, es retorna l'ID del document recent creat.
	 * @param k Koncepte a introduir a la base de dades.
	 * @return Cadena de caràcters, amb l'ID de la paraula introduïda.
	 */
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


	/**
	 * M&egrave;tode que obt&eacute; un koncepte, a partir de la seva representaci&oacute; escrita, i
	 * de l'idioma en qu&egrave; est&agrave; aquesta representaci&oacute;.
	 * @param text Cadena de car&agrave;cters que indica la paraula escrita.
	 * @param idioma Idioma en qu&egrave; est&agrave; escrit el text.
	 * @return Koncepte trobat a la base de dades que compleix les dues
	 * condicions (text i idioma del text). Si no es troba cap resultat, es
	 * retornar&agrave;
	 * <code>null</code>
	 * .
	 */
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


	/**
	 * M&egrave;tode que fa una cerca de koncepte a partir de l'ID d'aquest.
	 * @param id ID del koncepte que s'est&agrave; cercant.
	 * @return Koncepte que t&eacute; l'ID especificat per par&agrave;metre. Si no se'n
	 * troba cap, es retornar&agrave;
	 * <code>null</code>
	 * .
	 * @throws Exception
	 */
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
			logger.info("Not found!");
		} catch (Exception e) {
			logger.error("Error searching for object with id '" + id + "'");
			return null;
		} finally {
			closeDB();
		}

		return k;
	}



	/**
	 * M&egrave;tode que actualitza un koncepte amb els valors del nou objecte
	 * rebut per par&agrave;metre.
	 * @param k Koncepte que cont&eacute; els nous valors a escriure a la base de dades.
	 */
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

	/**
	 * M&egrave;tode que esborra un koncepte de la base de dades. L'esborrat que es fa
	 * &eacute;s f&iacute;sic, &eacute;s a dir, no es pot recuperar un registre un cop esborrat.
	 * @param id ID del concepte a esborrar.
	 * @return Cert si s'ha pogut esborrar el koncepte amb l'ID indicat. Fals
	 * en cas contrari (normalment perqu&egrave; no hi ha cap koncepte amb l'ID).
	 */
	public static boolean deleteKoncept(String id) {
		DBCollection coll = null;
		try {
			coll = getDBCollection();
			DBObject search = new BasicDBObject(PFCConstants.DB_FIELD_ID, new ObjectId(id));
			DBObject found = coll.findOne(search);
			if (found != null) {
				coll.remove(found);
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

	/**
	 * M&egrave;tode que cerca una paraula a l'atzar, d'entre les que compleixen la
	 * condici&oacute; que pertanyen a una llista especificada per par&agrave;metre, i que no
	 * estan entre un conjunt de paraules excloses de la cerca.
	 * @param idList Llista a la qual han de pert&agrave;nyer les paraules que se cercaran.
	 * @param forbidden Paraules excloses de la cerca.
	 * @return Un koncepte que pertany a la llista indicada, i que no &eacute;s a les
	 * excloses. Es retorna
	 * <code>null</code>
	 * si no es troba cap paraula que compleixi amb les condicions requerides.
	 */
	public static KoncepteParaula getNextWordFromList(String idList, Set<String> forbidden) {
		DBCollection coll = null;

		try {
			coll = getDBCollection();

			// Criteri de pertanyer a la llista.
			BasicDBObject query = new BasicDBObject();
			query.append(PFCConstants.DB_FIELD_LIST_ID, idList);

			// Excloent les prohibides.
			if (forbidden != null && forbidden.size() > 0) {
				ObjectId[] forbiddenArray = new ObjectId[forbidden.size()];

				int i = 0;
				for(String s: forbidden) {
					forbiddenArray[i++] = new ObjectId(s);
				}

				// Clausula $nin (NOT IN).
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
			logger.warn("Could not complete search for next word: " + e.getMessage());
		} finally {
			closeDB();
		}

		return null;
	}


	/**
	 * Mètode que retorna una llista de paraules a la base de dades, amb un límit en el nombre
	 * de paraules a retornar.
	 * @param maxResults Valor enter amb el nombre màxim de paraules a retornar.
	 * @return Una llista de paraules (koncepte), que mai no tindrà una
	 * cardinalitat superior a
	 * <code>maxResults</code>.
	 */
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