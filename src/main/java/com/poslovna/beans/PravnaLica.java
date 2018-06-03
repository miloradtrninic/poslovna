package com.poslovna.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class PravnaLica {
	@Id
	private String PIB;
	private String naziv;
	private String adresa;
	private String email;
	private String web;
	private String telefon;
	private String fax;
	private Boolean firma;
}
