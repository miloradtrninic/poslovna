package com.poslovna.beans;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class RacunPravnogLica {
	@Id
	private String brojRacuna;
	private Date datumOtvaranja;
	private Boolean vazeci;
	private Double stanje;
	@ManyToOne(optional=false)
	private PravnaLica vlasnik;
	@ManyToOne(optional=false)
	private Banka banka;
	@ManyToOne(optional=false)
	private Valuta valuta;
	@OneToMany(mappedBy="racunPravnogLica")
	private Set<DnevnoStanje> dnevnaStanja;
	
}
