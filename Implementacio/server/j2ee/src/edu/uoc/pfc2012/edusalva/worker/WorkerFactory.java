package edu.uoc.pfc2012.edusalva.worker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

public abstract class WorkerFactory {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(WorkerFactory.class.getName());

	public static final AbstractWorker getWorker(HttpServletRequest req, HttpServletResponse res) {

		String path = req.getPathInfo();
		Map<String, String[]> params = req.getParameterMap();

		if (PFCConstants.PATH_CREATE_CONCEPTE_PARAULA.equals(path)) {
			return createKoncept(req, res, path, params);
		} else if (PFCConstants.PATH_SEARCH_CONCEPTE_PARAULA.equals(path)) {
			return searchConcept(req, res, path, params);
		} else if (PFCConstants.PATH_EDIT_CONCEPTE_PARAULA.equals(path)) {
			return editKoncept(req, res, path, params);
		} else if (PFCConstants.PATH_GET_CONCEPTE_PARAULA.equals(path)) {
			return getKoncept(req, res, path, params);
		} else if (PFCConstants.PATH_GET_SOUND.equals(path)) {
			return getSound(req, res, path, params);
		} else if (PFCConstants.PATH_LIST_WORDS.equals(path)) {
			return getWordList(req, res, path, params);
		} else if (PFCConstants.PATH_DELETE_WORD.equals(path)) {
			return deleteWord(req, res, path, params);
		} else if (PFCConstants.PATH_GET_WORD_FROM_LIST.equals(path)) {
			return getNextWordFromList(req, res, path, params);
		} else if (PFCConstants.PATH_RESET_GAME.equals(path)) {
			return getResetGame(req, res, path, params);
		}

		// TODO throw Exception
		return null;
	}

	private static AbstractWorker deleteWord(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		// TODO Auto-generated method stub
		DeleteWordWorker w = new DeleteWordWorker(req, res, path, params);
		return w;
	}

	private static GetWordListWorker getWordList(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		GetWordListWorker w = new GetWordListWorker(req, res, path, params);
		return w;
	}

	private static CreateKonceptWorker createKoncept(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		CreateKonceptWorker w = new CreateKonceptWorker(req, res, path, params);
		return w;
	}

	private static SearchKonceptWorker searchConcept(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		SearchKonceptWorker w = new SearchKonceptWorker(req, res, path, params);
		return w;
	}

	private static AbstractWorker editKoncept(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		EditKonceptWorker w = new EditKonceptWorker(req, res, path, params);
		return w;
	}

	private static AbstractWorker getKoncept(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		GetKonceptWorker w = new GetKonceptWorker(req, res, path, params);
		return w;
	}

	private static AbstractWorker getSound(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		GetSoundWorker w = new GetSoundWorker(req, res, path, params);
		return w;
	}

	private static AbstractWorker getNextWordFromList(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		GetWordFromListWorker w = new GetWordFromListWorker(req, res, path, params);
		return w;
	}

	private static AbstractWorker getResetGame(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		ResetGameWorker w = new ResetGameWorker(req, res, path, params);
		return w;
	}
}
