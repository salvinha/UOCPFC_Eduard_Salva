package edu.uoc.pfc2012.edusalva.bean.response;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;

/**
 *
 * Classe que encapsula un koncepte en una resposta que li ser&agrave; enviada al
 * client.
 * <p>
 * Es considera que una resposta que inclou un koncepte sempre &eacute;s una resposta
 * amb &egrave;xit, per la qual cosa sempre s'inicialitzar&agrave; l'atribut
 * <code>success</code>
 * amb el valor
 * <code>true</code>
 * en el moment de crear els objectes d'aquesta classe.
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
 */
public class KonceptResponseBean extends ResponseBean {

	/**
	 * Atribut del koncepte que s'encapsula en aquesta resposta.
	 */
	private KoncepteParaula koncept;

	/**
	 * Constructor de la classe, que inicialitza el koncepte, i el valor de
	 * l'atribut <code>success</code> a <code>true</code>.
	 * @param koncept
	 */
	public KonceptResponseBean(KoncepteParaula koncept) {
		super();
		setSuccess(true);
		this.koncept = koncept;
	}

	/**
	 * Accessor de lectura de l'atribut <code>koncept</code>.
	 * @return Objecte de tipus
	 * <code>KoncepteParaula</code>
	 * amb l'objecte que ser&agrave; enviat en la resposta.
	 */
	public KoncepteParaula getKoncept() {
		return koncept;
	}

}
