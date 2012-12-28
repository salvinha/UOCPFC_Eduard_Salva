package edu.uoc.pfc2012.edusalva.worker;

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

public class EditKonceptWorker extends AbstractWorker {

	private static final Logger logger = Logger.getLogger(EditKonceptWorker.class.getName());

	public EditKonceptWorker() {
		super();
	}

	public EditKonceptWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}

	@Override
	public void processRequest() {
		String id = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_ID)[0];
		
		KoncepteParaula k = null;
		ResponseBean rb = null;
		
		try {
			k = DBController.findById(id);
			
			if (k != null) {
				// We have the existing koncept.
				// Now we have to replace its values with the ones received in the request.
				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_TEXT_CA)) {
					k.setTextcat(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_TEXT_CA)[0]);
				}

				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_TEXT_JP)) {
					k.setTextjap(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_TEXT_JP)[0]);	
				}
				
				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_CA)) {
					k.setAudioCatala(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_CA)[0]);
				}
				
				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_JP)) {
					k.setAudioJapones(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_JP)[0]);
				}
				
				
				// Values replaced. We will now save to DB.
				DBController.update(k);
				
				rb = new KonceptResponseBean(k);
			} else {
				rb = new ErrorResponseBean("No es troba la paraula a modificar.");
			}
			
			writeResponse(rb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
