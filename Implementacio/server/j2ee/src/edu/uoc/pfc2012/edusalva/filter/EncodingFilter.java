package edu.uoc.pfc2012.edusalva.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.utils.PFCConstants;

public class EncodingFilter implements Filter {

	private static final Logger logger = Logger.getLogger(EncodingFilter.class.getName());
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// Tomcat Connector configuration to accept UTF-8 requests: URIEncoding="UTF-8"
		res.setCharacterEncoding(PFCConstants.HTTP_RESPONSE_ENCODING);
		res.setContentType(PFCConstants.HTTP_RESPONSE_CONTENT_TYPE);

		HttpServletResponse hr = (HttpServletResponse) res;
		hr.addHeader("Access-Control-Allow-Origin", "*");
		
		// At this point, the request may be forwarded.
		chain.doFilter(req, res);
	}
	
	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig config) throws ServletException {}

}
