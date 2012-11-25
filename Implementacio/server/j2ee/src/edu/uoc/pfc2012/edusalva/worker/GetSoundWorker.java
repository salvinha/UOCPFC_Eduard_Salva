package edu.uoc.pfc2012.edusalva.worker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;
import edu.uoc.pfc2012.edusalva.utils.PFCUtils;

public class GetSoundWorker extends AbstractWorker {
	private static final Logger logger = Logger.getLogger(GetSoundWorker.class.getName());
	
	public GetSoundWorker() {
		super();
	}
	
	public GetSoundWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}
	
	

	@Override
	public void processRequest() {
		String id = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_ID)[0];
		String lang = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_IDIOMA)[0];

		logger.info("Processing request...");
		try {
			Writer w = getRes().getWriter();
			ObjectMapper mapper = new ObjectMapper();
			
			KoncepteParaula k = DBController.findById(id);
			if (k != null) {
				if (PFCConstants.LANG_CAT.equals(lang)) {
					if (k.getAudioCatala() == null) {
						w.write(PFCConstants.RESPONSE_NO_AUDIO);
					} else {
						File f = new File(k.getAudioCatala());
						w.write(getBase64FromFile(f));
					}
				} else if (PFCConstants.LANG_JAP.equals(lang)) {
					if (k.getAudioJapones() == null) {
						w.write(PFCConstants.RESPONSE_NO_AUDIO);
					} else {
						File f = new File(k.getAudioJapones());
						w.write(getBase64FromFile(f));
					}
				} else {
					// Unknown language!
					w.write(PFCConstants.RESPONSE_NO_AUDIO);
				}
			} else {
				mapper.writeValue(w, PFCConstants.RESPONSE_SEARCH_FOUND_NOTHING);
			}
			
			w.flush();
			w.close();
		} catch (Exception e) {
			
		}
	}
	
	private String getBase64FromFile(File file) {
		// TODO Implement.
		return "1234";
	}
	
}
