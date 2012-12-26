package edu.uoc.pfc2012.edusalva.worker;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.KonceptResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
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
		String text = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_TEXT_SEARCH)[0];
		String idioma = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_IDIOMA)[0];
		
		logger.info("Searching for '" + text + "' in lang '" + idioma + "' ...");
		KoncepteParaula k = DBController.getKoncept(text, idioma);
		
		ResponseBean rb = (k == null) ? new ErrorResponseBean("No s'ha trobat cap paraula"): new KonceptResponseBean(k);
		writeResponse(rb);
	}

}
