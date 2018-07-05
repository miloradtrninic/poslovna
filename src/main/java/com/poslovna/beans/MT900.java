package com.poslovna.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
@DiscriminatorValue("MT900")
public class MT900 extends Poruka {
	@ManyToOne(optional=false)
	private Banka bankaDuznik;
}
