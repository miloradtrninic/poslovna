package com.poslovna.services;

import com.poslovna.beans.AnalitikaIzvoda;
import com.poslovna.beans.DnevnoStanje;
import com.poslovna.beans.RacunPravnogLica;
import com.poslovna.dto.RtgsCreation;

public interface AnalitikaIzvodaService {
	AnalitikaIzvoda createAnalitikaIzvoda(RtgsCreation rtgsNalog, RacunPravnogLica racunDuznika, RacunPravnogLica racunPoverioca, DnevnoStanje dnevnoStanje, boolean hitno);
}
