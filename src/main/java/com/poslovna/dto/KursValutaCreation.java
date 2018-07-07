package com.poslovna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class KursValutaCreation {
	private Double kupovni;
	private Double srednji;
	private Double prodajni;
	private String osnovnaValuta;
	private String premaValuti;
}
