package com.vcars.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.vcars.dto.CarResponseDTOPage;
import com.vcars.dto.SelectedTaxations;
import com.vcars.entities.Car;
import com.vcars.entities.Taxation;
import com.vcars.enums.TaxationsNames;

@Component
public class CarMapper {

	private ArrayList<Car>alcrd;
	private int totalCarsOnPage;
	private Long totalCars;
	private int totalPages;
	private int currentPage=1;
	private int[] carsPagesArray;
	private int pageSize=10;
	public CarResponseDTOPage fromCars(Page<Car> carsPage){
		alcrd=new ArrayList<>();		
		carsPage.forEach(car->{
			alcrd.add(car);
		});
		this.totalPages=carsPage.getTotalPages();
		this.currentPage=carsPage.getNumber()+1;
		this.totalCars=carsPage.getTotalElements();
		this.totalCarsOnPage=carsPage.getNumberOfElements();		
		this.carsPagesArray=new int[totalPages];
		
		for(int i=0;i<totalPages;i++) {//remplissage des numéros des pages dans le tableau de pages
			carsPagesArray[i]=1+i;
		}
		List<SelectedTaxations>selecTa=new ArrayList<>();
		for(TaxationsNames tan:TaxationsNames.values()) {
			selecTa.add(new SelectedTaxations(tan, false));
		}
		CarResponseDTOPage carResponseDTOPage=CarResponseDTOPage.builder()
				.cresdto(alcrd)
				.totalCarsOnPage(totalCarsOnPage)
				.totalCars(totalCars)
				.totalPages(totalPages)
				.currentPage(currentPage)
				.carsPagesArray(carsPagesArray)
				.taxationsList(selecTa)
				.build();
		
		return carResponseDTOPage;
	}
	
	public CarResponseDTOPage fromCars(List<Car>carsList, List<SelectedTaxations>selectedTaxationsList, int page) {
		List<Car>selList = new ArrayList<>();
		List<TaxationsNames>selTaxNames=new ArrayList<>();
		int selectedTaxSize=0;
		
		for(SelectedTaxations st:selectedTaxationsList){	
			if(st.isSelected()) {
				selectedTaxSize++;
				selTaxNames.add(st.getTaxationName());
			}			
		}		
		for(int i=0;i<carsList.size();i++) {
			Car car=carsList.get(i);
			List<TaxationsNames>taxNames=new ArrayList<>();
			int counter=0;
			
			for(Taxation t:car.getTaxations()){
				taxNames.add(t.getTaxationName());
			}
			for(int j=0;j<selectedTaxSize;j++) {
				if(taxNames.contains(selTaxNames.get(j))) {
					counter++;
					if(counter==selectedTaxSize) {
						selList.add(car);
						break;
					}
				}
			}						
		}
		this.totalCars=(long) selList.size();
		this.currentPage=page+1;
		this.totalPages=(int) (totalCars/pageSize);
		this.totalCarsOnPage=(int) (totalCars%pageSize);
		if(totalCars%pageSize>0) {
			System.out.println("modulo: "+totalCars%pageSize);
			this.totalPages+=1;
			if(totalPages==page)this.totalCarsOnPage=(int) (totalCars%pageSize);
		}
		selList=selList.subList((currentPage-1)*pageSize, totalCarsOnPage);
		System.out.println(selList.size()+": selList ");
		
		this.carsPagesArray=new int[totalPages];		
		for(int i=0;i<totalPages;i++) {//remplissage des numéros des pages dans le tableau de pages
			carsPagesArray[i]=1+i;
		}
		CarResponseDTOPage crdto=new CarResponseDTOPage();
		crdto.setTotalCars((long) selList.size());
		crdto.setTotalCarsOnPage(totalCarsOnPage);
		crdto.setCresdto(selList);
		crdto.setTotalPages(totalPages);
		crdto.setTaxationsList(selectedTaxationsList);
		crdto.setCarsPagesArray(carsPagesArray);
		crdto.setCurrentPage(currentPage);
		
		return crdto;
	}
	
}
