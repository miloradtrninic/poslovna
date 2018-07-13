package com.poslovna.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class KursnaListaCreation {
	private Date primenjujeSeOd;
	private String PIB;
	private String naziv;
	private List<KursValutaCreation> valute;
}
