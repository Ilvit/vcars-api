package com.vcars.dto;

import java.util.List;

import com.vcars.entities.AppRole;
import com.vcars.entities.AppUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AppUsersDTOList {
	
	private List<AppUser>appUsers;
	private List<AppRole>allRoles;
	
}
