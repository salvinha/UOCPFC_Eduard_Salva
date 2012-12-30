package edu.uoc.pfc2012.edusalva.worker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.SuccessResponseBean;

public class ResetGameWorker extends AbstractWorker {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ResetGameWorker.class.getName());

	public ResetGameWorker() {
		super();
	}

	public ResetGameWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}


	@Override
	public void processRequest() {
		ResponseBean rb = null;
		try {
			getReq().getSession().invalidate();
			rb = new SuccessResponseBean();
		} catch (Exception e) {
			rb = new ErrorResponseBean("No s'ha pogut restaurar el joc!");
		}

		writeResponse(rb);
	}
}
