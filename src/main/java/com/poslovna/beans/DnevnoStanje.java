package com.poslovna.beans;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class DnevnoStanje {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumPromene;
	@ColumnDefault(value="0")
	private Double prethodnoStanje;
	@ColumnDefault(value="0")
	private Double novoStanje;
	@ColumnDefault(value="0")
	private Double promeneUKorist;
	@ColumnDefault(value="0")
	private Double promeneNaTeret;
	@ManyToOne
	private ObracunskiRacunBanke racunPravnogLica;
	
	public Double addKorist(Double korist) {
		if(promeneUKorist == null) {
			promeneUKorist = korist;
		} else {
			promeneUKorist += korist;
		}
		return promeneUKorist;
	}
	public Double addTeret(Double teret) {
		if(promeneNaTeret == null) {
			promeneNaTeret = teret;
		} else {
			promeneNaTeret += teret;
		}
		return promeneNaTeret;
	}
}
