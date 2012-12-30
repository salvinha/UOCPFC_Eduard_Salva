package edu.uoc.pfc2012.edusalva.worker;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.KonceptResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

public class GetWordFromListWorker extends AbstractWorker {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(GetWordFromListWorker.class.getName());

	public GetWordFromListWorker() {
		super();
	}

	public GetWordFromListWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}

	@Override
	public void processRequest() {
		HttpSession session = getReq().getSession();

		@SuppressWarnings("unchecked")
		Set<String> words = (Set<String>) session.getAttribute("WORDS");
		if (words == null) {
			words = new HashSet<String>();
		}

		String list = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_LLISTA_ESTUDI)[0];
		ResponseBean rb = null;

		try {
			KoncepteParaula k = DBController.getNextWordFromList(list, words);
			if (k != null) {
				words.add(k.getId());
				session.setAttribute("WORDS", words);
			}
			rb = (k == null) ? rb = new ErrorResponseBean("No hi ha paraules a la llista"): new KonceptResponseBean(k);
		} catch (Exception e) {
			e.printStackTrace();
		}

		writeResponse(rb);
	}

}
