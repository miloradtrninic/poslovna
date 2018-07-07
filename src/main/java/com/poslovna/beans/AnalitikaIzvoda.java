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
	private String svrhaPlacanja;
	private Date datumNaloga;
	@ManyToOne(optional=false)
	private DnevnoStanje dnevnoStanjeDuznik;
	@ManyToOne(optional=false)
	private DnevnoStanje dnevnoStanjePoverilac;
	@ManyToOne(optional=false)
	private Valuta valuta;
}
