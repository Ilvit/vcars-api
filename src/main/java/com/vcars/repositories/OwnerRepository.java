package com.vcars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcars.entities.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
