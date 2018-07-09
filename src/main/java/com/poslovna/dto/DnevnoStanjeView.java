package com.poslovna.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DnevnoStanjeView {
	private Long id;
	private Date datumPromene;
	private Double prethodnoStanje;
	private Double novoStanje;
	private Double promeneUKorist;
	private Double promeneNaTeret;
}
