package com.vcars.dto;

import java.util.List;

import com.vcars.entities.Car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CarResponseDTOPage {

	private List<Car>cresdto;
	private List<SelectedTaxations>selectedTaxations;
	private int totalCarsOnPage;
	private Long totalCars;
	private int totalPages;
	private int currentPage;
	private int[] carsPagesArray;
	private List<SelectedTaxations> taxationsList;
	
}
