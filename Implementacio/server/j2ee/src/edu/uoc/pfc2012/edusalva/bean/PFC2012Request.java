package edu.uoc.pfc2012.edusalva.bean;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.comparator.PathFileComparator;

import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

/**
 * Classe que representa una petici&oacute; HTTP feta al servidor, amb la seva ruta
 * (<i>path</i>), par&agrave;metres obligatoris i par&agrave;metres opcionals.
 * La classe s'utilitza per encapsular peticions que arriben al servidor, i
 * per poder verificar que les peticions s'adapten a l'especificaci&oacute;.
 *
 * @author Eduard Capell Brufau (<a href="mailto:ecapell@uoc.edu">ecapell@uoc.edu</a>)
 * @author Salvador Lorca Sans (<a href="salvinha@uoc.edu">salvinha@uoc.edu</a>)
 *
 * @version 1.0
 *
 */
public class PFC2012Request {

	/**
	 * Ruta de la petici&oacute;.
	 * <p>Una petici&oacute; pot ser de la seg&uuml;ent forma:</p>
	 * <p><code>http://host/get_concepte_paraula?id=12345678</code></p>
	 * <p>En aquest cas la ruta seria <strong>/get_concepte_paraula</strong>.</p>
	 * </p>Pel que fa als par&agrave;metres, es desen en un objecte de tipus <code>java.util.Set</code>, tant
	 * els que s&oacute;n obligatoris com els opcionals.</p>
	 */
	private String path;

	/**
	 * Conjunt de par&agrave;metres obligatoris, cada un representat pel seu nom amb
	 * una cadena de car&agrave;cters.
	 */
	private Set<String> mandatory;

	/**
	 * Conjunt de par&agrave;metres opcionals, cada un d'ells representat pel seu nom
	 * amb una cadena de car&agrave;cters.
	 */
	private Set<String> optional;

	/**
	 * Nombre m&iacute;nim de par&agrave;metres opcionals. A vegades pot ser &uacute;til definir,
	 * pels par&agrave;metres opcionals. Un cas en qu&egrave; aix&#242; seria interessant seria quan
	 * no hi hauria cap par&agrave;metre obligatori, per&#242; n'hi hauria d'haver almenys un
	 * d'opcional, per tal de poder dur a terme l'acci&oacute; concreta.
	 */
	private int minimumOptional;

	/**
	 * Constructor de l'objecte, amb la ruta de la petici&oacute;. Aquest constructor
	 * es limita a inicialitzar els conjunts de par&agrave;metres
	 * @param path La ruta de la petici&oacute;.
	 */
	public PFC2012Request(String path) {
		this.path = path;
		this.mandatory = new HashSet<String>();
		this.optional = new HashSet<String>();
		setupParameters();
	}


