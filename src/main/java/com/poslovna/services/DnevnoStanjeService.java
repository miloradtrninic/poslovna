package com.poslovna.services;

import com.poslovna.beans.DnevnoStanje;
import com.poslovna.beans.ObracunskiRacunBanke;
import com.poslovna.dto.RtgsCreation;

public interface DnevnoStanjeService {
	public DnevnoStanje changeDnevnoStanje(RtgsCreation rtgsNalog, ObracunskiRacunBanke racun, boolean uKorist);
}
