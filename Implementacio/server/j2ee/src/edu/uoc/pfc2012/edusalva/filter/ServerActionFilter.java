package edu.uoc.pfc2012.edusalva.filter;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.controller.exception.NoPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongMethodException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongRequestParametersException;
import edu.uoc.pfc2012.edusalva.utils.PFCConstants;
import edu.uoc.pfc2012.edusalva.utils.PFCUtils;

/**
 * Filtre que verifica que les accions sol·licitades pel client són correctes.
 *
 * <p>
 * Una petici&oacute; &eacute;s correcta si:
 * <ul>
 * 	<li>T&eacute; una ruta correcta</li>
 * 	<li>T&eacute; els par&agrave;metres correctes</li>
 * </ul>
 * </p>
 *
 * <p>
 * Si arriba una petici&oacute; del client i la petici&oacute; &eacute;s correcta, aquesta tira
 * endavant en la cadena de filtres.
 * </p>
 *
 * <p>
 * Si, per contra, arriba una petici&oacute; del client, i no &eacute;s correcta, la petici&oacute;
 * es descarta, enviant una notificaci&oacute; de l'error que s'ha produ&iuml;t.
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
public class ServerActionFilter implements Filter {

	/**
	 * Objecte Logger.
	 */
	private static final Logger logger = Logger.getLogger(ServerActionFilter.class.getName());

	/**
	 * Mètode que examina la petició i comprova que sigui correcta.
	 * Si no és correcta, la rebutjarà, mentre que si és correcta, la
	 * tirarà endavant en la cadena de filtres.
	 * @param req Petició del client.
	 * @param res Resposta al client.
	 * @param chain Cadena de filtres.
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hr = null;

		// 1 - La peticio ha de ser HTTP
		try {
			hr = (HttpServletRequest) req;
		} catch (ClassCastException e) {
			// TODO Handle.
			logger.error("Problems casting to HttpServletRequest.");
			return;
		}

		// 2 - El metode de la peticio ha de ser correcte.
		try {
			PFCUtils.checkRequestMethod(hr);
		} catch (WrongMethodException e) {
			// TODO Handle.
			logger.error(e.getMessage());
			return;
		}


		// 3 - La ruta ha de ser correcta.
		try{
			PFCUtils.checkPath(hr);
		}catch (NoPathException e) {
			// TODO Handle.
			logger.error("No path!");
			return;
		} catch (WrongPathException e) {
			// TODO Handle.
			logger.error("Wrong path!");
			return;
		} catch (Exception e) {
			// TODO Handle.
			logger.error("Unknown!!!");
			return;
		}

		// 4 - Els parametres han de ser correctes.
		try {
			PFCUtils.checkRequestParameters(hr);
		} catch (WrongRequestParametersException e) {
			logger.error("Wrong request parameters!");
			Writer w = res.getWriter();
			w.write(PFCConstants.RESPONSE_WRONG_PARAMETERS);
			w.flush();
			w.close();
			return;
		}

		// En aquest punt, podem garantir que la peticio esta ben formada.
		chain.doFilter(req, res);
	}


	/**
	 * Mètode que s'executa al final del cicle de vida del filtre. No fa res.
	 */
	@Override
	public void destroy() {}

	/**
	 * Mètode que s'executa a l'inici del cicle de vida del filtre. No fa res.
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {}

}
