package com.vcars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vcars.dto.CarRequestDTO;
import com.vcars.dto.CarResponseDTOPage;
import com.vcars.dto.SelectedTaxations;
import com.vcars.entities.Car;
import com.vcars.services.CarService;

@RestController
@CrossOrigin("http://localhost:4200")
public class CarController {

	@Autowired
	private CarService carService;
	
	@PreAuthorize("hasAuthority('SCOPE_USER')")
	@GetMapping("/user/cars")
	public CarResponseDTOPage getCars(@RequestParam(defaultValue = "", name = "kw") String matricOrChassisNbr, 
			@RequestParam(defaultValue = "0",name = "p") int page){
		
		System.out.println("Recherche des vehicules ");
		return carService.getCars(matricOrChassisNbr, page,null);
	}
	@PostMapping("/user/cars/taxations")
	@PreAuthorize("hasAuthority('SCOPE_USER')")
	public CarResponseDTOPage getCars(@RequestParam(defaultValue = "", name = "kw") String matricOrChassisNbr, 
			@RequestParam(defaultValue = "0",name = "p") int page, @RequestBody List<SelectedTaxations>selectedTaxList){
		
		System.out.println("Recherche des vehicules : "+selectedTaxList.isEmpty());
		return carService.getCars(matricOrChassisNbr, page, selectedTaxList);
	}
	@GetMapping("/user/cars/{id}")
	@PreAuthorize("hasAuthority('SCOPE_USER')")
	public Car getCar(@PathVariable Long id) {
		System.out.println("Appel par id ");
		Car carResponseDTO=carService.getCar(id);
		return carResponseDTO;
	}
	@DeleteMapping("/admin/cars/{id}")
	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public boolean deleteCar(@PathVariable Long id) {
		return carService.deleteCar(id);
	}
	@PostMapping("/admin/cars")
	@PreAuthorize("hasAuthority('SCOPE_CAR_MANAGER')")
	public Car addNewCar(@RequestBody CarRequestDTO carRequestDTO) {
		return carService.addCar(carRequestDTO);
	}
	@PutMapping("/admin/cars/{id}")
	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public Car updateCar(@RequestBody CarRequestDTO carRequestDTO, @PathVariable Long id) {
		return carService.updateCar(carRequestDTO,id);
	}

}
