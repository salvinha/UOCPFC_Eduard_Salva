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
 * Classe que té per missió cercar una paraula a la base de dades, a partir del
 * seu identificador.
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
public class GetKonceptWorker extends AbstractWorker {
	/**
	 * Objecte Logger.
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(GetKonceptWorker.class.getName());

	public GetKonceptWorker() {
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
	public GetKonceptWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}

	/**
	 * M&egrave;tode que executa la tasca de recuperaci&oacute; d'un KoncepteParaula a partir
	 * de l'ID de la paraula, rebuda per par&agrave;metre en la petici&oacute; del client.
	 */
	@Override
	public void processRequest() {
		String id = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_ID)[0];
		ResponseBean rb = null;

		try {
			KoncepteParaula k = DBController.findById(id);
			rb = (k == null) ? rb = new ErrorResponseBean("No s'ha trobat la paraula."): new KonceptResponseBean(k);
		} catch (Exception e) {
			e.printStackTrace();
		}

		writeResponse(rb);
	}

}
