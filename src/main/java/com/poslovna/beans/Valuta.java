package com.poslovna.beans;

import javax.persistence.Id;

public class Valuta {
	@Id
	private String sifra;
	private String naziv;
	private Boolean domaca;
	
}
