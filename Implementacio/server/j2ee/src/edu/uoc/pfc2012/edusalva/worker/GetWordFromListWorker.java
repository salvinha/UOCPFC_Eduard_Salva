package edu.uoc.pfc2012.edusalva.worker;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.KonceptResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

/**
 * Classe que cont&eacute; la funcionalitat d'obtenir una paraula d'una llista, simulant
 * el joc de l'usuari que intenta encertar les paraules que li van sortint.
 *
 * <p>
 * Si una paraula ja ha sortit pr&egrave;viament, aquesta es desa en una llista, i no tornar&agrave;
 * a sortir durant la sessi&oacute;.
 * </p>
 *
 * <p>
 * Hi ha un altre
 * <i>worker</i>
 * que permet de reiniciar la sessi&oacute;, per tal que les paraules que ja han aparegut
 * puguin tornar a sortir.
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
 */
public class GetWordFromListWorker extends AbstractWorker {

	/**
	 * Objecte Logger.
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(GetWordFromListWorker.class.getName());

	/**
	 * Constructor per defecte, crida el constructor de la superclasse.
	 */
	public GetWordFromListWorker() {
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
	public GetWordFromListWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}


	/**
	 * M&egrave;tode que executa la funcionalitat de cercar una paraula d'entre les que s&oacute;n a la
	 * llista indicada pel par&agrave;metre i que no hagi sortit encara en aquesta sessi&oacute;.
	 * Si no queden paraules, s'enviar&agrave; un missatge d'error al client indicant aquesta
	 * situaci&oacute;.
	 */
	@Override
	public void processRequest() {
		HttpSession session = getReq().getSession();

		@SuppressWarnings("unchecked")
		Set<String> words = (Set<String>) session.getAttribute("WORDS");
		if (words == null) {
			words = new HashSet<String>();
		}

		String list = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_LLISTA_ESTUDI)[0];
		ResponseBean rb = null;

		try {
			KoncepteParaula k = DBController.getNextWordFromList(list, words);
			if (k != null) {
				words.add(k.getId());
				session.setAttribute("WORDS", words);
			}
			rb = (k == null) ? rb = new ErrorResponseBean("No hi ha paraules a la llista"): new KonceptResponseBean(k);
		} catch (Exception e) {
			e.printStackTrace();
		}

		writeResponse(rb);
	}

}
