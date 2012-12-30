package edu.uoc.pfc2012.edusalva.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.filter.ServerActionFilter;
import edu.uoc.pfc2012.edusalva.worker.AbstractWorker;
import edu.uoc.pfc2012.edusalva.worker.WorkerFactory;


/**
 *
 * Aquest &eacute;s l'&uacute;nic punt d'entrada a l'aplicaci&oacute; del servidor.
 * <p>
 * Aquest servlet rep qualsevol petici&oacute;, i la delega de la manera m&eacute;s apropiada.
 * </p>
 *
 * <p>
 * Les peticions rebudes per aquest Servlet ja s'han verificat pr&egrave;viament (&eacute;s
 * a dir, nom&eacute;s arriben aqu&iacute; les peticions realment v&agrave;lides. D'aquesta manera, el
 * Servlet no ha de fer comprovacions extra, que ja s'han fet abans d'arribar aqu&iacute;.
 * L'encarregat d'efectuar la comprovació és un objecte de tipus
 * <code>ServerActionFilter</code>
 * .
 * </p>
 *
 * <p>
 * S'assumeix que totes les peticions estan codificades en UTF-8. Aix&#242; s'ha
 * d'assegurar a trav&eacute;s de la configuraci&oacute; del
 * <code>Connector</code>
 * en el fitxer de configuraci&oacute; del servidor (normalment
 * </code>$SERVER_HOME/conf/server.xml</code>
 * ).
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
 * @see ServerActionFilter
 *
 */
public class ControllerServlet extends HttpServlet {

	/**
	 * Valor <code>long</code> amb el serial version UID per aquesta classe.
	 */
	private static final long serialVersionUID = 1866281077747194045L;

	/**
	 * Objecte Logger.
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ControllerServlet.class.getName());


	/**
	 * M&egrave;tode per atendre les peticions entrants. Totes les peticions es
	 * deleguen al m&egrave;tode
	 * <code>processRequest()</code>
	 * , que ser&agrave; qui distribueixi la feina en funci&oacute; de la petici&oacute;.
	 * @param req Petici&oacute; HTTP.
	 * @param res Resposta HTTP.
	 * @throws ServletException Si la petici&oacute; no es pot tractar.
	 * @throws IOException Si es produeix alguna excepci&oacute; de tipus I/O en el
	 * processament de la petici&oacute;.
	 * @see #processRequest(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}


	/**
	 * M&egrave;tode que executa el que correspongui en funci&oacute; de la petici&oacute;, delegant
	 * la responsabilitat de l'execuci&oacute; a un objecte de tipus
	 * <code>AbstractWorker</code>
	 * .
	 * <p>
	 * La classe
	 * <code>WorkerFactory</code>
	 * proporciona el <i>worker</i> apropiat en funci&oacute; de la petici&oacute;. Un cop es disposa
	 * de l'objecte <i>worker</i>, ja es pot cridar al m&egrave;tode
	 * <code>processRequest</code>
	 * per procedir a efectuar el que correspongui segons la petici&oacute;.
	 * </p>
	 *
	 * @param req La petici&oacute; que ha arribat del client.
	 * @param res La resposta que s'enviar&agrave; al client un cop finalitzada
	 * l'execuci&oacute;.
	 *
	 * @see AbstractWorker
	 * @see AbstractWorker#processRequest()
	 * @see WorkerFactory
	 *
	 */
	private void processRequest(HttpServletRequest req, HttpServletResponse res) {
		AbstractWorker worker = WorkerFactory.getWorker(req, res);
		worker.processRequest();
	}

}

