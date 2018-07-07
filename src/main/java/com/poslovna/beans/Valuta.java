package com.poslovna.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class Valuta {
	@Id
	@Column(columnDefinition="CHAR(3)")
	private String sifra;
	@Column(columnDefinition="VARCHAR(20)")
	private String naziv;
	private Boolean domaca;

}
