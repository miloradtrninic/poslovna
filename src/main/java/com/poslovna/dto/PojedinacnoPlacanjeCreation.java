package com.poslovna.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor
public class PojedinacnoPlacanjeCreation {
	private String idPlacanje;
	private String duznik;
	private String primalac;
	private String svrha;
	private Date datumNaloga;
	private Integer modelZaduzenja;
	private String pozivNaBrojZaduzenja;
	private Integer modelOdobrenja;
	private String sifraValute;
	private String pozivNaBrojOdobrenja;
	private Double iznos;
	private String racunPoverioca;
	private String racunDuznika;
}
