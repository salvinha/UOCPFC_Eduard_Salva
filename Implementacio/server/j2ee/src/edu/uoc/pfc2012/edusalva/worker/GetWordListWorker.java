package edu.uoc.pfc2012.edusalva.worker;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.WordListResponseBean;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;
import edu.uoc.pfc2012.edusalva.utils.PFCUtils;

/**
 *
 * Classe que executa la tasca de retorna la llista de paraules que són al servidor.
 *
 * <p>
 * El client pot indicar el paràmetre (opcional) que digui quin és el nombre màxim
 * de paraules que vol que surtin. Si no n'especifica cap, s'utilitzarà el paràmetre
 * per defecte (
 * <i>PFCConstants.MAX_RESULTS_DEFAULT</i>
 * )
 * </p>
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
 * @see PFCConstants#MAX_RESULTS_DEFAULT
 */
public class GetWordListWorker extends AbstractWorker {

	/**
	 * Objecte Logger.
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(GetWordListWorker.class.getName());

	/**
	 * Constructor per defecte, crida el constructor de la superclasse.
	 */
	public GetWordListWorker() {
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
	public GetWordListWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}


	/**
	 * M&egrave;tode que executa la funcionalitat d'aquesta classe, consistent a buscar
	 * una llista de les paraules que s&oacute;n al servidor.
	 * Utilitza el par&agrave;metre del client per saber el nombre m&agrave;xim de paraules a
	 * retornar, per&#242; si aquest par&agrave;metre no arriba (&eacute;s opcional), utilitzar&agrave;
	 * el valor per defecte.
	 *
	 * @see PFCConstants#MAX_RESULTS_DEFAULT
	 */
	@Override
	public void processRequest() {
		int maxResults = Integer.MIN_VALUE;

		Properties props;
		try {
			if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_MAX_RESULTS)) {
				maxResults = Integer.parseInt(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_MAX_RESULTS)[0]);
			} else {
				props = PFCUtils.getProperties(PFCConstants.KEY_PROPERTIES_SERVER_FILE);
				maxResults = Integer.parseInt(props.getProperty(PFCConstants.PROPERTY_LIST_MAX_RESULTS));
			}
		} catch (Exception e) {
			maxResults = PFCConstants.MAX_RESULTS_DEFAULT;
		}

		List<KoncepteParaula> list = DBController.getWordList(maxResults);

		ResponseBean rb = null;

		try {
			rb = (list == null || list.size() == 0) ? new ErrorResponseBean("No s'ha trobat cap paraula.") : new WordListResponseBean(list);
			writeResponse(rb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
