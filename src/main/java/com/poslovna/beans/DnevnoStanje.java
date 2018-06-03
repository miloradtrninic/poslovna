package com.poslovna.beans;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class DnevnoStanje {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date datumPromene;
	private Double prethodnoStanje;
	private Double novoStanje;
	private Double promeneUKorist;
	private Double promeneNaTeret;
	@OneToMany(orphanRemoval=true, fetch=FetchType.LAZY)
	private Set<AnalitikaIzvoda> izvodi;
}
