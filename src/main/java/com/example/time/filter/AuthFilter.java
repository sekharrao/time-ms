package com.example.time.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;

import com.example.time.exception.AuthException; 

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthFilter implements Filter {

	private static final String AUTH_SECRET = "S)N'/vXP/U?7@.\"]";
	private static final String NOT_AUTHORIZED = "Not authorized!";;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

       String auth = request.getHeader("Authorization");
       String method = request.getMethod();
       if(validateAuthToken(method, auth)){

        //call next filter in the filter chain
        filterChain.doFilter(request, response);
       }else {
    	   throw new AuthException(NOT_AUTHORIZED);
       }
		
	}

	private boolean validateAuthToken(String method, String auth) {
		if (HttpMethod.GET.matches(method) ) {
			return AUTH_SECRET.equalsIgnoreCase(auth);
		}
		return true;
		
	}

}
