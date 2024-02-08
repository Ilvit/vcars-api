package com.vcars;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vcars.dto.AppUserRequestDTO;
import com.vcars.entities.AppRole;
import com.vcars.entities.Car;
import com.vcars.entities.Owner;
import com.vcars.entities.Taxation;
import com.vcars.enums.Categories;
import com.vcars.enums.Marks;
import com.vcars.enums.RolesNames;
import com.vcars.enums.TaxationsNames;
import com.vcars.repositories.AppRoleRepository;
import com.vcars.repositories.CarRepository;
import com.vcars.repositories.OwnerRepository;
import com.vcars.repositories.TaxationRepository;
import com.vcars.services.AppUserService;
import com.vcars.services.CarService;

@EnableScheduling
@SpringBootApplication
public class VCarsApplication {
	@Autowired
	CarService carService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(VCarsApplication.class, args);
		System.out.println("c'est bon !");
		
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner clr(CarRepository carRepository, OwnerRepository ownerRepository,
			TaxationRepository taxationRepository, AppRoleRepository appRoleRepository,
			AppUserService appUserService) {
		return args->{
			/*
			appRoleRepository.save(new AppRole(null,RolesNames.USER.toString()));
			appRoleRepository.save(new AppRole(null,RolesNames.ADMIN.toString()));
			appRoleRepository.save(new AppRole(null,RolesNames.CAR_MANAGER.toString()));
			appRoleRepository.save(new AppRole(null,RolesNames.USER_MANAGER.toString()));
			

			appUserService.addUser(new AppUserRequestDTO("user1","12345","user1@gmail.com", null));
			appUserService.addUser(new AppUserRequestDTO("admin1","12345","admin1@gmail.com", null));
			appUserService.addUser(new AppUserRequestDTO("admin2","12345","admin2@gmail.com", null));
			appUserService.addUser(new AppUserRequestDTO("vit@l.kab","vitfoot@2024","ilkab.vit@gmail.com",null));

			//*

			appUserService.addRoleToUser("user1", RolesNames.USER.toString());
			appUserService.addRoleToUser("admin1", RolesNames.USER.toString());
			appUserService.addRoleToUser("admin1", RolesNames.CAR_MANAGER.toString());
			appUserService.addRoleToUser("admin2", RolesNames.USER.toString());
			appUserService.addRoleToUser("admin2", RolesNames.CAR_MANAGER.toString());
			appUserService.addRoleToUser("admin2", RolesNames.ADMIN.toString());
			appUserService.addRoleToUser("vit@l.kab", RolesNames.USER.toString());
			appUserService.addRoleToUser("vit@l.kab", RolesNames.ADMIN.toString());
			appUserService.addRoleToUser("vit@l.kab", RolesNames.CAR_MANAGER.toString());
			appUserService.addRoleToUser("vit@l.kab", RolesNames.USER_MANAGER.toString());
//
//			Enregistrement des taxes dans la base des données
			for(TaxationsNames taxName:TaxationsNames.values()) {
				taxationRepository.save(Taxation.builder().taxationName(taxName).date(new Date()).build());
			}
//			Création des Owners
			Stream.of("BIBOMBA", "KALENGA", "MUJINGA","TSHIKUT","KYOMBELA").forEach(name->{
				Owner owner=Owner.builder()
						.name(name)
						.build();
				ownerRepository.save(owner);
				
				//Création des vehicules
				for(int i=0;i<3;i++) {
					//choix aléatoire de la marque
					double rnd=Math.random()*100;
					while(rnd>(Marks.values().length-1)) {
						rnd=Math.random()*100;
					}
					
//					choix aléatoire de la catégorie
					double rnd2=Math.random()*10;
					while(rnd2>(Categories.values().length-1)) {
						rnd2=Math.random()*10;
					}
//					
					Car c=new Car();
					
					for(Marks mark:Marks.values()) {//attribution de la marque
						if(mark.ordinal()==(int)rnd) {
							c.setMark(mark);
							break;
						}
					}
					for(Categories category :Categories.values()) {//attribution de la catégorie
						if(category.ordinal()==(int)rnd2) {
							c.setCategory(category);
							break;
						}
					}						
					c.setChassisNumber(UUID.randomUUID().toString());
					c.setMatriculation(UUID.randomUUID().toString().substring(0, 8));
					c.setOwner(owner);	
					carRepository.save(c);
				}
			});
			carRepository.findAll().forEach(c->{
//				choix aléatoire de la taxe
				double rn3=Math.random()*10;
				while(rn3>(TaxationsNames.values().length)) {
					rn3=Math.random()*10;
				}
//				paiement aléatoire d'une taxe
				for(TaxationsNames taxationName :TaxationsNames.values()) {//attribution de la catégorie
					if(taxationName.ordinal()==(int)rn3) {
						carService.addTaxation(c.getId(), taxationName);
						break;
					}
				}
			});

			// */
		};
	}
}
