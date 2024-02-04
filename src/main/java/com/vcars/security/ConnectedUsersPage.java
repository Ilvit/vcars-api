package com.vcars.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcars.repositories.ConnectedUserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ConnectedUsersPage {
	@Autowired
	ConnectedUserRepository connectedUserRepository;
	
	public void addConnectedUser(String username, String jwt) {
		ConnectedUser conUser=ConnectedUser.builder()
				.username(username)
				.jwt(jwt)
				.disconnected(false)
				.onLine(true)
				.build();
		connectedUserRepository.save(conUser);
	}
	
	public boolean containsJwt(String jwt) {
		try {
			ConnectedUser cu=connectedUserRepository.findByJwt(jwt);
			if(cu!=null) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	public boolean containsUser(String username) {
		try {
			ConnectedUser cu=connectedUserRepository.findByUsername(username);
			if(cu!=null) {
				return true;
			}
		} catch (NullPointerException e) {
			return false;
		}
		return false;		
	}
	
	public boolean isDisconnected(String jwt) {
		try {
			ConnectedUser connectedUser=connectedUserRepository.findByJwt(jwt);
			if(connectedUser.isDisconnected())return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
}
