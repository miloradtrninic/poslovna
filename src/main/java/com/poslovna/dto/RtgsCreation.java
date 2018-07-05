package com.poslovna.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class RtgsCreation {
	private Long id;
	private String svrhaPlacanja;
	private Date datumNaloga;
	private Date datumValute;
	private int modelZaduzenja;
	private String pozivNaBrojZaduzenja;
	private int modelOdobrenja;
	private String sifraValute;
	private String pozivNaBrojOdobrenja;
	private double iznos;
	private String obracunskiRacunDuznika;
	private String obracunskiRacunPoverioca;
	private String duznik;
	private String primalac;
	private String swiftDuznika;
	private String swiftPoverioca;
	private String racunDuznika;
	private String racunPoverioca;
}
