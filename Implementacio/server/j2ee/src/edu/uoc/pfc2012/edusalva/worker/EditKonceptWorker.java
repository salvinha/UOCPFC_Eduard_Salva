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
 * Classe que executa la tasca d'edici&oacute; d'una paraula existent.
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
public class EditKonceptWorker extends AbstractWorker {

	/**
	 * Objecte Logger.
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(EditKonceptWorker.class.getName());

	/**
	 * Constructor per defecte, es limita a cridar el constructor de la superclasse.
	 */
	public EditKonceptWorker() {
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
	public EditKonceptWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}

	/**
	 * Mètode que executa la tasca d'edició de la paraula que és al sistema,
	 * identificada pel seu ID.
	 * Primer es cerca l'objecte KoncepteParaula, i a continuació s'hi posen
	 * els atributs que ens hagin arribat per paràmetre; un cop fet això, es
	 * fa una actualització a la base de dades. La resposta enviada al client
	 * inclou la nova paraula.
	 */
	@Override
	public void processRequest() {
		String id = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_ID)[0];

		KoncepteParaula k = null;
		ResponseBean rb = null;

		try {
			k = DBController.findById(id);

			if (k != null) {
				// We have the existing koncept.
				// Now we have to replace its values with the ones received in the request.
				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_TEXT_CA)) {
					k.setTextcat(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_TEXT_CA)[0]);
				}

				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_TEXT_JP)) {
					k.setTextjap(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_TEXT_JP)[0]);
				}

				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_CA)) {
					k.setAudioCatala(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_CA)[0]);
				}

				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_JP)) {
					k.setAudioJapones(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_JP)[0]);
				}

				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_LLISTA_ESTUDI)) {
					k.setIdLlista(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_LLISTA_ESTUDI)[0]);
				}

				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_PRON_JAP)) {
					k.setPronjap(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_PRON_JAP)[0]);
				}

				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_PRON_CAT)) {
					k.setProncat(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_PRON_CAT)[0]);
				}


				// Values replaced. We will now save to DB.
				DBController.update(k);

				rb = new KonceptResponseBean(k);
			} else {
				rb = new ErrorResponseBean("No es troba la paraula a modificar.");
			}

			writeResponse(rb);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
