package com.vcars.dto;

import java.util.Set;

import com.vcars.entities.UserRoles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AppUserRequestDTO {
	
	private String username;
	private String password;
	private String mail;
	private Set<UserRoles>userRoles;
	
}
