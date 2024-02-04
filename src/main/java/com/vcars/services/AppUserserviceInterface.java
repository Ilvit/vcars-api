package com.vcars.services;

import java.util.ArrayList;

import com.vcars.dto.AppUserRequestDTO;
import com.vcars.dto.AppUsersDTOList;
import com.vcars.entities.AppUser;

public interface AppUserserviceInterface {
	
	public void addRoleToUser(String username, String roleName);
	public void addRolesToUser(String username, ArrayList<String> rolesNames);
	public void removeRole(String roleName, Long userId);
	public void addUser(AppUserRequestDTO userRequestDTO);
	public void updateUser(Long appUserId, AppUserRequestDTO userRequestDTO);
	public void deleteAppUser(Long appUserId);
	public AppUser getUser(String username);
	public AppUser getUser(Long userId);
	public AppUsersDTOList getAllAppUsers();	
}
