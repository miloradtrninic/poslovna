package com.poslovna.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor
public class ClearingCreation {
	private String id;
	private String swiftDuznik;
	private String racunDuznik;
	private String switftPoverilac;
	private String racunPoverilac;
	private Double iznos;
	private String sifraValute;
	private Date datumValute;
	private Date datum;
	private List<PojedinacnoPlacanjeCreation> placanja;
}
