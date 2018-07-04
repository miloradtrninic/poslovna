package com.poslovna.services;

import com.poslovna.beans.Banka;
import com.poslovna.dto.RtgsCreation;

public interface PorukaService {
	public boolean createMT900(RtgsCreation rtgsNalog, Banka bankaDuznik);
	public boolean createMT910(RtgsCreation rtgsNalog, Banka bankaDuznikbankaPoverioca);
}
