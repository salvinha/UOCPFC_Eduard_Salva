package edu.uoc.pfc2012.edusalva.worker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.SuccessResponseBean;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

/**
 * Classe que executa la tasca d'esborrar una paraula existent a la
 * base de dades.
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
public class DeleteWordWorker extends AbstractWorker {
	/**
	 * Objecte Logger.
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(DeleteWordWorker.class.getName());

	/**
	 * Constructor per defecte, es limita a cridar el constructor de la superclasse.
	 */
	public DeleteWordWorker() {
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
	public DeleteWordWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}

	/**
	 * M&egrave;tode que executa la tasca d'esborrat d'una paraula existent al sistema. Un cop
	 * acabada la tasca s'escriu la resposta al client, indicant si s'ha pogut executar
	 * amb &egrave;xit o no (en aquest darrer cas, el motiu habitual &eacute;s que la paraula
	 * no existeix a la base de dades).
	 */
	@Override
	public void processRequest() {
		String id = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_ID)[0];
		boolean b = DBController.deleteKoncept(id);
		ResponseBean rb = b ? new SuccessResponseBean() : new ErrorResponseBean("La paraula no s'ha trobat") ;

		try {
			writeResponse(rb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
