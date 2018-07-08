package com.poslovna.beans;

import java.util.Date;
import java.util.HashSet;
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
	@javax.persistence.Id
	private String Id;
	private Double ukupanIznos;
	private Date datumValute;
	private Date datum;
	@ManyToOne(optional=false)
	private Banka duznik;
	@ManyToOne(optional=false)
	private Banka poverilac;
	@ManyToOne(optional=false)
	private Valuta valuta;
	@OneToMany(fetch=FetchType.EAGER, orphanRemoval=true)
	private Set<PojedinacnoPlacanje> placanja;
	
	
	public Set<PojedinacnoPlacanje> getPlacanja() {
		if(placanja == null) {
			placanja = new HashSet<>();
		}
		return placanja;
	}
}
