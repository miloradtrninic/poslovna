package com.poslovna.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class NaseljenoMesto {
	@Id
	@Column(columnDefinition="CHAR(2)")
	private Long sifraMesta;
	@Column(columnDefinition="VARCHAR(20)")
	private String naziv;
	@Column(columnDefinition="CHAR(5)")
	private String pttOznaka;
	@ManyToOne
	private Drzava drzava;
}
