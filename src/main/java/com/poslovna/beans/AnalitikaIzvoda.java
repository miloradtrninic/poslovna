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
	private Date datumValute;
	private Double iznos;
	private String greska;
	@ManyToOne(optional=false)
	private ObracunskiRacunBanke duznik;
	@ManyToOne(optional=false)
	private ObracunskiRacunBanke poverilac;
	@ManyToOne(optional=false)
	private VrstaPlacanja vrstaPlacanja;
	@ManyToOne()
	private DnevnoStanje dnevnoStanje;
	@ManyToOne()
	private Valuta valuta;
	private String svrhaPlacanja;
	private Date datumNaloga;
	private int modelZaduzenja;
	private String pozivNaBrojZaduzenja;
	private String pozivNaBrojOdobrenja;
	private int modelOdobrenja;
	private String racunDuznika;
	private String racunPoverioca;
}
