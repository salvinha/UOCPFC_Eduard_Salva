package edu.uoc.pfc2012.edusalva.worker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

/**
 * Classe que serveix per a obtenir un
 * <i>worker</i>
 * apropiat en funció de la petició del client.
 *
 * <p>
 * La decisi&oacute; sobre quin
 * <i>worker</i>
 * servir &eacute;s en funci&oacute; &uacute;nicament de la ruta de la petici&oacute; client.
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
 * @see AbstractWorker
 */
public abstract class WorkerFactory {

	/**
	 * Objecte Logger.
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(WorkerFactory.class.getName());

	/**
	 * M&egrave;tode est&agrave;tic que retorna el
	 * <i>worker</i>
	 * que correspon a la petici&oacute; del client, en funci&oacute; de la ruta d'aquesta petici&oacute;.
	 * @param req Petici&oacute; HTTP del client.
	 * @param res Resposta HTTP que s'enviar&agrave; al client.
	 * @return Objecte de tipus
	 * <i>AbstractWorker</i>,
	 * la implementaci&oacute; concreta del qual dependr&agrave; de la ruta de la petici&oacute; que ha fet el client.
	 */
	public static final AbstractWorker getWorker(HttpServletRequest req, HttpServletResponse res) {

		String path = req.getPathInfo();
		Map<String, String[]> params = req.getParameterMap();

		if (PFCConstants.PATH_CREATE_CONCEPTE_PARAULA.equals(path)) {
			return createKoncept(req, res, path, params);
		} else if (PFCConstants.PATH_SEARCH_CONCEPTE_PARAULA.equals(path)) {
			return searchConcept(req, res, path, params);
		} else if (PFCConstants.PATH_EDIT_CONCEPTE_PARAULA.equals(path)) {
			return editKoncept(req, res, path, params);
		} else if (PFCConstants.PATH_GET_CONCEPTE_PARAULA.equals(path)) {
			return getKoncept(req, res, path, params);
		} else if (PFCConstants.PATH_GET_SOUND.equals(path)) {
			return getSound(req, res, path, params);
		} else if (PFCConstants.PATH_LIST_WORDS.equals(path)) {
			return getWordList(req, res, path, params);
		} else if (PFCConstants.PATH_DELETE_WORD.equals(path)) {
			return deleteWord(req, res, path, params);
		} else if (PFCConstants.PATH_GET_WORD_FROM_LIST.equals(path)) {
			return getNextWordFromList(req, res, path, params);
		} else if (PFCConstants.PATH_RESET_GAME.equals(path)) {
			return getResetGame(req, res, path, params);
		}

		/*
		 * No pot arribar aqui, perque el filtre ja ha validat que sigui
		 * una peticio valida.
		 */
		return null;
	}

	/**
	 * M&egrave;tode que retorna el
	 * <i>worker</i>
	 * que executa la tasca de
	 * l'esborrat d'una paraula.
	 * @param req La petici&oacute; HTTP del client.
	 * @param res La resposta HTTP que s'enviar&agrave; al client.
	 * @param path La ruta de la petici&oacute; del client.
	 * @param params Els par&agrave;metres de la petici&oacute; del client.
	 * @return Objecte de tipus
	 * <i>AbstractWorker</i>
	 * que ser&agrave; qui realitzi l'acci&oacute; sol&#183;licitada pel client.
	 */
	private static AbstractWorker deleteWord(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		DeleteWordWorker w = new DeleteWordWorker(req, res, path, params);
		return w;
	}

	/**
	 * M&egrave;tode que retorna el
	 * <i>worker</i>
	 * que executa la tasca de
	 * recuperaci&oacute; de la llista de paraules del servidor.
	 * @param req La petici&oacute; HTTP del client.
	 * @param res La resposta HTTP que s'enviar&agrave; al client.
	 * @param path La ruta de la petici&oacute; del client.
	 * @param params Els par&agrave;metres de la petici&oacute; del client.
	 * @return Objecte de tipus
	 * <i>AbstractWorker</i>
	 * que ser&agrave; qui realitzi l'acci&oacute; sol&#183;licitada pel client.
	 */
	private static GetWordListWorker getWordList(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		GetWordListWorker w = new GetWordListWorker(req, res, path, params);
		return w;
	}

	/**
	 * M&egrave;tode que retorna el
	 * <i>worker</i>
	 * que executa la tasca de
	 * creaci&oacute; d'una nova paraula.
	 * @param req La petici&oacute; HTTP del client.
	 * @param res La resposta HTTP que s'enviar&agrave; al client.
	 * @param path La ruta de la petici&oacute; del client.
	 * @param params Els par&agrave;metres de la petici&oacute; del client.
	 * @return Objecte de tipus
	 * <i>AbstractWorker</i>
	 * que ser&agrave; qui realitzi l'acci&oacute; sol&#183;licitada pel client.
	 */
	private static CreateKonceptWorker createKoncept(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		CreateKonceptWorker w = new CreateKonceptWorker(req, res, path, params);
		return w;
	}

