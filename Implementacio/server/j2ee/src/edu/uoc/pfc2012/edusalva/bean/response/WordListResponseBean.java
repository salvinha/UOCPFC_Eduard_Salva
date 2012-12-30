package edu.uoc.pfc2012.edusalva.bean.response;

import java.util.List;
import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;

/**
 * Classe que encapsula una llista de paraules (
 * <code>KoncepteParaula</code>
 * )
 * en una resposta.
 * <p>
 * Aquesta classe &eacute;s &uacute;til quan s'han d'enviar un conjunt de resultats de tipus
 * <code>KoncepteParaula</code>
 * .
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
public class WordListResponseBean extends ResponseBean {

	/**
	 * Atribut que conté la llista de konceptes.
	 */
	private List<KoncepteParaula> list;

	/**
	 * Constructor que inicialitza la llista de konceptes.
	 * @param list Objecte de tipus
	 * <code>java.util.List<KoncepteParaula></code>
	 * , amb la llista dels objectes de tipus Koncepte que cont&eacute; la
	 * resposta que s'enviar&agrave; al client.
	 */
	public WordListResponseBean(List<KoncepteParaula> list) {
		this.list = list;
		setSuccess(true);
	}

	/**
	 * Accessor de lectura de l'atribut
	 * <code>list</code>
	 * .
	 * @return Objecte de tipus
	 * <code>java.util.List<KoncepteParaula></code>
	 * amb la llista de paraules contingudes en aquesta resposta.
	 */
	public List<KoncepteParaula> getList() {
		return list;
	}
}
