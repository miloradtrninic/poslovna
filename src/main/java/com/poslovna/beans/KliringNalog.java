package com.poslovna.beans;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class KliringNalog {
	@javax.persistence.Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private Double ukupanIznos;
	@ManyToOne(optional=false)
	private Valuta valuta;
	private Date datumValute;
	private Date datum;
	@ManyToOne(optional=false)
	private Banka duznik;
	@ManyToOne(optional=false)
	private Banka poverilac;
	
	@OneToMany(fetch=FetchType.EAGER, orphanRemoval=true)
	private Set<PojedinacnoPlacanje> placanja;
}
