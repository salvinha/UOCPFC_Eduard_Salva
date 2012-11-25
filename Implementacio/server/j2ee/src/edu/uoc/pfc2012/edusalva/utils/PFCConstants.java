package edu.uoc.pfc2012.edusalva.utils;

public interface PFCConstants {
	
	public static final String HTTP_RESPONSE_ENCODING = "UTF-8";
	public static final String HTTP_RESPONSE_CONTENT_TYPE = "application/json;charset=UTF-8";
	
	public static final String RESPONSE_WRONG_PARAMETERS = "-1";
	public static final String RESPONSE_SEARCH_FOUND_NOTHING = "-2";
	
	public static final String HTTP_REQUEST_PARAM_TEXT_CA = "text_catala";
	public static final String HTTP_REQUEST_PARAM_TEXT_JP = "text_japones";
	public static final String HTTP_REQUEST_PARAM_AUDIO_CA = "audio_catala";
	public static final String HTTP_REQUEST_PARAM_AUDIO_JP = "audio_japones";
	public static final String HTTP_REQUEST_PARAM_TEXT_SEARCH = "text_cerca";
	public static final String HTTP_REQUEST_PARAM_IDIOMA = "idioma";
	public static final String HTTP_REQUEST_PARAM_ID = "id";
	
	public static final String LANG_CAT = "ca";
	public static final String LANG_JAP = "jp";
	
	public static final String HTTP_REQUEST_POST = "POST";
	
	public static final int KEY_PROPERTIES_DB_FILE = 1;
	public static final int KEY_PROPERTIES_SERVER_FILE = 2;
	public static final String PROPERTIES_DB_FILE = "/db.properties";
	public static final String PROPERTIES_SERVER_FILE = "/pfc2012.server.properties";
	
	public static final String PROPERTY_DB_MONGO_HOST = "db.mongo.host";
	public static final String PROPERTY_DB_MONGO_PORT = "db.mongo.port";
	public static final String PROPERTY_DB_MONGO_DBNAME = "db.mongo.dbName";
	public static final String PROPERTY_DB_MONGO_COLLECTION_NAME = "db.mongo.collectionName";
	
	public static final String PROPERTY_MP3_ROOT = "server.mp3.root";
	
	public static final String DB_FIELD_TEXT_CA = "text_catala";
	public static final String DB_FIELD_TEXT_JP = "text_japones";
	public static final String DB_FIELD_AUDIO_CA = "audio_catala";
	public static final String DB_FIELD_AUDIO_JP = "audio_japones";
	public static final String DB_FIELD_ID = "_id";
	
	public static final String PATH_NO_PATH = "/";
	public static final String PATH_CREATE_CONCEPTE_PARAULA = "/crear_concepte_paraula";
	public static final String PATH_SEARCH_CONCEPTE_PARAULA = "/search_concepte_paraula";
	public static final String PATH_EDIT_CONCEPTE_PARAULA = "/editar_concepte_paraula";
	
}
