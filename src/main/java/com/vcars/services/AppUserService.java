package com.vcars.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcars.dto.AppUserRequestDTO;
import com.vcars.dto.AppUsersDTOList;
import com.vcars.entities.AppUser;
import com.vcars.repositories.AppRoleRepository;
import com.vcars.repositories.AppUserRepository;

@Service
@Transactional
public class AppUserService implements AppUserserviceInterface {

	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppRoleRepository appRoleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void removeRole(String roleName, Long userId) {
		AppUser appUser=appUserRepository.findById(userId).get();
		appUser.getRoles().remove(appRoleRepository.findByRoleName(roleName));
		appUserRepository.save(appUser);
	}

	@Override
	public void addUser(AppUserRequestDTO userRequestDTO) {
		AppUser appUser=AppUser.builder()
				.username(userRequestDTO.getUsername())
				.password(passwordEncoder.encode(userRequestDTO.getPassword()))
				.mail(userRequestDTO.getMail())
				.build();
		appUserRepository.save(appUser);
	}

	@Override
	public void updateUser(Long appUserId, AppUserRequestDTO userRequestDTO) {
		AppUser appUser=appUserRepository.findById(appUserId).get();
		appUser.setUsername(userRequestDTO.getUsername());
		appUser.setMail(userRequestDTO.getMail());
		if(!userRequestDTO.getPassword().isEmpty() && userRequestDTO.getPassword().length()>3)appUser.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
		appUser.getRoles().clear();
		userRequestDTO.getUserRoles().forEach(ur->{
			if(ur.isHasRole()) {
				appUser.getRoles().add(appRoleRepository.findByRoleName(ur.getRoleName().toString()));
			}			
		});
		appUserRepository.save(appUser);
	}

	@Override
	public AppUser getUser(String username) {
		return appUserRepository.findByUsername(username);
	}

	@Override
	public AppUsersDTOList getAllAppUsers() {
		AppUsersDTOList appUsersDTOList=AppUsersDTOList.builder()
				.appUsers(appUserRepository.findAll())
				.allRoles(appRoleRepository.findAll())
				.build();
		appUsersDTOList.getAppUsers().forEach(au->{
			au.loadUserRoles();
		});
		return appUsersDTOList;
	}

	@Override
	public void addRolesToUser(String username, ArrayList<String> rolesNames) {
		AppUser appUser=appUserRepository.findByUsername(username);
		rolesNames.forEach(rn->{
			appUser.getRoles().add(appRoleRepository.findByRoleName(rn));
		});
		appUserRepository.save(appUser);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser appUser=appUserRepository.findByUsername(username);
		appUser.getRoles().add(appRoleRepository.findByRoleName(roleName));
		appUserRepository.save(appUser);
	}

	@Override
	public AppUser getUser(Long userId) {
		AppUser au=appUserRepository.findById(userId).get();
		au.loadUserRoles();
		return au;
	}

	@Override
	public void deleteAppUser(Long appUserId) {
		appUserRepository.deleteById(appUserId);
	}

}
