package com.poslovna.dto;

import java.util.Date;

import javax.persistence.ManyToOne;
import com.poslovna.beans.Valuta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor
public class AnalitikaIzvodaView {
	private Long id;
	private Date datumValute;
	private Double iznos;
	private String greska;
	private String svrhaPlacanja;
	private Date datumNaloga;
	private DnevnoStanjeView dnevnoStanjeDuznik;
	private DnevnoStanjeView dnevnoStanjePoverilac;
	private String valutaSifra;
	private String racunDuznika;
	private String racunPoverioca;
}
