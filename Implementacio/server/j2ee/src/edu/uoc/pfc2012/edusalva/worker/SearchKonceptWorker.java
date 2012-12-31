package edu.uoc.pfc2012.edusalva.worker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.KonceptResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

/**
 *
 * Classe que efectua la feina de cercar un koncepte, a partir d'un text i
 * un idioma.
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
public class SearchKonceptWorker extends AbstractWorker {

	/**
	 * Objecte Logger.
	 */
	private static final Logger logger = Logger.getLogger(SearchKonceptWorker.class.getName());

	/**
	 * Constructor amb els par&agrave;metres dels atributs del
	 * <i>worker</i>
	 * abstracte, per tal d'inicialitzar els atributs b&agrave;sics d'aquest.
	 * @param req La petici&oacute; HTTP del client.
	 * @param res La resposta HTTP que s'enviar&agrave; al client.
	 * @param path La ruta de la petici&oacute; del client.
	 * @param params Els par&agrave;metres de la petici&oacute; del client.
	 */
	public SearchKonceptWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}


	/**
	 * M&egrave;tode que efectua la cerca, es limita a passar els par&agrave;metres que ha enviat
	 * el client al m&egrave;tode adequat de la classe
	 * <i>DBController</i>
	 * i respon al client amb els resultats obtinguts.
	 */
	@Override
	public void processRequest() {
		String text = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_TEXT_SEARCH)[0];
		String idioma = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_IDIOMA)[0];

		logger.info("Searching for '" + text + "' in lang '" + idioma + "' ...");
		KoncepteParaula k = DBController.getKoncept(text, idioma);

		ResponseBean rb = (k == null) ? new ErrorResponseBean("No s'ha trobat cap paraula"): new KonceptResponseBean(k);
		writeResponse(rb);
	}

}
