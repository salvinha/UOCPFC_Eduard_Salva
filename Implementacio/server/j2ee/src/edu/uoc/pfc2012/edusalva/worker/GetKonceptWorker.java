package edu.uoc.pfc2012.edusalva.worker;

import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

public class GetKonceptWorker extends AbstractWorker {
	private static final Logger logger = Logger.getLogger(GetKonceptWorker.class.getName());
	
	public GetKonceptWorker() {
		super();
	}
	
	public GetKonceptWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}
	
	@Override
	public void processRequest() {
		String id = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_ID)[0];
		
		try {
			Writer w = getRes().getWriter();
			ObjectMapper mapper = new ObjectMapper();
			
			KoncepteParaula k = DBController.findById(id);
			if (k != null) {
				mapper.writeValue(w, k);
			} else {
				mapper.writeValue(w, PFCConstants.RESPONSE_SEARCH_FOUND_NOTHING);
			}
			
			w.flush();
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
