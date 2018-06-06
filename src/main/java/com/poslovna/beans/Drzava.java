package com.poslovna.beans;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class Drzava {
	@Id
	private String sifraDrzave;
	private String nazivDrzave;
	@OneToMany(mappedBy="drzava")
	private Set<NaseljenoMesto> naseljenaMesta;
	
	@OneToMany(orphanRemoval=true)
	private Set<Valuta> valute;
}
