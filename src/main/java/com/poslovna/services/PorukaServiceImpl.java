package com.poslovna.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovna.beans.Banka;
import com.poslovna.beans.MT900;
import com.poslovna.beans.MT910;
import com.poslovna.beans.Valuta;
import com.poslovna.dto.RtgsCreation;
import com.poslovna.repository.MT900Repo;
import com.poslovna.repository.MT910Repo;
import com.poslovna.repository.PorukaRepo;
import com.poslovna.repository.ValutaRepo;

@Service
@Transactional
public class PorukaServiceImpl implements PorukaService{
	@Autowired
	private MT900Repo mt900Repo;
	
	@Autowired
	private MT910Repo mt910Repo;

	@Override
	public boolean createMT900(Date datumValute, Double iznos, String nalogId, Valuta valuta, Banka bankaDuznik) {
		MT900 mt900 = new MT900();
		mt900.setBankaDuznik(bankaDuznik);
		mt900.setDatumValute(datumValute);
		mt900.setPorukaNalogaId(nalogId);
		mt900.setIznos(iznos);
		mt900.setValuta(valuta);
		mt900Repo.save(mt900);
		return true;
	}

	@Override
	public boolean createMT910(Date datumValute, Double iznos, String nalogId, Valuta valuta, Banka bankaPoverioca) {
		MT910 mt910 = new MT910();
		mt910.setBankaPoverioca(bankaPoverioca);
		mt910.setDatumValute(datumValute);
		mt910.setIznos(iznos);
		mt910.setValuta(valuta);
		mt910Repo.save(mt910);
		return true;
	}

}
