package edu.uoc.pfc2012.edusalva.worker;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;
import edu.uoc.pfc2012.edusalva.utils.PFCUtils;

/**
 *
 * Classe de tipus
 * <i>worker</i>,
 * que executa la tasca de cercar el so d'una paraula, i retornar-lo al client
 * en format Base64.
 *
 * <p>
 * Projecte Final de Carrera - Desenvolupament d'aplicacions m&#242;bils en HTML5
 * </p>
 *
 * <p>
 * Data: Gener de 2013
 * </p>
 *
 * @author Eduard Capell Brufau (<a href="mailto:ecapell@uoc.edu">ecapell@uoc.edu</a>)
 * @author Salvador Lorca Sans (<a href="salvinha@uoc.edu">salvinha@uoc.edu</a>)
 *
 * @version 1.0
 *
 */
public class GetSoundWorker extends AbstractWorker {
	/**
	 * Objecte Logger.
	 */
	private static final Logger logger = Logger.getLogger(GetSoundWorker.class.getName());

	/**
	 * Constructor per defecte, crida el constructor de la superclasse.
	 */
	public GetSoundWorker() {
		super();
	}

	/**
	 * Constructor amb els par&agrave;metres dels atributs del
	 * <i>worker</i>
	 * abstracte, per tal d'inicialitzar els atributs b&agrave;sics d'aquest.
	 * @param req La petici&oacute; HTTP del client.
	 * @param res La resposta HTTP que s'enviar&agrave; al client.
	 * @param path La ruta de la petici&oacute; del client.
	 * @param params Els par&agrave;metres de la petici&oacute; del client.
	 */
	public GetSoundWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}



	/**
	 * M&egrave;tode que recupera el so de la paraula indicada (ID rebuda per par&agrave;metre
	 * en la petici&oacute; del client), i el retorna al client en format Base64.
	 * En la petici&oacute; del client es reben dos par&agrave;metres:
	 * <ul>
	 * <li>
	 * ID de la paraula
	 * </li>
	 * <li>
	 * Idioma
	 * </li>
	 * </ul>
	 * Un cop es t&eacute; l'ID de la paraula, es pot trobar la paraula completa (aix&#242;
	 * inclou la ruta al fitxer d'&agrave;udio que es troba al sistema de fitxers del
	 * servidor).
	 * Quan tenim la ruta del fitxer d'&agrave;udio, s'ha dobrir el fitxer, i convertir-lo
	 * al format Base64 per tal de poder-lo enviar al client en format text.
	 */
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
						w.write(PFCUtils.getBase64FromFile(k.getAudioCatala()));
					}
				} else if (PFCConstants.LANG_JAP.equals(lang)) {
					if (k.getAudioJapones() == null) {
						w.write(PFCConstants.RESPONSE_NO_AUDIO);
					} else {
						w.write(PFCUtils.getBase64FromFile(k.getAudioJapones()));
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
			logger.error("Problemes per recuperar l'àudio", e);
			ResponseBean rb = new ErrorResponseBean("Problemes per recuperar el fitxer de so");
			writeResponse(rb);
		}
	}
}
