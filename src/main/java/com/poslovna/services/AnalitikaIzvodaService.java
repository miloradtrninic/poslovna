package com.poslovna.services;

import com.poslovna.beans.AnalitikaIzvoda;
import com.poslovna.beans.DnevnoStanje;
import com.poslovna.beans.ObracunskiRacunBanke;
import com.poslovna.dto.RtgsCreation;

public interface AnalitikaIzvodaService {
	AnalitikaIzvoda createAnalitikaIzvoda(RtgsCreation rtgsNalog, ObracunskiRacunBanke racunDuznika, ObracunskiRacunBanke racunPoverioca, DnevnoStanje dnevnoStanje, boolean hitno);
}
