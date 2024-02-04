package com.vcars.services;

import java.util.List;

import com.vcars.security.ConnectedUser;

public interface ConnectedUserServiceInterface {
	public void disconnectUser(String jwt);
	public void connectUser(String jwt);
	public void deleteUselessConnectedUsers();
	public ConnectedUser getConnectedUser(String jwt);
	public List<ConnectedUser>getConnectedUsers();
}
