package com.poslovna.services;

import com.poslovna.dto.ClearingCreation;
import com.poslovna.exceptions.NelikvidanException;
import com.poslovna.exceptions.NepostojecaBankaException;
import com.poslovna.exceptions.NepostojeciRacunException;
import com.poslovna.exceptions.NepoznataValutaExceptio;
import com.poslovna.exceptions.PojedinacnoPlacanjeException;

public interface ClearingSerivce {
	void processClearing(ClearingCreation clearing) throws NelikvidanException, PojedinacnoPlacanjeException, NepostojeciRacunException, NepostojecaBankaException, NepoznataValutaExceptio;
}
