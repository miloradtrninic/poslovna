package com.poslovna.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class RacunPravnogLica {
	@Id
	private String brojRacuna;
	private Date datumOtvaranja;
	private Boolean vazeci;
	@ManyToOne(optional=false)
	private PravnaLica vlasnik;
	@ManyToOne(optional=false)
	private Banka banka;
	@ManyToOne(optional=false)
	private Valuta valuta;
	
}
