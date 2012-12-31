package edu.uoc.pfc2012.edusalva.controller.exception;

/**
 * Classe genèrica que representa una excepció que es produeix quan hi ha
 * errors amb una petició.
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
public abstract class WrongRequestException extends PFC2012ServerException {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 4077920785287480105L;

	/**
	 * Constructor per defecte.
	 */
	public WrongRequestException() {
	}

	/**
	 * Constructor amb un missatge d'error per l'excepció.
	 * @param message El missatge d'error
	 */
	public WrongRequestException(String message) {
		super(message);
	}

}
