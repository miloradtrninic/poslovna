package com.poslovna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class KursValutaView {
	private Long id;
	private Double kupovni;
	private Double srednji;
	private Double prodajni;
	private String osnovnaValutaSifra;
	private String premaValutiSifra;
}
