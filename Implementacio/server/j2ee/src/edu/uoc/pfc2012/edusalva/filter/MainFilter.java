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

import edu.uoc.pfc2012.edusalva.controller.ControllerServlet;
import edu.uoc.pfc2012.edusalva.controller.exception.NoPathException;
import edu.uoc.pfc2012.edusalva.controller.exception.WrongPathException;
import edu.uoc.pfc2012.edusalva.utils.HttpUtils;

public class MainFilter implements Filter {

	private static final Logger logger = Logger.getLogger(MainFilter.class.getName());
	
	@Override
	public void destroy() {
		logger.info("Filter destroy.");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		logger.info("Filter.doFilter()");
//		req.setCharacterEncoding("UTF-8");
		
		try {
			HttpServletRequest hr = (HttpServletRequest) req;
			HttpUtils.checkPath(hr);
		} catch (NoPathException e) {
			// TODO Handle.
			logger.error("No path!");
			throw new IOException("No path.");
		} catch (WrongPathException e) {
			// TODO Handle.
			logger.error("Wrong path!");
			throw new IOException("Wrong path!");
		} catch (Exception e) {
			// TODO Handle.
			logger.error("Unknown!!!");
			throw new IOException("Unknown!");
		}
		
		// At this point, the request may be forwarded.
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("Filter.init()");
	}

}
