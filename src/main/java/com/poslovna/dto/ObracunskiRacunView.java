package com.poslovna.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poslovna.beans.DnevnoStanje;
import com.poslovna.beans.Valuta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ObracunskiRacunView {
	private String brojRacuna;
	private Date datumOtvaranja;
	private Boolean vazeci;
	private Double dozvoljeniMinus;
	private String valutaSifra;
}
