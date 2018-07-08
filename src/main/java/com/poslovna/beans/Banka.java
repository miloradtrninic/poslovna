package com.poslovna.beans;

import java.util.Set;

import javax.persistence.Column;
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
	@Column(length=50, nullable=false)
	private String naziv;
	@Column(length=50, nullable=false)
	private String adresa;
	@Column(length=50, nullable=false)
	private String email;
	@Column(length=30, nullable=false)
	private String web;
	@Column(length=20, nullable=false)
	private String telefon;
	@Column(length=20, nullable=false)
	private String fax;
	private Integer sifraBanke;
	@Column(columnDefinition="CHAR(8)")
	private String swift;
	@OneToOne(orphanRemoval=true, fetch=FetchType.EAGER)
	private ObracunskiRacunBanke racun;
	@OneToMany(orphanRemoval=true, fetch=FetchType.LAZY, mappedBy="banka")
	private Set<KursnaLista> kursneListe;
}
