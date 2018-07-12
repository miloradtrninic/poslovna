package com.poslovna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class NaseljenoMestoView {
	private Long id;
	private String sifraMesta;
	private String naziv;
	private String pttOznaka;
	private String drzavaSifraDrzava;
}
