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
		String text = getParams().get("text_cerca")[0];
		String idioma = getParams().get("idioma")[0];
		
		logger.info("TEXT = '" + text + "'");
		logger.info("IDIOMA = '" + idioma + "'");
		
		KoncepteParaula k = DBController.getKoncept(text, idioma);
		
		logger.debug("K = " + k);
		
		try {
			Writer w = getRes().getWriter();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(w, k);
			w.flush();
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
