package edu.uoc.pfc2012.edusalva.controller.exception;

/**
 * Classe que representa l'excepció que es produeix quan la petició del client
 * utilitza un mètode HTTP no suportat (ha de ser GET o POST).
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
public class WrongMethodException extends WrongRequestException {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 354276024963910350L;

	/**
	 * Constructor per defecte.
	 */
	public WrongMethodException() {
		super();
	}

	/**
	 * Constructor amb un missatge d'error per l'excepció.
	 * @param message El missatge d'error
	 */
	public WrongMethodException(String message) {
		super(message);
	}

}
