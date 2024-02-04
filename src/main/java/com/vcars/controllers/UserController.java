package com.vcars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vcars.dto.AppUserRequestDTO;
import com.vcars.dto.AppUsersDTOList;
import com.vcars.entities.AppUser;
import com.vcars.security.ConnectedUser;
import com.vcars.services.AppUserService;
import com.vcars.services.ConnectedUserService;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

	@Autowired
	private AppUserService appUserService;
	@Autowired
	private ConnectedUserService connectedUserService;
	
	@GetMapping("/users")
	@PreAuthorize("hasAuthority('SCOPE_USER_MANAGER')")
	public AppUsersDTOList getAllAppUsers(){
		return appUserService.getAllAppUsers();
	}
	@GetMapping("/users/connected-users")
	@PreAuthorize("hasAuthority('SCOPE_USER_MANAGER')")
	public List<ConnectedUser>getAllConnectedUsers(){
		return connectedUserService.getConnectedUsers();
	}
	
	@PreAuthorize("hasAuthority('SCOPE_USER_MANAGER')")
	@PostMapping("users/connected-users")
	public boolean disconnectAppUser(@RequestBody String jwt) {
		connectedUserService.disconnectUser(jwt);
		return true;
	}
	
	@PreAuthorize("hasAuthority('SCOPE_USER_MANAGER')")
	@GetMapping("/users/{id}")
	public AppUser getAppUser(@PathVariable Long id) {
		return appUserService.getUser(id);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_USER_MANAGER')")
	@DeleteMapping("/users/{id}")
	public boolean deleteAppUser(@PathVariable Long id) {
		appUserService.deleteAppUser(id);
		return true;
	}
	@PutMapping("/users/{id}")
	@PreAuthorize("hasAuthority('SCOPE_USER_MANAGER')")
	public boolean updateAppUser(@PathVariable Long id, @RequestBody AppUserRequestDTO appUserDTO) {
		System.out.println("id: "+id);
		appUserService.updateUser(id, appUserDTO);
	return true;
	}
	@PostMapping("/users")
	@PreAuthorize("hasAuthority('SCOPE_USER_MANAGER')")
	public boolean addNewAppUser(@RequestBody AppUserRequestDTO appUserRequestDTO) {
		appUserService.addUser(appUserRequestDTO);
		return true;
	}
	public AppUser getAppUserByUsername(String username) {
		return appUserService.getUser(username);
	}
}
