package com.poslovna.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class KursValuta {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double kupovni;
	private Double srednji;
	private Double prodajni;
	@ManyToOne(optional=false)
	private Valuta osnovnaValuta;
	@ManyToOne(optional=false)
	private Valuta premaValuti;
	@ManyToOne(optional=false)
	private KursnaLista kursnaLista;
}
