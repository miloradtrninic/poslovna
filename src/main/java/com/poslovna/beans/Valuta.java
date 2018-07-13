package com.poslovna.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class Valuta {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition="CHAR(3)")
	private String sifra;
	@Column(columnDefinition="VARCHAR(20)")
	private String naziv;
	private Boolean domaca;
	@JsonIgnore
	@ManyToOne(optional=false)
	private Drzava drzava;
	

}
