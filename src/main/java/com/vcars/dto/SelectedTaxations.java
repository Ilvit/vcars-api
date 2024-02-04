package com.vcars.dto;

import com.vcars.enums.TaxationsNames;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SelectedTaxations {
	private TaxationsNames taxationName;
	private boolean selected=true;
}
