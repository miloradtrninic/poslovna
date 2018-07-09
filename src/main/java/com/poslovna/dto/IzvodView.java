package com.poslovna.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class IzvodView {
	private String brojRacuna;
	private List<AnalitikaIzvodaView> analitike;
}
