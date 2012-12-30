package edu.uoc.pfc2012.edusalva.bean.response;

/**
 *
 * Classe que encapsula una resposta a una petici&oacute; que ha acabat correctament.
 *
 * <p>
 * Aquesta resposta es limita a indicar que tot ha anat b&eacute;, sense enviar cap
 * m&eacute;s tipus d'informaci&oacute; en la resposta.
 * </p>
 *
 * <p>
 * &Eacute;s &uacute;til en situacions en qu&egrave; es vol enviar una confirmaci&oacute; de finalitzaci&oacute;
 * d'una operaci&oacute;, per exemple quan es vol esborrar una paraula. No t&eacute; sentit
 * donar m&eacute;s informaci&oacute;, excepte que tot ha anat correctament.
 * </p>
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
public class SuccessResponseBean extends ResponseBean {

	/**
	 * Constructor per defecte, inicialitza l'atribut <code>success</code> amb
	 * el valor <code>true</code>.
	 */
	public SuccessResponseBean() {
		setSuccess(true);
	}

}
