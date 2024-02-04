package com.vcars.entities;

import com.vcars.enums.RolesNames;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserRoles {
	private RolesNames roleName;
	private boolean hasRole;
}
