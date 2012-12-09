package edu.uoc.pfc2012.edusalva.worker;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

public class SearchKonceptWorker extends AbstractWorker {

	private static final Logger logger = Logger.getLogger(SearchKonceptWorker.class.getName());
	
	public SearchKonceptWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}

	@Override
	public void processRequest() {
		logger.debug("Searching!");
		
		// Arriba una petició de cerca d'una paraula, cal saber text i idioma.
		String text = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_TEXT_SEARCH)[0];
		String idioma = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_IDIOMA)[0];
		
		KoncepteParaula k = DBController.getKoncept(text, idioma);
		Writer w = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			w = getRes().getWriter();
			if (k != null) {
				mapper.writeValue(w, k);				
			} else {
				throw new Exception("No results!");
			}
		} catch (Exception e) {
			try {
				mapper.writeValue(w, PFCConstants.RESPONSE_SEARCH_FOUND_NOTHING);
				w.flush();
				w.close();
			} catch (IOException e1) {}
		}
	}

}
