package com.poslovna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ValutaCreation {
	private String sifra;
	private String naziv;
	private Boolean domaca;
	private String sifraDrzave;
}
