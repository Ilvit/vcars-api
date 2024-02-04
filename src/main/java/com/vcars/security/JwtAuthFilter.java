package com.vcars.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.vcars.services.ConnectedUserService;

@Component
public class JwtAuthFilter implements Filter {

	ConnectedUsersPage connectedUsersPage;
	ConnectedUserService connectedUserService;

	public JwtAuthFilter(ConnectedUsersPage connectedUsersPage, ConnectedUserService connectedUserService) {
		this.connectedUsersPage = connectedUsersPage;
		this.connectedUserService = connectedUserService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest=(HttpServletRequest) request;
		String accessToken=servletRequest.getHeader("Authorization");
		try {
			accessToken=accessToken.replace("Bearer ", "");
		} catch (NullPointerException e) {
			System.out.println("This is the first time ..."+accessToken);
		}
		if(connectedUsersPage.containsJwt(accessToken)) {
			if(connectedUsersPage.isDisconnected(accessToken)) {
				try {
					SecurityContextHolder.getContext().setAuthentication(null);
				} catch (Exception e) {
					System.out.println("set authentication error !");
				}
			}
		}else {
			if(SecurityContextHolder.getContext().getAuthentication()!=null) {
				SecurityContextHolder.getContext().setAuthentication(null);
			}
		}
		chain.doFilter(request, response);
	}

}
