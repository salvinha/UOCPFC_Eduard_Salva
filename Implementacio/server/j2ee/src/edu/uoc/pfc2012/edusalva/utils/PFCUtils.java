package edu.uoc.pfc2012.edusalva.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.PFC2012Request;
import edu.uoc.pfc2012.edusalva.controller.exception.NoPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongMethodException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongRequestParametersException;
import edu.uoc.pfc2012.edusalva.db.DBController;

/**
 * Classe d'utilitats, que cont&eacute; m&egrave;todes i codi est&agrave;tics per realitzar funcions
 * gen&egrave;riques que s&oacute;n cridades des de diversos punts de l'aplicaci&oacute;.
 *
 * <p>
 * A m&eacute;s de m&egrave;todes est&agrave;tics, tamb&eacute; hi ha una part de codi est&agrave;tic, no lligat a
 * cap m&egrave;tode. Aquest codi serveix per inicialitzar tots els tipus de peticions
 * i posar-les en mem&#242;ria per tal que es puguin consultar r&agrave;pidament en el moment
 * de l'execuci&oacute;.
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
public abstract class PFCUtils {

	/**
	 * Objecte Logger.
	 */
	private static final Logger logger = Logger.getLogger(PFCUtils.class.getName());

	/**
	 * Objecte de tipus
	 * <i>java.util.Map<String, PFC2012Request></i>
	 * amb les peticions possibles.
	 * És una taula de parelles clau / valor, on la clau és una cadena de text
	 * amb la ruta de la petició, i el valor és l'objecte
	 * <i>PFC2012Request</i>
	 * que conté la informació rellevant de la petició
	 * (ruta, paràmetres).
	 *
	 * @see PFC2012Request
	 */
	private static Map<String, PFC2012Request> requests = null;

	/*
	 * Inicialitzacio de les possibles peticions.
	 */
	static {
		requests = new HashMap<String, PFC2012Request>();

		PFC2012Request rCreate = new PFC2012Request(PFCConstants.PATH_CREATE_CONCEPTE_PARAULA);
		PFC2012Request rEdit = new PFC2012Request(PFCConstants.PATH_EDIT_CONCEPTE_PARAULA);
		PFC2012Request rSearch = new PFC2012Request(PFCConstants.PATH_SEARCH_CONCEPTE_PARAULA);
		PFC2012Request rGet = new PFC2012Request(PFCConstants.PATH_GET_CONCEPTE_PARAULA);
		PFC2012Request rGetSound = new PFC2012Request(PFCConstants.PATH_GET_SOUND);
		PFC2012Request rGetWordList = new PFC2012Request(PFCConstants.PATH_LIST_WORDS);
		PFC2012Request rDeleteWord = new PFC2012Request(PFCConstants.PATH_DELETE_WORD);
		PFC2012Request rNextWordFromList = new PFC2012Request(PFCConstants.PATH_GET_WORD_FROM_LIST);
		PFC2012Request rResetGame = new PFC2012Request(PFCConstants.PATH_RESET_GAME);

		requests.put(PFCConstants.PATH_CREATE_CONCEPTE_PARAULA, rCreate);
		requests.put(PFCConstants.PATH_EDIT_CONCEPTE_PARAULA, rEdit);
		requests.put(PFCConstants.PATH_SEARCH_CONCEPTE_PARAULA, rSearch);
		requests.put(PFCConstants.PATH_GET_CONCEPTE_PARAULA, rGet);
		requests.put(PFCConstants.PATH_GET_SOUND, rGetSound);
		requests.put(PFCConstants.PATH_LIST_WORDS, rGetWordList);
		requests.put(PFCConstants.PATH_DELETE_WORD, rDeleteWord);
		requests.put(PFCConstants.PATH_GET_WORD_FROM_LIST, rNextWordFromList);
		requests.put(PFCConstants.PATH_RESET_GAME, rResetGame);
	}


	/**
	 * M&egrave;tode que permet d'obtenir un fitxer de properties concret, en base
	 * al seu ID. Els IDs dels fitxers de properties (valors enters) estan
	 * codificats a la interf&iacute;cie
	 * <i>PFCConstants</i>.
	 *
	 * @param i Valor enter amb el codi del fitxer de properties que es busca.
	 * @return El fitxer de properties desitjat.
	 * @throws Exception Si no es troba el fitxer, o hi ha problemes en el
	 * seu acc&eacute;s (permisos).
	 */
	public static final Properties getProperties(int i) throws Exception {
		Properties props = null;

		switch (i) {
		case PFCConstants.KEY_PROPERTIES_DB_FILE:
			props = new Properties();
			try {
				props.load(DBController.class.getClassLoader().getResourceAsStream(PFCConstants.PROPERTIES_DB_FILE));
				return props;
			} catch (IOException e) {
				logger.error("Cannot load DB properties file!");
				throw e;
			}
		case PFCConstants.KEY_PROPERTIES_SERVER_FILE:
			props = new Properties();
			try {
				props.load(DBController.class.getClassLoader().getResourceAsStream(PFCConstants.PROPERTIES_SERVER_FILE));
				return props;
			} catch (Exception e) {
				logger.error("Cannot load server configuration properties file!");
				throw e;
			}
		}

		throw new Exception("Wrong properties file requested.");
	}

	/**
	 * M&egrave;tode que comprova si la ruta
	 * (<i>path</i>)
	 * d'una petici&oacute; &eacute;s v&agrave;lida.
	 * Si la petici&oacute; &eacute;s correcta, el m&egrave;tode acaba sense retornar res i sense
	 * que es produeixi cap excepci&oacute;. Per contra, si la petici&oacute; &eacute;s incorrecta,
	 * el m&egrave;tode llen&ccedil;ar&agrave; una excepci&oacute;.
	 * @param req La petició HTTP del client.
	 * @throws WrongPathException Si la ruta no és vàlida.
	 */
	public static final void checkPath(HttpServletRequest req) throws WrongPathException {
		String path = req.getPathInfo();
		if (path == null || path.equals(PFCConstants.PATH_NO_PATH)) {
			throw new NoPathException();
		}

		if (path.equals(PFCConstants.PATH_CREATE_CONCEPTE_PARAULA)) {
			return;
		} else if (path.equals(PFCConstants.PATH_SEARCH_CONCEPTE_PARAULA)) {
			return;
		} else if (path.equals(PFCConstants.PATH_EDIT_CONCEPTE_PARAULA)) {
			return;
		} else if (path.equals(PFCConstants.PATH_GET_CONCEPTE_PARAULA)) {
			return;
		} else if (path.equals(PFCConstants.PATH_GET_SOUND)) {
			return;
		} else if (path.equals(PFCConstants.PATH_LIST_WORDS)) {
			return;
		} else if (path.equals(PFCConstants.PATH_DELETE_WORD)) {
			return;
		} else if (path.equals(PFCConstants.PATH_GET_WORD_FROM_LIST)) {
			return;
		} else if (path.equals(PFCConstants.PATH_RESET_GAME)) {
			return;
		}

		throw new WrongPathException("Path is not valid: '" + path + "'");
	}


	/**
	 * M&egrave;tode que verifica si el HTTP Method de la petici&oacute; &eacute;s v&agrave;lid.
	 * L'aplicaci&oacute; suporta els m&egrave;todes POST i GET.
	 * Si la petici&oacute; &eacute;s correcta el m&egrave;tode acaba sense retornar res i sense que
	 * es produeixi cap excepci&oacute;; per contra, si la petici&oacute; no &eacute;s correcta, es
	 * genera una excepci&oacute;.
	 * @param req Petició HTTP del client.
	 * @throws WrongMethodException Si el M&egrave;tode HTTP de la petici&oacute; del client no est&agrave; suportat.
	 */
	public static final void checkRequestMethod(HttpServletRequest req) throws WrongMethodException {
		if (req == null) {
			throw new WrongMethodException("Request is null!");
		}

		if (req.getMethod() == null || req.getMethod().trim().length() == 0) {
			throw new WrongMethodException("Request method is null or empty!");
		}

		String method = req.getMethod();
		if (!(PFCConstants.HTTP_REQUEST_POST.equalsIgnoreCase(method) || PFCConstants.HTTP_REQUEST_GET.equalsIgnoreCase(method))) {
			throw new WrongMethodException("Method not accepted: " + method);
		}
	}


	/**
	 * Mètode que verifica els parametres de la petició del client.
	 * Si falta algun dels paràmetres obligatoris, es generarà una excepció. Si
	 * no hi ha cap error, el mètode finalitzarà sense retornar res.
	 * @param hr La petició HTTP del client.
	 * @throws WrongRequestParametersException Si hi ha errors en els par&agrave;metres de la petici&oacute;.
	 *
	 * @see PFC2012Request
	 */
	public static void checkRequestParameters(HttpServletRequest hr) throws WrongRequestParametersException {

		Enumeration<String> names = hr.getParameterNames();

		String path = hr.getPathInfo();
		PFC2012Request r = requests.get(path);

		Set<String> mandatory = r.getMandatory();
		Set<String> optional = r.getOptional();

		int nMandatory = mandatory.size();
		int foundOptional = 0;

		while (names.hasMoreElements()) {
			String s = names.nextElement();
			if (mandatory.contains(s)) {
				nMandatory--;
			} else if (optional.contains(s)) {
				// OK.
				foundOptional++;
			} else {
				// Not mandatory, not optional!
				logger.error("Not mandatory, Not optional (" + s + ")");
				// throw new WrongRequestParametersException("Parameter '" + s + "' is not specified by request " + path);
			}
		}

		logger.info("nMandatory: " + nMandatory);

		if (nMandatory > 0) {
			// Not all mandatory parameters are in the request!
			logger.error("Missing parameters!");
			throw new WrongRequestParametersException("Some parameters are missing from the request.");
		}

		if (foundOptional < r.getMinimumOptional()) {
			logger.error("Not enough minium optional parameters (" + r.getMinimumOptional() + ")");
			throw new WrongRequestParametersException("");
		}
	}


	/**
	 * Mètode que desa una cadena de text codificada en Base64 a un fitxer. Primer es
	 * descodifica el valor rebut, i a continuació s'escriu a un fitxer binari.
	 * @param source La cadena de text amb el contingut en format Base64.
	 * @param out <i>OutputStream</i> on s'escriur&agrave; (normalment un fitxer).
	 * @throws Exception Si es produeix algun error (no es troba el fitxer on escriure, manca de permisos).
	 */
	public static final void saveBase64(String source, OutputStream out) throws Exception{
		byte[] bytes = Base64.decodeBase64(source.getBytes());

		out.write(bytes);
		out.flush();
		out.close();
	}


	/**
	 * M&egrave;tode que converteix les dades d'un fitxer a una cadena de text en
	 * format Base64. Aquest m&egrave;tode fa &uacute;s de la llibreria Commons Codec per tal
	 * de fer la conversi&oacute;.
	 * @param path La ruta del fitxer a llegir i convertir a Base64.
	 * @return Cadena de text amb el fitxer codificat en format Base64.
	 */
	public static String getBase64FromFile(String path) {
		File f = new File(path);
		if (!(f.exists())) {
			return null;
		}

		byte[] bytes = null;

		try {
			bytes = FileUtils.readFileToByteArray(f);
		} catch (IOException e) {
			logger.error("Could not read file into byte array! (" + f.getName() + ")");
			return null;
		}


		if (bytes != null && bytes.length > 0) {
			byte[] s = Base64.encodeBase64URLSafe(bytes);
			return new String(s);
		}

		return null;

	}
}
