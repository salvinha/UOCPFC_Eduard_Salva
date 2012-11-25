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

public class CreateKonceptWorker extends AbstractWorker {
	private static final Logger logger = Logger.getLogger(CreateKonceptWorker.class.getName());
	
	private KoncepteParaula koncept;
	
	public CreateKonceptWorker() {
		super();
		this.koncept = new KoncepteParaula();
	}
	
	public CreateKonceptWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}
	
	
	/**
	 * Tipus de petició des del client:
	 * var b64 = encodeURIComponent("alkjdsljdlkasjdlkdasjldjk"); // Fitxer MP3 en Base64.
	 * 	$.ajax({
		url: "http://localhost:8080/pfc2012/crear_concepte_paraula?text_catala=pepe&text_japones=製品を実際に試しながら&audio_japones=" + b64,
		type: 'POST', 
		async: false,
		dataType: "json", 
	 */
	@Override
	public void processRequest() {
		try {
			koncept.setTextCatala(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_TEXT_CA)[0]);
			koncept.setTextJapones(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_TEXT_JP)[0]);

			// TODO Gestionar amb excepcions.
			boolean b = DBController.konceptExists(koncept);
			String id = null;
			
			Writer w = getRes().getWriter();
			
			if (!b) {
				id = DBController.createKoncept(koncept);
				koncept.setId(id);
				
				logger.info("Koncept created. ID = " +  id);
				
				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_JP)) {
					processAudio(getParams(), PFCConstants.LANG_JAP, koncept);
				} 
				
				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_CA)) {
					processAudio(getParams(), PFCConstants.LANG_CAT, koncept);
				}
				
				ObjectMapper mapper = new ObjectMapper();
				// Filtre per incloure nomes l'ID en la resposta. // TODO Check!
				FilterProvider filters = new SimpleFilterProvider().addFilter("id_only", SimpleBeanPropertyFilter.filterOutAllExcept("id"));
				mapper.setFilters(filters);
				mapper.writeValue(w, koncept.getId());
				
				w.write(id);
			} else {
				w.write("Word already exists.");
				logger.warn("Already exists!");
			}

			w.flush();
			w.close();
		} catch (Exception e) {
			logger.error("Error processing request!");
			e.printStackTrace();
		}
	}
	
	private void processAudio(Map<String, String[]> params, String lang, KoncepteParaula k) throws IOException {
		logger.info("Processing audio for '" + lang + " ...");
		
		Properties props;
		try {
			props = PFCUtils.getProperties(PFCConstants.KEY_PROPERTIES_SERVER_FILE);
		} catch (Exception e) {
			throw new IOException("Error looking for properties file.");
		}
		
		String folder = props.getProperty(PFCConstants.PROPERTY_MP3_ROOT) + System.getProperty("file.separator") + lang;
		
		logger.info("FOLDER = '" + folder + "'");
		
		String audio = null;
		if (lang.equals(PFCConstants.LANG_CAT)) {
			audio = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_CA)[0];
		} else if (lang.equals(PFCConstants.LANG_JAP)) {
			audio = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_JP)[0];
		}
		
		logger.info("audio.length() = " + audio.length());
		
		String path = folder + System.getProperty("file.separator") + k.getId() + ".mp3";
		OutputStream out = new FileOutputStream(new File(path));
		try {
			PFCUtils.saveBase64(audio, out);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("Cannot save in Base64!");
		}
	}

	public KoncepteParaula getKoncept() {
		return koncept;
	}

	public void setKoncept(KoncepteParaula koncept) {
		this.koncept = koncept;
	}

}
