package edu.uoc.pfc2012.edusalva.worker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.SuccessResponseBean;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

public class DeleteWordWorker extends AbstractWorker {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(DeleteWordWorker.class.getName());

	public DeleteWordWorker() {
		super();
	}

	public DeleteWordWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}

	@Override
	public void processRequest() {
		String id = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_ID)[0];
		boolean b = DBController.deleteKoncept(id);
		ResponseBean rb = b ? new SuccessResponseBean() : new ErrorResponseBean("La paraula no s'ha trobat") ;

		try {
			writeResponse(rb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
