package com.vcars.services;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcars.repositories.ConnectedUserRepository;
import com.vcars.security.ConnectedUser;

@Service
@Transactional

public class ConnectedUserService implements ConnectedUserServiceInterface {

	ConnectedUserRepository connectedUserRepository;

	public ConnectedUserService(ConnectedUserRepository connectedUserRepository) {
		this.connectedUserRepository = connectedUserRepository;
	}

	@Override
	public void connectUser(String jwt) {
		ConnectedUser connectedUser;
		try {
			connectedUser = connectedUserRepository.findByJwt(jwt);
			connectedUser.setDisconnected(true);
			connectedUserRepository.save(connectedUser);
		} catch (Exception e) {
			System.out.println("Connected user not found!");
		}		
	}

	@Override
	public void disconnectUser(String jwt) {
		try {
			ConnectedUser connectedUser=connectedUserRepository.findByJwt(jwt);
			connectedUser.setDisconnected(true);
			connectedUserRepository.save(connectedUser);
		} catch (Exception e) {
			System.out.println("connected user not found!");
		}
	}

	@Override
	public List<ConnectedUser> getConnectedUsers() {
		return connectedUserRepository.findAllAndOnLine(true);
	}

	@Override
	@Scheduled(cron = "59 * * * * *")
	public void deleteUselessConnectedUsers() {
		connectedUserRepository.deleteByDisconnected(true);
	}
	@Override
	public ConnectedUser getConnectedUser(String jwt) {
		return connectedUserRepository.findByJwt(jwt);
	}
	
}
