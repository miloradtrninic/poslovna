package com.poslovna.services;

import java.util.Date;

import com.poslovna.beans.Banka;
import com.poslovna.beans.Valuta;

public interface PorukaService {
	public boolean createMT900(Date datumValute, Double iznos, String nalogId, Valuta valuta, Banka bankaDuznik);
	public boolean createMT910(Date datumValute, Double iznos, String nalogId, Valuta valuta, Banka bankaPoverioca);
}
