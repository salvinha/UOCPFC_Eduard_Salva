package edu.uoc.pfc2012.edusalva.worker;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uoc.pfc2012.edusalva.bean.response.ResponseBean;

/**
 * Classe gen&egrave;rica que cont&eacute; les funcions b&agrave;siques de qualsevol
 * <i>worker</i>
 * de l'aplicaci&oacute;.
 * <p>
 * Un
 * <i>worker</i>
 * &eacute;s un objecte que realitza una funci&oacute; determinada, i al qual se li delega
 * la responsabilitat d'executar aquella funci&oacute;, i nom&eacute;s aquella funci&oacute;.
 * </p>
 *
 * <p>
 * La classe t&eacute; acc&eacute;s als objectes seg&uuml;ents:
 * <ul>
 * <li>
 * Ruta de la petici&oacute;.
 * </li>
 * <li>
 * Par&agrave;metres de la petici&oacute;.
 * </li>
 * <li>
 * Petici&oacute; HTTP.
 * </li>
 * <li>
 * Resposta HTTP.
 * </li>
 * </ul>
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
public abstract class AbstractWorker {
	/**
	 * Objecte Logger.
	 */
	private static final Logger logger = Logger.getLogger(AbstractWorker.class.getName());

	/**
	 * Mapa amb els par&agrave;metres de la petici&oacute; HTTP. Cada par&agrave;metre (clau del
	 * <i>map</i>
	 * ) t&eacute; un valor, de tipus
	 * <i>array</i>
	 * de cadenes de text.
	 */
	private Map<String, String[]> params;

	/**
	 * Ruta de la petici&oacute; HTTP.
	 */
	private String path;

	/**
	 * Petici&oacute; HTTP del client.
	 */
	private HttpServletRequest req;

	/**
	 * Resposta HTTP que s'enviar&agrave; al client.
	 */
	private HttpServletResponse res;

	/**
	 * M&egrave;tode abstracte que realitzar&agrave; les operacions espec&iacute;fiques de cada
	 * <i>worker</i>.
	 */
	public abstract void processRequest();

	/**
	 * M&egrave;tode que t&eacute; per responsabilitat l'escriptura de la resposta, a partir
	 * d'un objecte
	 * <i>ResponseBean</i>.
	 * La funcionalitat &eacute;s la seg&uuml;ent: s'agafa l'objecte resposta que es vol
	 * escriure, es crea un nou objecte de tipus
	 * <i>ObjectMapper</i>
	 * (per tal de serialitzar la resposta a format JSON), i finalment s'escriu
	 * a la resposta HTTP que s'enviar&agrave; al client.
	 * @param rb L'objecte que s'ha d'escriure.
	 * @see ResponseBean
	 * @see ObjectMapper
	 */
	public void writeResponse(ResponseBean rb) {
		if (rb == null) {
			return;
		}

		ObjectMapper m = new ObjectMapper();
		try {
			Writer w = getRes().getWriter();
			m.writeValue(w, rb);
			w.flush();
			w.close();
		} catch (IOException e) {
			logger.error("Error writing response...");
		}
	}

	/**
	 * Accessor de lectura de l'atribut <code>params</code>.
	 * @return Objecte de tipus
	 * <code>Map<String, String[]></code>
	 * amb els par&agrave;metres de la petici&oacute; del client.
	 */
	public Map<String, String[]> getParams() {
		return params;
	}


	/**
	 * Accessor d'escriptura de l'atribut <code>params</code>.
	 * @param params Nou valor del mapa de par&agrave;metres, que &eacute;s una taula
	 * clau - valor, on la clau &eacute;s una cadena de text, i el valor &eacute;s un
	 * <i>array</i>
	 * de cadenes de text.
	 */
	public void setParams(Map<String, String[]> params) {
		this.params = params;
	}

	/**
	 * Accessor de lectura de l'atribut <code>path</code>.
	 * @return Cadena de text amb la ruta de la petici&oacute; del client.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Accessor d'escriptura de l'atribut <code>path</code>.
	 * @param path Cadena de text amb el nou valor per la ruta de la
	 * petici&oacute; del client.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Accessor de lectura de l'atribut <code>req</code>.
	 * @return Objecte de tipus
	 * <code>HttpServletRequest</code>
	 * amb la petici&oacute; HTTP que ha fet el client.
	 * @see HttpServletRequest
	 */
	public HttpServletRequest getReq() {
		return req;
	}

	/**
	 * Accessor d'escriptura de l'atribut <code>req</code>.
	 * @param req Nou valor per l'objecte amb la petici&oacute; HTTP del client.
	 * @see HttpServletRequest
	 */
	public void setReq(HttpServletRequest req) {
		this.req = req;
	}

	/**
	 * Accessor de lectura de l'atribut <code>res</code>.
	 * @return Objecte de tipus
	 * <code>HttpServletResponse</code>
	 * amb la resposta HTTP que s'enviar&agrave; al client.
	 * @see HttpServletResponse
	 */
	public HttpServletResponse getRes() {
		return res;
	}

	/**
	 * Accessor d'escriptura de l'atribut <code>res</code>.
	 * @param res Nou valor amb l'objecte resposta HTTP que s'enviar&agrave; al client.
	 * @see HttpServletResponse
	 */
	public void setRes(HttpServletResponse res) {
		this.res = res;
	}

}
