package com.vcars.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.vcars.dto.CarTaxation;
import com.vcars.enums.Categories;
import com.vcars.enums.Marks;
import com.vcars.enums.TaxationsNames;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Car {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Marks mark;
	@Enumerated(EnumType.STRING)
	private Categories category;
	private String matriculation;	
	private String chassisNumber;
	@ManyToOne
	private Owner owner;
	@ManyToMany
	private Set<Taxation>taxations;	
	private transient List<TaxationsNames>carTaxNList=new ArrayList<>();
	private transient List<TaxationsNames>addedTaxList=new ArrayList<>();
	private transient List<CarTaxation>carTaxations=new ArrayList<>();	
	
	public void loadCarTaxations() {	
//		remplissage des taxes pour l'affichage
		for(TaxationsNames taxationName:TaxationsNames.values()) {
			taxations.forEach(tax->{
				carTaxNList.add(tax.getTaxationName());
			});
			//On l'ajoute si elle n'est pas encore été ajoutée dans la liste
			if(!addedTaxList.contains(taxationName)) {
				if(carTaxNList.contains(taxationName)) {//paid true or false
					carTaxations.add(CarTaxation.builder().taxationName(taxationName).paid(true).build());
				}else carTaxations.add(CarTaxation.builder().taxationName(taxationName).paid(false).build());
			}			
			if(!addedTaxList.contains(taxationName))addedTaxList.add(taxationName);
		}
	}	
}
