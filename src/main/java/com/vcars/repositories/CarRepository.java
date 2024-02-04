package com.vcars.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vcars.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

	public Car findByMatriculation(String matriculation);
	public List<Car>findByMatriculationContains(String matric);
	public Page<Car>findByMatriculationContains(String matric, Pageable pageable);
	public Car findByChassisNumber(String chassisNumber);
	public Page<Car> findByChassisNumberContains(String chassNbr, Pageable pageable);
}
