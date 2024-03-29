package com.josip.physical.activity.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter  implements Filter{

	   @Override
	    public void init(FilterConfig config) throws ServletException {
	        // NOOP.
	    }

	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	        request.setCharacterEncoding("UTF-8");
	        chain.doFilter(request, response);
	    }

	    @Override
	    public void destroy() {
	        // NOOP.
	    }

}
