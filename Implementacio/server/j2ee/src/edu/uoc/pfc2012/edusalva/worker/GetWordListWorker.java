package edu.uoc.pfc2012.edusalva.worker;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.bcel.classfile.Constant;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.WordListResponseBean;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;
import edu.uoc.pfc2012.edusalva.utils.PFCUtils;

public class GetWordListWorker extends AbstractWorker {
	private static final Logger logger = Logger.getLogger(GetWordListWorker.class.getName());
	
	public GetWordListWorker() {
		super();
	}
	
	public GetWordListWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}
	
	@Override
	public void processRequest() {
		int maxResults = Integer.MIN_VALUE;
		
		Properties props;
		try {
			if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_MAX_RESULTS)) {
				maxResults = Integer.parseInt(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_MAX_RESULTS)[0]);
			} else {
				props = PFCUtils.getProperties(PFCConstants.KEY_PROPERTIES_SERVER_FILE);
				maxResults = Integer.parseInt(props.getProperty(""));
			}
		} catch (Exception e) {
			maxResults = PFCConstants.MAX_RESULTS_DEFAULT;
		}
		
		List<KoncepteParaula> list = DBController.getWordList(maxResults);
		
		ResponseBean rb = null;
		
		try {
			rb = (list == null || list.size() == 0) ? new ErrorResponseBean("No s'ha trobat cap paraula.") : new WordListResponseBean(list);
			writeResponse(rb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
