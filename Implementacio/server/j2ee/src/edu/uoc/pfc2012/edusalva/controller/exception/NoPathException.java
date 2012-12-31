package edu.uoc.pfc2012.edusalva.controller.exception;

/**
 *
 * Classe que representa l'excepci&oacute; que es produeix quan en la petici&oacute; del client
 * no hi ha una ruta especificada.
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
public class NoPathException extends WrongPathException {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 8191156665546881366L;

	/**
	 * Constructor per defecte.
	 */
	public NoPathException() {
		super();
	}

	/**
	 * Constructor amb un missatge d'error per l'excepció.
	 * @param message El missatge d'error
	 */
	public NoPathException(String message) {
		super(message);
	}

}
