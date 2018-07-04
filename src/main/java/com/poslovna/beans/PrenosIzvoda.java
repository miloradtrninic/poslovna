package com.poslovna.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class PrenosIzvoda {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date datumNaloga;
	private Integer brojPreseka;
	private Integer brojPromena;
	private Double ukupnoUKorist;
	private Integer status;
	
	@ManyToOne(optional=false)
	private PravnaLica pravnaLica;
	
	@ManyToOne
	private DnevnoStanje naloziUIzvodu;
	
	@ManyToOne(optional=false)
	private RacunPravnogLica brojRacuna;
}
