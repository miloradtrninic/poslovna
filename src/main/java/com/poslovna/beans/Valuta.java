package com.poslovna.beans;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class Valuta {
	@Id
	private String sifra;
	private String naziv;
	private Boolean domaca;

}
