package com.poslovna.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;

import com.poslovna.beans.Banka;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor @Getter @Setter
public class KursnaListaView {
	private Long id;
	private Date primenjujeSeOd;
	private String naziv;
	private List<KursValutaView> valute;
}
