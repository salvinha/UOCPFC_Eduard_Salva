package edu.uoc.pfc2012.edusalva.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

/**
 * Classe de tipus Filter, que serveix per assegurar que les respostes que
 * envia l'aplicació estaran en la codificació correcta (UTF-8).
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
public class EncodingFilter implements Filter {

	/**
	 * Objecte Logger.
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(EncodingFilter.class.getName());

	/**
	 * M&egrave;tode que executa l'acci&oacute; de filtratge, consistent a assegurar que
	 * la resposta est&agrave; codificada com s'espera al client (UTF-8), i que el
	 * <code>Content-Type</code>
	 * &eacute;s el correcte (JSON).
	 * @param req La petició del client.
	 * @param res La resposta cap al client.
	 * @param chain La cadena de filtres.
	 * @throws ServletException Si la petici&oacute; no es pot tractar.
	 * @throws IOException Si es produeix alguna excepci&oacute; de tipus I/O en el
	 * processament de la petici&oacute;.
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		res.setCharacterEncoding(PFCConstants.HTTP_RESPONSE_ENCODING);
		res.setContentType(PFCConstants.HTTP_RESPONSE_CONTENT_TYPE);

		chain.doFilter(req, res);
	}

	/**
	 * M&egrave;tode que s'executa al final del cicle de vida d'aquest filtre. No fa res.
	 */
	@Override
	public void destroy() {}

	/**
	 * M&egrave;tode que s'execute a l'inici del cicle de vida d'aquest filtre. No fa res.
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {}

}
