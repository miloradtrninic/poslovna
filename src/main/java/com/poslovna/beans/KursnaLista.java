package com.poslovna.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity @Getter @Setter @NoArgsConstructor
public class KursnaLista {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date primenjujeSeOd;
	@Column(columnDefinition="VARCHAR(80)")
	private String nazivKursneListe;
	@OneToMany(orphanRemoval=true, mappedBy="kursnaLista", fetch=FetchType.EAGER)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Set<KursValuta> valute;
	@ManyToOne
	private Banka banka;
	
	public Set<KursValuta> getValute() {
		if(valute == null)
			return new HashSet<>();
		else 
			return valute;
	}
}
