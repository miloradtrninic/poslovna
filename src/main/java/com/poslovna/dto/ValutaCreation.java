package com.poslovna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ValutaCreation {
	private String sifra;
	private String naziv;
	private boolean domaca;
	private String sifraDrzave;
}
