package com.poslovna.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
@DiscriminatorValue("MT910")
public class MT910 extends Poruka {
	@ManyToOne()
	private Banka bankaPoverioca;
}
