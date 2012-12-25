package edu.uoc.pfc2012.edusalva.worker;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.bcel.classfile.Constant;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;
import edu.uoc.pfc2012.edusalva.utils.PFCUtils;

public class DeleteWordWorker extends AbstractWorker {
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
		int maxResults = Integer.MIN_VALUE;
		String id = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_ID)[0];
		
		logger.info("Trying to delete word " + id);
		
		boolean b = DBController.deleteKoncept(id);
		
		try {
			Writer w = getRes().getWriter();
			ObjectMapper mapper = new ObjectMapper();
			
			Map<String, Object> successMap = new HashMap<String, Object>();
			
			successMap.put("success", b);
			mapper.writeValue(w, successMap);
			
			w.flush();
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
