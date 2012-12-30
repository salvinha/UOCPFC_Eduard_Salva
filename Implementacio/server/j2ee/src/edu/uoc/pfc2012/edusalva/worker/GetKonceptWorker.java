package edu.uoc.pfc2012.edusalva.worker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.KonceptResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

public class GetKonceptWorker extends AbstractWorker {
	@SuppressWarnings("unused")
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
		ResponseBean rb = null;

		try {
			KoncepteParaula k = DBController.findById(id);
			rb = (k == null) ? rb = new ErrorResponseBean("No s'ha trobat la paraula."): new KonceptResponseBean(k);
		} catch (Exception e) {
			e.printStackTrace();
		}

		writeResponse(rb);
	}

}
