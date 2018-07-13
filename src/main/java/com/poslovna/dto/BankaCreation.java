package com.poslovna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class BankaCreation {
	private String PIB;
	private String naziv;
	private String adresa;
	private String email;
	private String web;
	private String telefon;
	private String fax;
	private Integer sifraBanke;
	private String swift;
}
