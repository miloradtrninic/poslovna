package com.poslovna.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class NaseljenoMesto {
	@Id
	private Long sifraMesta;
	private String naziv;
	private String pttOznaka;
	@ManyToOne
	private Drzava drzava;
}
