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
import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.PFC2012Request;
import edu.uoc.pfc2012.edusalva.controller.exception.NoPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongMethodException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongRequestParametersException;
import edu.uoc.pfc2012.edusalva.db.DBController;

public abstract class PFCUtils {

	private static final Logger logger = Logger.getLogger(PFCUtils.class.getName());
	private static Map<String, PFC2012Request> requests = null;

	/*
	 * Initialization of parameters for requests.
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


	public static final void saveBase64(String source, OutputStream out) throws Exception{
		byte[] bytes = Base64.decodeBase64(source.getBytes());

		out.write(bytes);
		out.flush();
		out.close();
	}


	public static String getBase64FromFile(String path) {
		File f = new File(path);
		if (!(f.exists())) {
			return null;
		}

		byte[] bytes = null;

		try {
			bytes = org.apache.commons.io.FileUtils.readFileToByteArray(f);
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
