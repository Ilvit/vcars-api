package com.vcars.dto;

import com.vcars.enums.TaxationsNames;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CarTaxation {
	private TaxationsNames taxationName;
	private boolean paid;
}
