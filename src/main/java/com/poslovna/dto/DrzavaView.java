package com.poslovna.dto;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DrzavaView {
	private String sifraDrzave;
	private String nazivDrzave;
	private Set<NaseljenoMestoView> naseljenaMesta;
	
	private Set<ValutaView> valute;
}
