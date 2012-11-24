package edu.uoc.pfc2012.edusalva.worker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

public abstract class WorkerFactory {

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
		}
		
		// TODO throw Exception
		return null;
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
		EditKonceptWorker w = new EditKonceptWorker();
		return w;
	}

	
}
