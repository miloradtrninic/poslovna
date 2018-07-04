package com.poslovna.services;

import com.poslovna.beans.RacunPravnogLica;
import com.poslovna.dto.RtgsCreation;

public interface DnevnoStanjeService {
	public boolean changeDnevnoStanje(RtgsCreation rtgsNalog, RacunPravnogLica racun, boolean uKorist);
}
