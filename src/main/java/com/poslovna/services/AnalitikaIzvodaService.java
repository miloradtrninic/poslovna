package com.poslovna.services;

import java.util.Date;

import com.poslovna.beans.AnalitikaIzvoda;
import com.poslovna.beans.DnevnoStanje;
import com.poslovna.exceptions.NepoznataValutaExceptio;

public interface AnalitikaIzvodaService {
	
	/**
	 * @param sifraValute
	 * @param datumValute
	 * @param iznos
	 * @param dnevnoStanjeDuznik
	 * @param dnevnoStanjePoverilac
	 * @param svrha
	 * @return
	 * @throws NepoznataValutaExceptio
	 */
	AnalitikaIzvoda createAnalitikaIzvoda(Date datumNalog, String sifraValute, Date datumValute,
										  double iznos, DnevnoStanje dnevnoStanjeDuznik, 
										  DnevnoStanje dnevnoStanjePoverilac, String svrha,
										  String racunDuznika, String racunPoverioca) throws NepoznataValutaExceptio;
}
