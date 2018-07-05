package com.poslovna.beans;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class Banka{
	@Id
	private String PIB;
	private String naziv;
	private String adresa;
	private String email;
	private String web;
	private String telefon;
	private String fax;
	private Boolean firma;
	private Integer sifraBanke;
	private String swift;
	@OneToOne(orphanRemoval=true, fetch=FetchType.EAGER)
	private ObracunskiRacunBanke racun;
	@OneToMany(orphanRemoval=true, fetch=FetchType.LAZY)
	private Set<KursnaLista> kursneListe;
}
