package com.vcars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcars.dto.CarRequestDTO;
import com.vcars.dto.CarResponseDTOPage;
import com.vcars.dto.SelectedTaxations;
import com.vcars.entities.Car;
import com.vcars.entities.Owner;
import com.vcars.enums.TaxationsNames;
import com.vcars.mappers.CarMapper;
import com.vcars.repositories.CarRepository;
import com.vcars.repositories.OwnerRepository;
import com.vcars.repositories.TaxationRepository;

@Service @Transactional
public class CarService implements CarServiceInterface {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private TaxationRepository taxationRepository;
	@Autowired
	private CarMapper carMapper;
	private int pageSize=10;
	
	@Override
	public Car addCar(CarRequestDTO carrd) {
		Owner owner=ownerRepository.findById(carrd.getOwnerId()).get();
		Car car=new Car();
				car.setMark(carrd.getMark());
				car.setCategory(carrd.getCategory());
				car.setChassisNumber(carrd.getChassisNumber());
				car.setMatriculation(carrd.getMatriculation());
				car.setOwner(owner);
		carRepository.save(car);
		return car;
	}

	@Override
	public boolean deleteCar(Long id) {
		carRepository.deleteById(id);
		return true;
	}

	@Override
	public Car updateCar(CarRequestDTO carrd, Long id) {
		Car c=carRepository.findById(id).get();
		if(carrd.getCategory()!=null)c.setCategory(carrd.getCategory());
		if(carrd.getChassisNumber()!=null)c.setChassisNumber(carrd.getChassisNumber());
		if(carrd.getMark()!=null) c.setMark(carrd.getMark());
		if(carrd.getMatriculation()!=null)c.setMatriculation(carrd.getMatriculation());
		if(!carrd.getCarTaxations().isEmpty()) {
			c.getTaxations().clear();
			carrd.getCarTaxations().forEach(ct->{
				if(ct.isPaid()) {
					c.getTaxations().add(taxationRepository.findByTaxationName(ct.getTaxationName()));
				}
			});
		}
		c.getCarTaxations().clear();
		carRepository.save(c);
		
		return c;
	}

	@Override
	public CarResponseDTOPage getCars(String matricOrChassis, int page, List<SelectedTaxations>selectedTaxations) {
		CarResponseDTOPage crdtopage;
		boolean taxRequest=false;
		
		if(selectedTaxations!=null) taxRequest=true;		
		if(matricOrChassis.startsWith("-")) {			
			crdtopage=carMapper.fromCars(carRepository.findByChassisNumberContains(matricOrChassis.substring(1), PageRequest.of(page, pageSize)));			
		}else {
			if(taxRequest) {
				crdtopage=carMapper.fromCars(carRepository.findByMatriculationContains(matricOrChassis),
						selectedTaxations, page);
			}else crdtopage=carMapper.fromCars(carRepository.findByMatriculationContains(matricOrChassis, PageRequest.of(page, pageSize)));
		}
		crdtopage.getCresdto().forEach(c->{
			c.loadCarTaxations();				
		});
		return crdtopage;		
	}

	@Override
	public Car getCar(Long id) {	
		Car car=carRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("le vehicule portant l'id %s n'est pas enregistré", id)));
		car.loadCarTaxations();
		return car;
	}

	@Override
	public boolean addTaxation(Long carId, TaxationsNames taxationName) {
		Car car=carRepository.findById(carId).get();
		car.getTaxations().add(taxationRepository.findByTaxationName(taxationName));
		carRepository.save(car);
		return true;
	}
}
