package com.poslovna.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor
public class PojedinacnoPlacanjeCreation {
	private String id;
	private String duznik;
	private String svrha;
	private String primalac;
	private Date datumNaloga;
	private String racunDuznika;
	private Integer modelZaduzenja;
	private String pozivNaBrojZaduzenja;
	private String racunPoverioca;
	private Integer modelOdobrenja;
	private String pozivNaBrojOdobrenja;
	private Double iznos;
	private String sifraValute;
}
