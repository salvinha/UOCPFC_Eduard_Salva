package edu.uoc.pfc2012.edusalva.utils;

/**
 * Interface que defineix les constants que s'utilitzen al llarg de
 * l'aplicaci&oacute;.
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
public interface PFCConstants {

	/**
	 * Sistema de codificaci&oacute; de la resposta HTTP.
	 */
	public static final String HTTP_RESPONSE_ENCODING = "UTF-8";

	/**
	 * Tipus de contingut de la resposta HTTP.
	 */
	public static final String HTTP_RESPONSE_CONTENT_TYPE = "application/json;charset=UTF-8";

	/**
	 * Codi que es retorna al client quan els par&agrave;metres s&oacute;n incorrectes.
	 */
	public static final String RESPONSE_WRONG_PARAMETERS = "-1";

	/**
	 * Codi que es retorna al client quan no s'ha trobat resultats en una cerca.
	 */
	public static final String RESPONSE_SEARCH_FOUND_NOTHING = "-2";

	/**
	 * Codi que es retorna al client quan no s'ha trobat &agrave;udio per una petici&oacute; del client.
	 */
	public static final String RESPONSE_NO_AUDIO = "-3";


	/**
	 * Par&agrave;metre HTTP de text en catal&agrave;.
	 */
	public static final String HTTP_REQUEST_PARAM_TEXT_CA = "text_catala";

	/**
	 * Par&agrave;metre HTTP de text en japon&egrave;s.
	 */
	public static final String HTTP_REQUEST_PARAM_TEXT_JP = "text_japones";

	/**
	 * Par&agrave;metre HTTP de so en catal&agrave;.
	 */
	public static final String HTTP_REQUEST_PARAM_AUDIO_CA = "audio_catala";

	/**
	 * Par&agrave;metre HTTP de so en japon&egrave;s.
	 */
	public static final String HTTP_REQUEST_PARAM_AUDIO_JP = "audio_japones";

	/**
	 * Par&agrave;metre HTTP de text cercat.
	 */
	public static final String HTTP_REQUEST_PARAM_TEXT_SEARCH = "text_cerca";

	/**
	 * Par&agrave;metre HTTP d'idioma.
	 */
	public static final String HTTP_REQUEST_PARAM_IDIOMA = "idioma";

	/**
	 * Par&agrave;metre HTTP de ID de paraula.
	 */
	public static final String HTTP_REQUEST_PARAM_ID = "id";

	/**
	 * Par&agrave;metre HTTP de nombre m&agrave;xim de resultats.
	 */
	public static final String HTTP_REQUEST_PARAM_MAX_RESULTS = "max_results";

	/**
	 * Par&agrave;metre HTTP de codi de la llista d'estudi.
	 */
	public static final String HTTP_REQUEST_PARAM_LLISTA_ESTUDI = "llista";

	/**
	 * Par&agrave;metre HTTP de pronunciaci&oacute; en japon&egrave;s.
	 */
	public static final String HTTP_REQUEST_PARAM_PRON_JAP = "pronjap";

	/**
	 * Par&agrave;metre HTTP de pronunciaci&oacute; en catal&agrave;.
	 */
	public static final String HTTP_REQUEST_PARAM_PRON_CAT = "proncat";

	/**
	 * Codi de l'idioma catal&agrave;.
	 */
	public static final String LANG_CAT = "ca";

	/**
	 * Codi de l'idioma japon&egrave;s.
	 */
	public static final String LANG_JAP = "jp";


	/**
	 * M&egrave;tode HTTP POST.
	 */
	public static final String HTTP_REQUEST_POST = "POST";

	/**
	 * M&egrave;tode HTTP GET.
	 */
	public static final String HTTP_REQUEST_GET = "GET";

	/**
	 * Valor per defecte pel nombre m&agrave;xim de resultats en la llista de paraules.
	 */
	public static final int MAX_RESULTS_DEFAULT = 10;

	/**
	 * Codi del fitxer de propietats de configuraci&oacute; de la base de dades.
	 */
	public static final int KEY_PROPERTIES_DB_FILE = 1;

	/**
	 * Codi del fitxer de propietats de configuraci&oacute; del servidor.
	 */
	public static final int KEY_PROPERTIES_SERVER_FILE = 2;

	/**
	 * Nom del fitxer de propietats de configuraci&oacute; de la base de dades.
	 */
	public static final String PROPERTIES_DB_FILE = "/db.properties";

	/**
	 * Nom del fitxer de propietats de configuraci&oacute; del servidor.
	 */
	public static final String PROPERTIES_SERVER_FILE = "/pfc2012.server.properties";

	/**
	 * Clau per la propietat de host del servidor de base de dades.
	 */
	public static final String PROPERTY_DB_MONGO_HOST = "db.mongo.host";

	/**
	 * Clau per la propietat de port del servidor de base de dades.
	 */
	public static final String PROPERTY_DB_MONGO_PORT = "db.mongo.port";

	/**
	 * Clau per la propietat del nom de la base de dades.
	 */
	public static final String PROPERTY_DB_MONGO_DBNAME = "db.mongo.dbName";

	/**
	 * Clau per la propietat del nom de la col&#183;lecci&oacute; de la base de dades.
	 */
	public static final String PROPERTY_DB_MONGO_COLLECTION_NAME = "db.mongo.collectionName";


	/**
	 * Clau per la propietat de la ruta base de destinaci&oacute; dels fitxers MP3.
	 */
	public static final String PROPERTY_MP3_ROOT = "server.mp3.root";

	/**
	 * Clau per la propietat del nombre m&agrave;xim de resultats en la llista de paraules.
	 */
	public static final String PROPERTY_LIST_MAX_RESULTS = "server.list.maxResults";

	/**
	 * Nom del camp de la base de dades del text en catal&agrave;.
	 */
	public static final String DB_FIELD_TEXT_CA = "text_catala";

	/**
	 * Nom del camp de la base de dades del text en japon&egrave;s.
	 */
	public static final String DB_FIELD_TEXT_JP = "text_japones";

	/**
	 * Nom del camp de la base de dades del so en catal&agrave;.
	 */
	public static final String DB_FIELD_AUDIO_CA = "audio_catala";

	/**
	 * Nom del camp de la base de dades del so en japon&egrave;s.
	 */
	public static final String DB_FIELD_AUDIO_JP = "audio_japones";

	/**
	 * Nom del camp de la base de dades de la pronunciaci&oacute; en catal&agrave;.
	 */
	public static final String DB_FIELD_PRON_CAT = "proncat";

	/**
	 * Nom del camp de la base de dades de la pronunciaci&oacute; en japon&egrave;s.
	 */
	public static final String DB_FIELD_PRON_JAP = "pronjap";

	/**
	 * Nom del camp de la base de dades del codi de la llista a qu&egrave; pertany la paraula.
	 */
	public static final String DB_FIELD_LIST_ID = "llista";

	/**
	 * Nom del camp de la base de dades de l'ID de la paraula.
	 */
	public static final String DB_FIELD_ID = "_id";


	/**
	 * Ruta inexistent.
	 */
	public static final String PATH_NO_PATH = "/";

	/**
	 * Ruta per l'acci&oacute; de creaci&oacute; d'una nova paraula.
	 */
	public static final String PATH_CREATE_CONCEPTE_PARAULA = "/crear_concepte_paraula";

	/**
	 * Ruta per l'acció de cerca de paraula.
	 */
	public static final String PATH_SEARCH_CONCEPTE_PARAULA = "/search_concepte_paraula";

	/**
	 * Ruta per l'acci&oacute; de modificaci&oacute; d'una paraula.
	 */
	public static final String PATH_EDIT_CONCEPTE_PARAULA = "/editar_camp_concepte_paraula";

	/**
	 * Ruta per l'acci&oacute; de recuperaci&oacute; d'una paraula.
	 */
	public static final String PATH_GET_CONCEPTE_PARAULA = "/get_concepte_paraula";

	/**
	 * Ruta per l'acci&oacute; de recuperaci&oacute; del so d'una paraula.
	 */
	public static final String PATH_GET_SOUND = "/get_sound";

	/**
	 * Ruta per l'acci&oacute; de recuperaci&oacute; de la llista de paraules.
	 */
	public static final String PATH_LIST_WORDS = "/get_words";

	/**
	 * Ruta per l'acci&oacute; de l'esborrat de paraules.
	 */
	public static final String PATH_DELETE_WORD = "/delete_word";

	/**
	 * Ruta per l'acci&oacute; de recuperaci&oacute; d'una paraula d'una llista.
	 */
	public static final String PATH_GET_WORD_FROM_LIST = "/get_paraula";

	/**
	 * Ruta per l'acci&oacute; de restauraci&oacute; de la partida.
	 */
	public static final String PATH_RESET_GAME = "/reset_game";


	/**
	 * Par&agrave;metre Sencha FALSE.
	 */
	public static final String SENCHA_TOUCH_HTTP_PARAM_FALSE = "false";

	/**
	 * Par&agrave;metre Sencha _DC
	 */
	public static final String SENCHA_TOUCH_HTTP_PARAM_DC = "_dc";

	/**
	 * Par&agrave;metre Sencha CALLBACK
	 */
	public static final String SENCHA_TOUCH_HTTP_PARAM_CALLBACK = "callback";

}
