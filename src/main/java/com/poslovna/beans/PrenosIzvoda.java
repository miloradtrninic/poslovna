package com.poslovna.beans;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class PrenosIzvoda {
	private Date datumNaloga;
	private Integer brojPreseka;
	private Integer brojPromena;
	private Double ukupnoUKorist;
	private Integer status;
}
