package edu.uoc.pfc2012.edusalva.bean.response;

/**
*
* Classe que representa objectes de tipus resposta cap al client.
*
* <p>Aquestes respostes s&oacute;n enviades al client en format
* <a href="http://www.json.org/">JSON</a>.</p>
*
* <p>Qualsevol resposta cap al client heretar&agrave; d'aquest objecte, que t&eacute; un
* &uacute;nic atribut: <code>success</code>, per indicar si la petici&oacute; que s'ha
* fet pr&egrave;viament ha tingut &egrave;xit o no.</p>
*
* @author Eduard Capell Brufau (<a href="mailto:ecapell@uoc.edu">ecapell@uoc.edu</a>)
* @author Salvador Lorca Sans (<a href="salvinha@uoc.edu">salvinha@uoc.edu</a>)
*
* @version 1.0
*
*/
public abstract class ResponseBean {

	/**
	 * Attribut que indica si la petició ha tingut èxit (<code>true</code>) o no (<code>false</code>).
	 */
	private boolean success;

	/**
	 * Constructor per defecte. Es limita a posar el valor de <code>success</code> al
	 * seu valor per defecte (<code>false</code>).
	 */
	public ResponseBean() {
		setSuccess(false);
	}

	/**
	 * Accessor de lectura de l'atribut <code>success</code>, per saber quin
	 * resultat ha tingut la petici&oacute; (<code>True / False</code>).
	 * @return
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Accessor d'escriptura de l'atribut <code>success</code>.
	 * @param success Nou valor boole&agrave; que indica l'&egrave;xit o frac&agrave;s de la petici&oacute;.
	 */
	protected void setSuccess(boolean success) {
		this.success = success;
	}

}
