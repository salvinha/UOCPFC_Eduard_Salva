package edu.uoc.pfc2012.edusalva.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Clase que representa un concepte paraula, amb els tots els seus atributs (
 * text en catal&agrave; i japon&egrave;s; pronunciaci&oacute; en catal&agrave;
 * i japon&egrave;s; &agrave;udio en catal&agrave; i japon&egrave;s).
 * Tamb&eacute; inclou el codi de la llista al qual pertany la paraula, i l'id de la
 * pr&#242;pia paraula, que servir&agrave; per referenciar-la a la base de dades.
 *
 * Aquesta classe és l'objecte fonamental de l'aplicació, i representa el bean
 * que es fa servir sempre per intercanviar informació entre el client i el
 * servidor.
 *
 * @author Eduard Capell Brufau (<a href="mailto:ecapell@uoc.edu">ecapell@uoc.edu</a>)
 * @author Salvador Lorca Sans (<a href="salvinha@uoc.edu">salvinha@uoc.edu</a>)
 *
 */
public class KoncepteParaula {

	/**
	 * Cadena de text amb l'ID de la paraula a la base de dades.
	 */
	private String id;

	/**
	 * Cadena de text amb el codi de la llista al qual pertany la paraula.
	 */
	private String idLlista;

	/**
	 * Text tal com s'escriu la paraula en l'idioma català.
	 */
	private String textcat;

	/**
	 * Text tal com s'escriu la paraula en l'idioma japonès.
	 */
	private String textjap;

	/**
	 * Text que denota la pronunciació de la paraula en l'idioma català.
	 */
	@JsonIgnore
	private String proncat;


	/**
	 * Text que denota la pronunciació de la paraula en l'idioma japonès.
	 */
	private String pronjap;

	@JsonIgnore
	private String audioCatala;

	@JsonIgnore
	private String audioJapones;

	public KoncepteParaula() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdLlista() {
		return idLlista;
	}

	public void setIdLlista(String idLlista) {
		this.idLlista = idLlista;
	}

	public String getTextcat() {
		return textcat;
	}

	public void setTextcat(String textcat) {
		this.textcat = textcat;
	}

	public String getTextjap() {
		return textjap;
	}

	public void setTextjap(String textJapones) {
		this.textjap = textJapones;
	}

	public String getPronjap() {
		return this.pronjap;
	}


	public void setPronjap(String pronjap) {
		this.pronjap = pronjap;
	}


	public String getAudioCatala() {
		return audioCatala;
	}

	public void setAudioCatala(String audioCatala) {
		this.audioCatala = audioCatala;
	}

	public String getAudioJapones() {
		return audioJapones;
	}

	public void setAudioJapones(String audioJapones) {
		this.audioJapones = audioJapones;
	}

	@Override
	public String toString() {
		return "KoncepteParaula [textCatala=" + textcat + ", textJapones="
				+ textjap + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((textcat == null) ? 0 : textcat.hashCode());
		result = prime * result
				+ ((textjap == null) ? 0 : textjap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KoncepteParaula other = (KoncepteParaula) obj;
		if (textcat == null) {
			if (other.textcat != null)
				return false;
		} else if (!textcat.equals(other.textcat))
			return false;
		if (textjap == null) {
			if (other.textjap != null)
				return false;
		} else if (!textjap.equals(other.textjap))
			return false;
		return true;
	}

	public void setProncat(String proncat) {
		this.proncat = proncat;
	}

	public String getProncat() {
		return proncat;
	}
}