	/**
	 * M&egrave;tode que retorna el
	 * <i>worker</i>
	 * que executa la tasca de
	 * cerca d'una paraula a la base de dades.
	 * @param req La petici&oacute; HTTP del client.
	 * @param res La resposta HTTP que s'enviar&agrave; al client.
	 * @param path La ruta de la petici&oacute; del client.
	 * @param params Els par&agrave;metres de la petici&oacute; del client.
	 * @return Objecte de tipus
	 * <i>AbstractWorker</i>
	 * que ser&agrave; qui realitzi l'acci&oacute; sol&#183;licitada pel client.
	 */
	private static SearchKonceptWorker searchConcept(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		SearchKonceptWorker w = new SearchKonceptWorker(req, res, path, params);
		return w;
	}

	/**
	 * M&egrave;tode que retorna el
	 * <i>worker</i>
	 * que executa la tasca de
	 * modificaci&oacute; d'una paraula al servidor.
	 * @param req La petici&oacute; HTTP del client.
	 * @param res La resposta HTTP que s'enviar&agrave; al client.
	 * @param path La ruta de la petici&oacute; del client.
	 * @param params Els par&agrave;metres de la petici&oacute; del client.
	 * @return Objecte de tipus
	 * <i>AbstractWorker</i>
	 * que ser&agrave; qui realitzi l'acci&oacute; sol&#183;licitada pel client.
	 */
	private static AbstractWorker editKoncept(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		EditKonceptWorker w = new EditKonceptWorker(req, res, path, params);
		return w;
	}

	/**
	 * M&egrave;tode que retorna el
	 * <i>worker</i>
	 * que executa la tasca de
	 * recuperació d'un objecte de tipus KoncepteParaula.
	 * @param req La petici&oacute; HTTP del client.
	 * @param res La resposta HTTP que s'enviar&agrave; al client.
	 * @param path La ruta de la petici&oacute; del client.
	 * @param params Els par&agrave;metres de la petici&oacute; del client.
	 * @return Objecte de tipus
	 * <i>AbstractWorker</i>
	 * que ser&agrave; qui realitzi l'acci&oacute; sol&#183;licitada pel client.
	 */
	private static AbstractWorker getKoncept(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		GetKonceptWorker w = new GetKonceptWorker(req, res, path, params);
		return w;
	}

	/**
	 * M&egrave;tode que retorna el
	 * <i>worker</i>
	 * que executa la tasca de
	 * cerca del so associat a una paraula.
	 * @param req La petici&oacute; HTTP del client.
	 * @param res La resposta HTTP que s'enviar&agrave; al client.
	 * @param path La ruta de la petici&oacute; del client.
	 * @param params Els par&agrave;metres de la petici&oacute; del client.
	 * @return Objecte de tipus
	 * <i>AbstractWorker</i>
	 * que ser&agrave; qui realitzi l'acci&oacute; sol&#183;licitada pel client.
	 */
	private static AbstractWorker getSound(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		GetSoundWorker w = new GetSoundWorker(req, res, path, params);
		return w;
	}

	/**
	 * M&egrave;tode que retorna el
	 * <i>worker</i>
	 * que executa la tasca de
	 * petici&oacute; de la seg&uuml;ent paraula d'una llista.
	 * @param req La petici&oacute; HTTP del client.
	 * @param res La resposta HTTP que s'enviar&agrave; al client.
	 * @param path La ruta de la petici&oacute; del client.
	 * @param params Els par&agrave;metres de la petici&oacute; del client.
	 * @return Objecte de tipus
	 * <i>AbstractWorker</i>
	 * que ser&agrave; qui realitzi l'acci&oacute; sol&#183;licitada pel client.
	 */
	private static AbstractWorker getNextWordFromList(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		GetWordFromListWorker w = new GetWordFromListWorker(req, res, path, params);
		return w;
	}

	/**
	 * M&egrave;tode que retorna el
	 * <i>worker</i>
	 * que executa la tasca de
	 * restauraci&oacute; del joc de l'usuari, fent com si no hagu&eacute;s demanat encara cap paraula.
	 * @param req La petici&oacute; HTTP del client.
	 * @param res La resposta HTTP que s'enviar&agrave; al client.
	 * @param path La ruta de la petici&oacute; del client.
	 * @param params Els par&agrave;metres de la petici&oacute; del client.
	 * @return Objecte de tipus
	 * <i>AbstractWorker</i>
	 * que ser&agrave; qui realitzi l'acci&oacute; sol&#183;licitada pel client.
	 */
	private static AbstractWorker getResetGame(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		ResetGameWorker w = new ResetGameWorker(req, res, path, params);
		return w;
	}
}
