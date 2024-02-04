package com.vcars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcars.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

	public AppRole findByRoleName(String roleName);
}
