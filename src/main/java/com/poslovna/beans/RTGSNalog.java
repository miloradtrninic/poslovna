package com.poslovna.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class RTGSNalog {
	@javax.persistence.Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	@OneToOne(optional=false)
	@Cascade(value=CascadeType.ALL)
	private AnalitikaIzvoda analitikaIzvoda;
}
