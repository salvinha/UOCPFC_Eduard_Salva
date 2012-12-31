package edu.uoc.pfc2012.edusalva.worker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.bean.response.ErrorResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.KonceptIdResponseBean;
import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;
import edu.uoc.pfc2012.edusalva.db.DBController;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;
import edu.uoc.pfc2012.edusalva.utils.PFCUtils;

/**
 * Classe
 * <i>worker</i>
 * que executa la tasca de creaci&oacute; d'un nou Koncepte, afegint-lo a la base
 * de dades.
 *
 * La creaci&oacute; consisteix en l'alta d'un nou document a la base de dades, i
 * en la serialitzaci&oacute; de l'&agrave;udio (si s'ha rebut) a un nou fitxer al
 * sistema de fitxers del servidor.
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
 * @see KoncepteParaula
 */
public class CreateKonceptWorker extends AbstractWorker {

	/**
	 * Objecte Logger.
	 */
	private static final Logger logger = Logger.getLogger(CreateKonceptWorker.class.getName());

	/**
	 * Atribut que emmagatzema el Koncepte a desar a la base de dades.
	 */
	private KoncepteParaula koncept;

	/**
	 * Constructor per defecte, inicialitza el koncepte amb els valors en blanc.
	 */
	public CreateKonceptWorker() {
		super();
		this.koncept = new KoncepteParaula();
	}

	/**
	 * Constructor amb els par&agrave;metres dels atributs del
	 * <i>worker</i>
	 * abstracte, per tal d'inicialitzar els atributs b&agrave;sics d'aquest.
	 *
	 * @param req La petici&oacute; HTTP del client.
	 * @param res La resposta HTTP que s'enviar&agrave; al client.
	 * @param path La ruta de la petici&oacute; del client.
	 * @param params Els par&agrave;metres de la petici&oacute; del client.
	 */
	public CreateKonceptWorker(HttpServletRequest req, HttpServletResponse res, String path, Map<String, String[]> params) {
		this();
		setReq(req);
		setRes(res);
		setPath(path);
		setParams(params);
	}

	/**
	 * Mètode que executa la tasca de creació de la paraula i la seva alta
	 * a la base de dades, i l'emmagatzematge dels fitxers d'àudio al sistema
	 * de fitxers del servidor.
	 */
	@Override
	public void processRequest() {
		try {
			koncept.setTextcat(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_TEXT_CA)[0]);
			koncept.setTextjap(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_TEXT_JP)[0]);
			if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_LLISTA_ESTUDI)) {
				koncept.setIdLlista(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_LLISTA_ESTUDI)[0]);
			}

			if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_PRON_JAP)) {
				koncept.setPronjap(getParams().get(PFCConstants.HTTP_REQUEST_PARAM_PRON_JAP)[0]);
			}

			// TODO Gestionar amb excepcions.
			boolean b = DBController.konceptExists(koncept);
			String id = null;

			if (!b) {
				id = DBController.createKoncept(koncept);
				koncept.setId(id);

				logger.info("Koncept created. ID = " +  id);

				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_JP)) {
					String pathJP = processAudio(getParams(), PFCConstants.LANG_JAP, koncept);
					koncept.setAudioJapones(pathJP);
				}

				if (getParams().containsKey(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_CA)) {
					String pathCA = processAudio(getParams(), PFCConstants.LANG_CAT, koncept);
					koncept.setAudioCatala(pathCA);
				}

				// Actualitzem valor audio.
				DBController.update(koncept);

				KonceptIdResponseBean rb = new KonceptIdResponseBean(koncept.getId());
				writeResponse(rb);
			} else {
				ResponseBean rb = new ErrorResponseBean("La paraula ja existeix");
				writeResponse(rb);
			}
		} catch (Exception e) {
			logger.error("Error processing request!");
			e.printStackTrace();
		}
	}

	private String processAudio(Map<String, String[]> params, String lang, KoncepteParaula k) throws IOException {
		logger.info("Processing audio for '" + lang + "' ...");

		Properties props;
		try {
			props = PFCUtils.getProperties(PFCConstants.KEY_PROPERTIES_SERVER_FILE);
		} catch (Exception e) {
			throw new IOException("Error looking for properties file.");
		}

		String folder = props.getProperty(PFCConstants.PROPERTY_MP3_ROOT) + System.getProperty("file.separator") + lang;

		logger.info("FOLDER = '" + folder + "'");

		String audio = null;
		if (lang.equals(PFCConstants.LANG_CAT)) {
			audio = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_CA)[0];
		} else if (lang.equals(PFCConstants.LANG_JAP)) {
			audio = getParams().get(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_JP)[0];
		}

		logger.info("audio.length() = " + audio.length());

		String path = folder + System.getProperty("file.separator") + k.getId() + ".mp3";
		OutputStream out = new FileOutputStream(new File(path));
		try {
			PFCUtils.saveBase64(audio, out);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("Cannot save in Base64!");
		}

		return path;
	}

	public KoncepteParaula getKoncept() {
		return koncept;
	}

	public void setKoncept(KoncepteParaula koncept) {
		this.koncept = koncept;
	}

}
