package edu.uoc.pfc2012.edusalva.bean.response;

/**
 * Classe que representa una resposta que cont&eacute; l'ID d'un KoncepteParaula.
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
public class KonceptIdResponseBean extends ResponseBean {

	/**
	 * Atribut de tipus <code>String</code> que conté l'ID de la paraula que
	 * es vol comunicar al client.
	 */
	private String id;

	/**
	 * Constructor per defecte. Es considera que la petici&oacute; ha tingut &egrave;xit,
	 * per&#242; tot i aix&iacute; no es posa cap valor a l'ID de la paraula. Pot ser &uacute;til
	 * per inicialitzar l'objecte, i establir el valor de l'ID a posteriori.
	 */
	public KonceptIdResponseBean() {
		super();
		setSuccess(true);
	}

	/**
	 * Constructor amb el paràmetre de l'ID del koncepte.
	 * @param id Cadena de text amb l'ID del koncepte que s'enviar&agrave; al client.
	 */
	public KonceptIdResponseBean(String id) {
		this();
		setId(id);
	}

	/**
	 * Accessor de lectura per l'atribut <code>id</code>.
	 * @return Cadena de text amb l'ID del koncepte.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Accessor d'escriptura per l'atribut <code>id</code>.
	 * @param id Nou valor per l'ID del koncepte.
	 */
	public void setId(String id) {
		this.id = id;
	}

}
