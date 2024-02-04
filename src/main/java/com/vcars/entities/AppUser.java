package com.vcars.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.vcars.enums.RolesNames;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AppUser {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<AppRole>roles;
	@Column(unique = true)
	private String mail; 
	private transient List<UserRoles>userRoles;
	private transient List<String>rolesNames;
	private transient List<String>addedRolesNames;
	
	public void loadUserRoles() {
//		remplissage des taxes pour l'affichage
		userRoles=new ArrayList<>(); rolesNames=new ArrayList<>(); addedRolesNames=new ArrayList<>();
		for(RolesNames rn:RolesNames.values()) {
			roles.forEach(rol->{
				rolesNames.add(rol.getRoleName());
			});
			//On l'ajoute si elle n'est pas encore été ajoutée dans la liste
			if(!addedRolesNames.contains(rn.toString())) {
				if(rolesNames.contains(rn.toString())) {//paid true or false
					userRoles.add(UserRoles.builder().roleName(rn).hasRole(true).build());
				}else userRoles.add(UserRoles.builder().roleName(rn).hasRole(false).build());
			}			
			if(!addedRolesNames.contains(rn.toString()))addedRolesNames.add(rn.toString());
		}
	}
}
