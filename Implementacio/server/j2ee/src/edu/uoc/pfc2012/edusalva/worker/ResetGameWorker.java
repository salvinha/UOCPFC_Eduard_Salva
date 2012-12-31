package edu.uoc.pfc2012.edusalva.worker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.SuccessResponseBean;

/**
 * Classe que executa la tasca de reiniciar el comptador de la llista de paraules
 * ja enviades al client.
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
public class ResetGameWorker extends AbstractWorker {
	/**
	 * Objecte Logger.
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ResetGameWorker.class.getName());

	/**
	 * Constructor per defecte, crida el de la superclasse.
	 */
	public ResetGameWorker() {
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
	public ResetGameWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}


	/**
	 * M&egrave;tode que executa la tasca de restaurar la sessi&oacute; de joc de l'usuari. En realitat
	 * el que es fa &eacute;s simplement invalidar la sessi&oacute; HTTP.
	 */
	@Override
	public void processRequest() {
		ResponseBean rb = null;
		try {
			getReq().getSession().invalidate();
			rb = new SuccessResponseBean();
		} catch (Exception e) {
			rb = new ErrorResponseBean("No s'ha pogut restaurar el joc!");
		}

		writeResponse(rb);
	}
}
