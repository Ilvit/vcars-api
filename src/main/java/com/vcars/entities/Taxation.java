package com.vcars.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vcars.enums.TaxationsNames;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Taxation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private TaxationsNames taxationName;
	private Date date;
	@ManyToMany(mappedBy = "taxations" )
	@JsonIgnore
	private List<Car>cars;
	
	public Taxation(TaxationsNames taxationName, Date date){
		this.taxationName=taxationName;
		this.date=date;
	}
}
