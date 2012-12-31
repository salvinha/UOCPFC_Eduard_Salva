package edu.uoc.pfc2012.edusalva.controller.exception;

/**
 * Classe gen&egrave;rica d'excepcions del m&#242;dul de servidor de l'aplicaci&oacute; PFC2012.
 *
 * <p>
 * Les excepcions generades durant tota l'aplicaci&oacute; seran hereves d'aquesta.
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
public abstract class PFC2012ServerException extends Exception {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1564217814641349930L;

	/**
	 * Constructor per defecte.
	 */
	public PFC2012ServerException() {
		super();
	}

	/**
	 * Constructor amb un missatge d'error per l'excepció.
	 * @param message El missatge d'error
	 */
	public PFC2012ServerException(String message) {
		super(message);
	}

}
