package com.poslovna.beans;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
@DiscriminatorValue(value="false")
public class Banka extends PravnaLica {
	private String obracunskiRacun;
	private Integer sifraBanke;
	private String swift;
	@OneToMany(orphanRemoval=true, fetch=FetchType.LAZY)
	private Set<RacunPravnogLica> racuni;
	private Set<KursnaLista> kursneListe;
}
