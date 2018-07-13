package com.poslovna.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

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
	private Double ukupanIznos;
	private String sifraValute;
	private Date datumValute;
	private Date datum;
	@JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList<PojedinacnoPlacanjeCreation> placanja;
}
