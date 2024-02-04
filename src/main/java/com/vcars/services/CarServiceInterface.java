package com.vcars.services;

import java.util.List;

import com.vcars.dto.CarRequestDTO;
import com.vcars.dto.CarResponseDTOPage;
import com.vcars.dto.SelectedTaxations;
import com.vcars.entities.Car;
import com.vcars.enums.TaxationsNames;

public interface CarServiceInterface {

	public CarResponseDTOPage getCars(String matricOrChassis, int page, List<SelectedTaxations>selectedTaxations);
	public Car getCar(Long id);
	public Car addCar(CarRequestDTO carrd);
	public boolean deleteCar(Long id);
	public Car updateCar(CarRequestDTO carrd, Long id);
	public boolean addTaxation(Long carId, TaxationsNames taxationName);
		
}
