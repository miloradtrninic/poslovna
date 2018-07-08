package com.poslovna.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class Poruka {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String Id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumValute;
	private String porukaNalogaId;
	private Double iznos;
	@ManyToOne(optional=false)
	private Valuta valuta;
}
