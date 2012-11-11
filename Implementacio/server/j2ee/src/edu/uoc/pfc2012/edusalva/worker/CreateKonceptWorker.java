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
			String audio64 = getParams().get("audio_japones")[0];
			
			// 1 - Descodifiquem Base64 per obtenir el binary del fitxer, i l'escrivim.
			String m = "/Users/edu/Desktop/m.mp3";
			byte[] bytes = Base64.decodeBase64(audio64.getBytes());
			FileOutputStream outs = new FileOutputStream(new File(m));
			outs.write(bytes);
			outs.flush();
			outs.close();

			
			String id = DBController.createKoncept(koncept);
			koncept.setId(id);
			
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
			logger.error(e.getStackTrace().toString());
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
