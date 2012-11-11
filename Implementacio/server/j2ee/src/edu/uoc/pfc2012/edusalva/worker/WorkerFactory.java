package edu.uoc.pfc2012.edusalva.worker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

public abstract class WorkerFactory {

	public static final AbstractWorker getWorker(HttpServletRequest req, HttpServletResponse res) {
		AbstractWorker aw = null;
		
		String path = req.getPathInfo();
		Map<String, String[]> params = req.getParameterMap();
		
		if (PFCConstants.PATH_CREATE_CONCEPTE_PARAULA.equals(path)) {
			return createKoncept(req, res, path, params);
		}
		
		// TODO throw Exception
		return aw;
	}
	
	private static CreateKonceptWorker createKoncept(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		CreateKonceptWorker w = new CreateKonceptWorker(req, res, path, params);
		return w;
	}
}
