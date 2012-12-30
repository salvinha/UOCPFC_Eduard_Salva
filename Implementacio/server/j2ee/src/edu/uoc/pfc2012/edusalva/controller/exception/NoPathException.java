package edu.uoc.pfc2012.edusalva.controller.exception;

/**
 * <p>
 * Projecte Final de Carrera - Desenvolupament d'aplicacions m&#242;bils en HTML5
 * </p>
 *
 * <p>
 * Data: Gener de 2012
 * </p>
 *
 * @author Eduard Capell Brufau (<a href="mailto:ecapell@uoc.edu">ecapell@uoc.edu</a>)
 * @author Salvador Lorca Sans (<a href="salvinha@uoc.edu">salvinha@uoc.edu</a>)
 *
 * @version 1.0
 *
 */
public class NoPathException extends WrongPathException {

	private static final long serialVersionUID = 8191156665546881366L;

	public NoPathException(String message) {
		super(message);
	}

	public NoPathException() {
		super();
	}

}
