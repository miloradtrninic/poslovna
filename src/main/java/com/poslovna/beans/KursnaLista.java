package com.poslovna.beans;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity @Getter @Setter @NoArgsConstructor
public class KursnaLista {
	private Long id;
	private Date primenjujeSeOd;
	private Set<KursValuta> valute;
}
