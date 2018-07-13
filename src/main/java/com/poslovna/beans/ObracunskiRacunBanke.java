package com.poslovna.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class ObracunskiRacunBanke {
	@Id
	@Column(columnDefinition="CHAR(18)")
	private String brojRacuna;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumOtvaranja;
	private Boolean vazeci;
	private Double dozvoljeniMinus;
	@ManyToOne(optional=false)
	private Valuta valuta;
	@OneToMany(mappedBy="racunPravnogLica")
	@JsonIgnore
	private List<DnevnoStanje> dnevnaStanja;
}
