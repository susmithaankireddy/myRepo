package com.demo.spring;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class SImpleCORSFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest requestToUse=(HttpServletRequest)request;
		HttpServletResponse responseToUse=(HttpServletResponse)response;
		responseToUse.setHeader("Access-Control-Allow-Origin", "*");
		responseToUse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,DELETE,PATCH");
		responseToUse.setHeader("Access-Control-Allow-Headers", "Origin,Content-Type,Accept,x-auth,value,X-Requested-With");
		
		chain.doFilter(requestToUse, responseToUse);

	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
			Filter.super.init(filterConfig);
			System.out.println("CORS Filter initialized...");;
	}

}