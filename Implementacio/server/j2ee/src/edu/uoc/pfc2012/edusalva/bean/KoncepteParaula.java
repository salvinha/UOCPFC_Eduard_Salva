package edu.uoc.pfc2012.edusalva.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Clase que representa un concepte paraula, amb els tots els seus atributs (
 * text en catal&agrave; i japon&egrave;s; pronunciaci&oacute; en catal&agrave;
 * i japon&egrave;s; &agrave;udio en catal&agrave; i japon&egrave;s).
 * Tamb&eacute; inclou el codi de la llista al qual pertany la paraula, i l'id de la
 * pr&#242;pia paraula, que servir&agrave; per referenciar-la a la base de dades.
 *
 * Aquesta classe &eacute;s l'objecte fonamental de l'aplicaci&oacute;, i representa el bean
 * que es fa servir sempre per intercanviar informaci&oacute; entre el client i el
 * servidor.
 *
 * Ens referim a una paraula com a 'Koncepte', per evitar parlar de manera
 * espec&iacute;fica de 'Paraula', ja que un Koncepte &eacute;s m&eacute;s que una simple paraula (&eacute;s
 * una paraula en dos idiomes, i &eacute;s una manera de pronunciar-la en cada un
 * d'aquests dos idiomes).
 *
 * @author Eduard Capell Brufau (<a href="mailto:ecapell@uoc.edu">ecapell@uoc.edu</a>)
 * @author Salvador Lorca Sans (<a href="salvinha@uoc.edu">salvinha@uoc.edu</a>)
 *
 * @version 1.0
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
	 * Text tal com s'escriu la paraula en l'idioma catal&agrave;.
	 */
	private String textcat;

	/**
	 * Text tal com s'escriu la paraula en l'idioma japon&egrave;s.
	 */
	private String textjap;

	/**
	 * Text que denota la pronunciaci&oacute; de la paraula en l'idioma catal&agrave;.
	 * No se serialitzar&agrave; en les comunicacions JSON, perqu&egrave; es posa aqu&iacute; per
	 * un &uacute;s futur.
	 */
	@JsonIgnore
	private String proncat;


	/**
	 * Text que denota la pronunciaci&oacute; de la paraula en l'idioma japon&egrave;s.
	 */
	private String pronjap;

	/**
	 * Text que indica la ruta del sistema de fitxers on es desar&agrave; un fitxer
	 * d'&agrave;udio amb la paraula en idioma catal&agrave;.
	 * No se serialitzar&agrave; en les comunicacions JSON, perqu&egrave; es posa aqu&iacute; per un
	 * &uacute;s futur.
	 */
	@JsonIgnore
	private String audioCatala;

	/**
	 * Text que indica la ruta del sistema de fitxers on es desar&agrave; un fitxer
	 * d'&agrave;udio amb la paraula en idioma japon&egrave;s.
	 * No se serialitzar&agrave; en les comunicacions JSON, perqu&egrave; es posa aqu&iacute; per un
	 * possible &uacute;s futur.
	 */
	@JsonIgnore
	private String audioJapones;

	/**
	 * Constructor buit. No t&eacute; funcionalitat.
	 */
	public KoncepteParaula() {}

	/**
	 * Accessor de lectura per l'atribut <code>id</code>.
	 * @return Cadena de car&agrave;cters amb l'id del koncepte.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Accessor d'escriptura per l'atribut <code>id</code>.
	 * @param id Cadena de car&agrave;cters amb el nou valor per l'id del koncepte.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Accessor de lectura per l'atribut <code>idLlista</code>.
	 * @return Cadena de car&agrave;cters amb el codi de la llista a la qual est&agrave;
	 * assignada aquest koncepte.
	 */
	public String getIdLlista() {
		return idLlista;
	}

	/**
	 * Accessor d'escriptura per l'atribut <code>idLlista</code>.
	 * @param idLlista Cadena de car&agrave;cters amb el codi de la llista a la qual
	 * volem associar aquest koncepte.
	 */
	public void setIdLlista(String idLlista) {
		this.idLlista = idLlista;
	}

	/**
	 * Accessor de lectura per l'atribut <code>textcat</code>.
	 * @return Cadena de car&agrave;cters amb el text de la paraula en idioma catal&agrave;.
	 */
	public String getTextcat() {
		return textcat;
	}


	/**
	 * Accessor d'escriptura per l'atribut <code>textcat</code>.
	 * @param textcat Cadena de car&agrave;cters amb el nou valor pel text de la
	 * paraula en l'idioma catal&agrave;.
	 */
	public void setTextcat(String textcat) {
		this.textcat = textcat;
	}

	/**
	 * Accessor de lectura per l'atribut <code>textjap</code>.
	 * @return Cadena de car&agrave;cters amb el text (<i>kanji</i>) de la paraula
	 * en idioma japon&egrave;s.
	 */
	public String getTextjap() {
		return textjap;
	}

	/**
	 * Accessor d'escriptura per l'atribut <code>textjap</code>.
	 * @param textJapones Cadena de car&agrave;cters amb el nou valor pel text de la
	 * paraula en l'idioma japon&egrave;s. Aquesta cadena de car&agrave;cters, codificada en
	 * UTF-8, &eacute;s el <i>kanji</i> de la paraula japonesa.
	 */
	public void setTextjap(String textJapones) {
		this.textjap = textJapones;
	}

	/**
	 * Accessor de lectura de l'atribut <code>pronjap</code>.
	 * @return Text que representa la pronunciaci&oacute; de la paraula japonesa.
	 */
	public String getPronjap() {
		return this.pronjap;
	}

	/**
	 * Accessor d'escriptura de l'atribut <code>pronjap</code>.
	 * @param pronjap Cadena de car&agrave;cters amb la nova pronunciaci&oacute; japonesa per
	 * aquest koncepte.
	 */
	public void setPronjap(String pronjap) {
		this.pronjap = pronjap;
	}

	/**
	 * Accessor de lectura de l'atribut <code>proncat</code>.
	 * @return Text que representa la pronunciaci&oacute; de la paraula catalana.
	 */
	public String getProncat() {
		return proncat;
	}

	/**
	 * Accessor d'escriptura de l'atribut <code>proncat</code>.
	 * @param proncat Cadena de car&agrave;cters amb la nova pronunciaci&oacute; catalana per
	 * aquest koncepte.
	 */
	public void setProncat(String proncat) {
		this.proncat = proncat;
	}


	/**
	 * Accessor de lectura per l'atribut <code>audioCatala</code>.
	 * @return Cadena de car&agrave;cters que representa la ruta dins del sistema de
	 * fitxers del servidor on hi ha la representaci&oacute; &agrave;udio de la paraula, en
	 * l'idioma catal&agrave;.
	 */
	public String getAudioCatala() {
		return audioCatala;
	}

	/**
	 * Accessor d'escriputra per l'atribut <code>audioCatala</code>.
	 * @param audioCatala Nova ruta on hi ha la representaci&oacute; &agrave;udio de la
	 * paraula, en l'idioma catal&agrave;.
	 */
	public void setAudioCatala(String audioCatala) {
		this.audioCatala = audioCatala;
	}

	/**
	 * Accessor de lectura per l'atribut <code>audioJapones</code>.
	 * @return Cadena de car&agrave;cters que representa la ruta dins del sistema de
	 * fitxers del servidor on hi ha la representaci&oacute; &agrave;udio de la paraula, en
	 * l'idioma japon&egrave;s.
	 */
	public String getAudioJapones() {
		return audioJapones;
	}

	/**
	 * Accessor d'escriputra per l'atribut <code>audioJapones</code>.
	 * @param audioJapones Nova ruta on hi ha la representaci&oacute; &agrave;udio de la
	 * paraula, en l'idioma japon&egrave;s.
	 */
	public void setAudioJapones(String audioJapones) {
		this.audioJapones = audioJapones;
	}


	/**
	 * M&egrave;tode que retorna una representaci&oacute; del koncepte en forma de cadena
	 * de car&agrave;cters. Es basa en el text en catal&agrave; i el text en japon&egrave;s.
	 * @return Cadena de car&agrave;cters amb la representaci&oacute; de la paraula.
	 */
	@Override
	public String toString() {
		return "KoncepteParaula [textCatala=" + textcat + ", textJapones="
				+ textjap + "]";
	}

	/**
	 * M&egrave;tode que calcula el codi <i>hash</i> d'aquest koncepte. Es basa en
	 * el text catal&agrave; i el japon&egrave;s.
	 * @return Valor enter amb el codi <i>hash</i> d'aquest koncepte.
	 */
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

	/**
	 * M&egrave;tode que verifica si aquest koncepte &eacute;s igual o no a un altre objecte.
	 * Es basa en el text catal&agrave; i el japon&egrave;s: si l'objecte rebut per par&agrave;metre
	 * &eacute;s de tipus Koncepte, i coincideixen en el text catal&agrave; i en el text
	 * japon&egrave;s, es considrra que els dos objectes s&oacute;n iguals; en qualsevol altre
	 * cas els dos objectes es consideren diferents.
	 * @return Cert si els dos objectes s&oacute;n iguals (de tipus Koncepte, i amb
	 * els dos textos iguals), Fals en cas contrari.
	 */
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

}
