package edu.uoc.pfc2012.edusalva.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class EncodingFilter implements Filter {

	private static final Logger logger = Logger.getLogger(EncodingFilter.class.getName());
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// Tomcat Connector configuration to accept UTF-8 requests: URIEncoding="UTF-8"
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json;charset=UTF-8");
		
		// At this point, the request may be forwarded.
		chain.doFilter(req, res);
	}
	
	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig config) throws ServletException {}

}
