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
public class AnalitikaIzvoda {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String svrhaPlacanja;
	private Date primljen;
	private Date datumValute;
	private Integer modelZaduzenja;
	private String pozivNaBroj;
	private Integer modelOdobrenja;
	private String pozivNaBrojOdobrenja;
	private Boolean hitno;
	private Double iznos;
	private Integer status;
	private Integer tipGreske;
	
	@ManyToOne(optional=false)
	private RacunPravnogLica duznik;
	@ManyToOne(optional=false)
	private RacunPravnogLica poverilac;
	@ManyToOne(optional=false)
	private NaseljenoMesto naseljenoMesto;
	@ManyToOne(optional=false)
	private VrstaPlacanja vrstaPlacanja;
}
