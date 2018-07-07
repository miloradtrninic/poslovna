package com.poslovna.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class VrstaPlacanja {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long oznakaVrste;
	@Column(columnDefinition="VARCHAR(80)")
	private String nazivVrstePlacanja;
	
}
