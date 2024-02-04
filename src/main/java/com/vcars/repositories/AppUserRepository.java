package com.vcars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcars.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByUsername(String username);
}
