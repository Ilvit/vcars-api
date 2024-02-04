package com.vcars.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.vcars.entities.Taxation;
import com.vcars.enums.Categories;
import com.vcars.enums.Marks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CarRequestDTO {
	private Marks mark;
	private Categories category;
	private String matriculation;
	private String chassisNumber;
	private Long ownerId;
	private Set<Taxation>taxations;
	private List<CarTaxation> carTaxations=new ArrayList<>();
}
