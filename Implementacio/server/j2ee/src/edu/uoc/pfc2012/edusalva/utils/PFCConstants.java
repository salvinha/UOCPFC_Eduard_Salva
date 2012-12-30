package edu.uoc.pfc2012.edusalva.utils;

public interface PFCConstants {

	public static final String HTTP_RESPONSE_ENCODING = "UTF-8";
	public static final String HTTP_RESPONSE_CONTENT_TYPE = "application/json;charset=UTF-8";

	public static final String RESPONSE_WRONG_PARAMETERS = "-1";
	public static final String RESPONSE_SEARCH_FOUND_NOTHING = "-2";
	public static final String RESPONSE_NO_AUDIO = "-3";
	public static final String RESPONSE_WORD_ALREADY_EXISTS = "-1";
	public static final String RESPONSE_DELETE_WORD_OK = "0";
	public static final String RESPONSE_DELETE_WORD_KO = "-1";

	public static final String HTTP_REQUEST_PARAM_TEXT_CA = "text_catala";
	public static final String HTTP_REQUEST_PARAM_TEXT_JP = "text_japones";
	public static final String HTTP_REQUEST_PARAM_AUDIO_CA = "audio_catala";
	public static final String HTTP_REQUEST_PARAM_AUDIO_JP = "audio_japones";
	public static final String HTTP_REQUEST_PARAM_TEXT_SEARCH = "text_cerca";
	public static final String HTTP_REQUEST_PARAM_IDIOMA = "idioma";
	public static final String HTTP_REQUEST_PARAM_ID = "id";
	public static final String HTTP_REQUEST_PARAM_MAX_RESULTS = "max_results";
	public static final String HTTP_REQUEST_PARAM_LLISTA_ESTUDI = "llista";
	public static final String HTTP_REQUEST_PARAM_PRON_JAP = "pronjap";
	public static final String HTTP_REQUEST_PARAM_PRON_CAT = "proncat";


	public static final String LANG_CAT = "ca";
	public static final String LANG_JAP = "jp";

	public static final String HTTP_REQUEST_POST = "POST";
	public static final String HTTP_REQUEST_GET = "GET";

	public static final int MAX_RESULTS_DEFAULT = 10;

	public static final int KEY_PROPERTIES_DB_FILE = 1;
	public static final int KEY_PROPERTIES_SERVER_FILE = 2;
	public static final String PROPERTIES_DB_FILE = "/db.properties";
	public static final String PROPERTIES_SERVER_FILE = "/pfc2012.server.properties";

	public static final String PROPERTY_DB_MONGO_HOST = "db.mongo.host";
	public static final String PROPERTY_DB_MONGO_PORT = "db.mongo.port";
	public static final String PROPERTY_DB_MONGO_DBNAME = "db.mongo.dbName";
	public static final String PROPERTY_DB_MONGO_COLLECTION_NAME = "db.mongo.collectionName";

	public static final String PROPERTY_MP3_ROOT = "server.mp3.root";
	public static final String PROPERTY_LIST_MAX_RESULTS = "server.list.maxResults";

	public static final String DB_FIELD_TEXT_CA = "text_catala";
	public static final String DB_FIELD_TEXT_JP = "text_japones";
	public static final String DB_FIELD_AUDIO_CA = "audio_catala";
	public static final String DB_FIELD_AUDIO_JP = "audio_japones";
	public static final String DB_FIELD_PRON_CAT = "proncat";
	public static final String DB_FIELD_PRON_JAP = "pronjap";
	public static final String DB_FIELD_LIST_ID = "llista";

	public static final String DB_FIELD_ID = "_id";

	public static final String PATH_NO_PATH = "/";
	public static final String PATH_CREATE_CONCEPTE_PARAULA = "/crear_concepte_paraula";
	public static final String PATH_SEARCH_CONCEPTE_PARAULA = "/search_concepte_paraula";
	public static final String PATH_EDIT_CONCEPTE_PARAULA = "/editar_camp_concepte_paraula";
	public static final String PATH_GET_CONCEPTE_PARAULA = "/get_concepte_paraula";
	public static final String PATH_GET_SOUND = "/get_sound";
	public static final String PATH_LIST_WORDS = "/get_words"; // TODO Document!
	public static final String PATH_DELETE_WORD = "/delete_word";
	public static final String PATH_GET_WORD_FROM_LIST = "/get_paraula";
	public static final String PATH_RESET_GAME = "/reset_game";

	public static final String SENCHA_TOUCH_HTTP_PARAM_FALSE = "false";
	public static final String SENCHA_TOUCH_HTTP_PARAM_DC = "_dc";
	public static final String SENCHA_TOUCH_HTTP_PARAM_CALLBACK = "callback";

}
