package edu.uoc.pfc2012.edusalva.worker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Writer;
import java.util.Map;

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
			koncept.setTextCatala(getParams().get("text_catala")[0]);
			koncept.setTextJapones(getParams().get("text_japones")[0]);
			String audioJap = null;
			String audioCat = null;
			
			if (getParams().containsKey("audio_japones")) {
				// TODO Moure el tema de base64 a una classe helper (Utils?)
				audioJap = getParams().get("audio_japones")[0];
				String m = "/Users/edu/Desktop/jap.mp3";
				byte[] bytes = Base64.decodeBase64(audioJap.getBytes());
				FileOutputStream outs = new FileOutputStream(new File(m));
				outs.write(bytes);
				outs.flush();
				outs.close();
				// TODO Desar a Koncept, en funcio de la ruta on es guardi.
			} 
			
			if (getParams().containsKey("audio_catala")) {
				audioCat = getParams().get("audio_catala")[0];
				String m = "/Users/edu/Desktop/cat.mp3";
				byte[] bytes = Base64.decodeBase64(audioCat.getBytes());
				FileOutputStream outs = new FileOutputStream(new File(m));
				outs.write(bytes);
				outs.flush();
				outs.close();
			}
			
			// TODO Gestionar amb excepcions.
			boolean b = DBController.konceptExists(koncept);
			String id = null;
			if (!b) {
				id = DBController.createKoncept(koncept);
				koncept.setId(id);
			} else {
				logger.warn("Already exists!");
			}
			
			Writer w = getRes().getWriter();
			
			ObjectMapper mapper = new ObjectMapper();
			// Filtre per incloure nomes l'ID en la resposta.
			FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id"));
			mapper.setFilters(filters);
			mapper.writeValue(w, koncept); 
			
			w.write(id);
			w.flush();
			w.close();
		} catch (Exception e) {
			logger.error("Error processing request!");
			e.printStackTrace();
		}
	}

	public KoncepteParaula getKoncept() {
		return koncept;
	}

	public void setKoncept(KoncepteParaula koncept) {
		this.koncept = koncept;
	}

}
