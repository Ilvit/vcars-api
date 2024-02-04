package com.vcars.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vcars.security.ConnectedUser;

public interface ConnectedUserRepository extends JpaRepository<ConnectedUser, Long> {
	
	public ConnectedUser findByJwt(String jwt);
	public ConnectedUser findByUsername(String username);
	@Query("select cu from ConnectedUser cu where cu.onLine=true")
	public List<ConnectedUser>findAllAndOnLine(boolean onLine);
	public void deleteByJwt(String jwt);
	public void deleteByUsername(String username);
	public void deleteByDisconnected(boolean disconnected);
	
}