	/**
	 * M&egrave;tode que serveix per a inicialitzar els conjunts de par&agrave;metres, en
	 * funci&oacute; de la petici&oacute;.
	 */
	private void setupParameters() {
		if (PFCConstants.PATH_CREATE_CONCEPTE_PARAULA.equals(getPath())) {
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_TEXT_CA);
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_TEXT_JP);
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_LLISTA_ESTUDI);
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_PRON_JAP);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_CA);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_JP);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_PRON_CAT);
			setMinimumOptional(0);
		} else if (PFCConstants.PATH_EDIT_CONCEPTE_PARAULA.equals(getPath())) {
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_ID);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_TEXT_CA);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_TEXT_JP);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_CA);
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_AUDIO_JP);
			setMinimumOptional(1);
		} else if (PFCConstants.PATH_SEARCH_CONCEPTE_PARAULA.equals(getPath())) {
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_TEXT_SEARCH);
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_IDIOMA);
			setMinimumOptional(0);
		} else if (PFCConstants.PATH_GET_CONCEPTE_PARAULA.equals(getPath())) {
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_ID);
			setMinimumOptional(0);
		} else if (PFCConstants.PATH_GET_SOUND.equals(getPath())) {
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_ID);
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_IDIOMA);
			setMinimumOptional(0);
		} else if (PFCConstants.PATH_LIST_WORDS.equals(getPath())) {
			optional.add(PFCConstants.HTTP_REQUEST_PARAM_MAX_RESULTS);
			setMinimumOptional(0);
		} else if (PFCConstants.PATH_GET_WORD_FROM_LIST.equals(getPath())) {
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_LLISTA_ESTUDI);
			setMinimumOptional(0);
		} else if (PFCConstants.PATH_DELETE_WORD.equals(getPath())) {
			mandatory.add(PFCConstants.HTTP_REQUEST_PARAM_ID);
			setMinimumOptional(0);
		}

		/*
		 * Additional default parameters, added for compatibility with
		 * requests coming from Sencha Touch framework.
		 */
		addSenchaTouchDefaultParameters();
	}


	/**
	 * M&egrave;tode que s'utilitza per inicialitzar els par&agrave;metres per defecte que
	 * sempre venen des del framework <i>Sencha Touch</i>.
	 */
	private void addSenchaTouchDefaultParameters() {
		optional.add(PFCConstants.SENCHA_TOUCH_HTTP_PARAM_FALSE);
		optional.add(PFCConstants.SENCHA_TOUCH_HTTP_PARAM_DC);
		optional.add(PFCConstants.SENCHA_TOUCH_HTTP_PARAM_CALLBACK);
	}

	/**
	 * M&egrave;tode que calcula el valor &lt;i&gt;hash&lt;/i&gt; de l'objecte. Es basa
	 * &uacute;nicament en la ruta.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}


	/**
	 * M&egrave;tode per verificar si dos peticions s&oacute;n iguals, es basa en la ruta.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PFC2012Request other = (PFC2012Request) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	/**
	 * M&egrave;tode per obtenir una representaci&oacute; en format cadena de car&agrave;cters d'una
	 * petici&oacute; HTTP. Es basa en la ruta i els par&agrave;metres (obligatoris i
	 * opcionals).
	 */
	@Override
	public String toString() {
		return "PFC2012Request [path=" + path + ", mandatory=" + mandatory
				+ ", optional=" + optional + "]";
	}

	/**
	 * Accessor de lectura de l'atribut <code>path</code>.
	 * @return Cadena de car&agrave;cters amb la ruta de la petici&oacute;.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Accessor d'escriptura de l'atribut <code>path</code>.
	 * @param path Cadena de car&agrave;cters amb el nou valor per la ruta de la
	 * petici&oacute;.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Accessor de lectura de l'atribut <code>mandatory</code>.
	 * @return Objecte de tipus <code>java.util.Set</code>, amb tots els
	 * noms dels par&agrave;metres obligatoris de la petici&oacute;.
	 */
	public Set<String> getMandatory() {
		return mandatory;
	}

	/**
	 * Accessor de lectura de l'atribut <code>optional</code>.
	 * @return Objecte de tipus <code>java.util.Set</code>, amb tots els
	 * noms dels par&agrave;metres opcionals de la petici&oacute;.
	 */
	public Set<String> getOptional() {
		return optional;
	}

	/**
	 * Accessor de lectura de l'atribut <code>minimumOptional</code>.
	 * @return Valor enter amb el nombre m&iacute;nim de par&agrave;metres opcionals que ha
	 * de tenir la petici&oacute; per ser considerada correcta.
	 */
	public int getMinimumOptional() {
		return minimumOptional;
	}

	/**
	 * Accessor d'escriptura de l'atribut <code>minimumOptional</code>.
	 * @param minimumOptional El nou valor (enter) pel nombre m&iacute;nim de par&agrave;metres
	 * opcionals.
	 */
	private void setMinimumOptional(int minimumOptional) {
		this.minimumOptional = minimumOptional;
	}
}
