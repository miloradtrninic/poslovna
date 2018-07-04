package com.poslovna.beans;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity @Getter @Setter @NoArgsConstructor
public class KursnaLista {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date primenjujeSeOd;
	@OneToMany(orphanRemoval=true, mappedBy="kursnaLista")
	private Set<KursValuta> valute;
}
