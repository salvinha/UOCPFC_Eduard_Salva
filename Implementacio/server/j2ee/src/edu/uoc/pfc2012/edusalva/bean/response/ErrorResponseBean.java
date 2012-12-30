package edu.uoc.pfc2012.edusalva.bean.response;

/**
 *
 * Classe gen&egrave;rica de resposta al client que indica que s'ha produ&iuml;t un error.
 *
 * <p>La classe accepta un par&agrave;metre que indica el missatge d'error que s'ha produ&iuml;t, permetent
 * d'aquesta manera que el client pugui processar la resposta.</p>
 *
 * <p>Es recomana utilitzar aquesta classe quan l'error que s'ha produ&iuml;t &eacute;s desconegut, o b&eacute;
 * quan l'error &eacute;s controlat, per&#242; no t&eacute; sentit enviar m&eacute;s informaci&oacute; sin&oacute; un missatge d'error.</p>
 *
 * @author Eduard Capell Brufau (<a href="mailto:ecapell@uoc.edu">ecapell@uoc.edu</a>)
 * @author Salvador Lorca Sans (<a href="salvinha@uoc.edu">salvinha@uoc.edu</a>)
 *
 * @version 1.0
 *
 */
public class ErrorResponseBean extends ResponseBean {

	/**
	 * Atribut amb el missatge d'error.
	 */
	private String errorMessage;

	/**
	 * Constructor per defecte, inicialitza el valor de <code>success</code> a <code>false</code>.
	 * No s'indica cap missatge, per la qual cosa, es limitar&agrave; a indicar que hi ha hagut un error.
	 */
	public ErrorResponseBean() {
		super();
		setSuccess(false);
	}

	/**
	 * Constructor amb un missatge d'error. Estableix el valor de <code>success</code> a
	 * <code>false</code>, i a m&eacute;s inicialitza el missatge d'error.
	 * @param errorMessage Cadena de text amb el missatge d'error.
	 */
	public ErrorResponseBean(String errorMessage) {
		this();
		setErrorMessage(errorMessage);
	}

	/**
	 * Accessor de lectura de l'atribut <code>errorMessage</code>.
	 * @return Cadena de text amb el missatge d'error.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Accessor d'escriptura de l'atribut <code>errorMessage</code>.
	 * @param errorMessage Nou valor pel missatge d'error.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
