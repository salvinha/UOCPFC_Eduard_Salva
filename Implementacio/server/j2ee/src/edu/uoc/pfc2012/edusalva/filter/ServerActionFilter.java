package edu.uoc.pfc2012.edusalva.filter;

import java.io.IOException;

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
import edu.uoc.pfc2012.edusalva.utils.HttpUtils;

public class ServerActionFilter implements Filter {

	private static final Logger logger = Logger.getLogger(ServerActionFilter.class.getName());
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hr = null;
		
		// 1 - Request has to be HTTP.
		try {
			// Casting request to HttpServletRequest and checking the path.
			hr = (HttpServletRequest) req;
		} catch (ClassCastException e) {
			// TODO Handle.
			logger.error("Problems casting to HttpServletRequest.");
			return;
		} 
		
		// 2 - Request method must be right.
		try {
			HttpUtils.checkRequestMethod(hr);
		} catch (WrongMethodException e) {
			// TODO Handle.
			logger.error(e.getMessage());
			return;
		}

		
		// 3 - Path must be OK.
		try{
			HttpUtils.checkPath(hr);
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

		// At this point, the request is OK to be forwarded.
		chain.doFilter(req, res);
	}
	
	
	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig config) throws ServletException {}

}
