package com.vcars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcars.entities.Taxation;
import com.vcars.enums.TaxationsNames;

public interface TaxationRepository extends JpaRepository<Taxation, Long> {
	public Taxation findByTaxationName(TaxationsNames taxation );

}
