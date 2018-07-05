package com.poslovna.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovna.beans.Banka;
import com.poslovna.beans.MT900;
import com.poslovna.beans.MT910;
import com.poslovna.beans.Valuta;
import com.poslovna.dto.RtgsCreation;
import com.poslovna.repository.PorukaRepo;
import com.poslovna.repository.ValutaRepo;

@Service
@Transactional
public class PorukaServiceImpl implements PorukaService{
	@Autowired
	private PorukaRepo porukaRepo;
	
	@Autowired
	private ValutaRepo valutaRepo;

	@Override
	public boolean createMT900(RtgsCreation rtgsNalog, Banka bankaDuznik) {
		MT900 mt900 = new MT900();
		mt900.setBankaDuznik(bankaDuznik);
		mt900.setDatumValute(rtgsNalog.getDatumValute());
		mt900.setIznos(rtgsNalog.getIznos());
		
		Optional<Valuta> valuta = valutaRepo.findById(rtgsNalog.getSifraValute());
		//mt900.setPorukaNalogaId(rtgsNalog.getId());
		mt900.setValuta(valuta.get());
		porukaRepo.save(mt900);
		return true;
	}

	@Override
	public boolean createMT910(RtgsCreation rtgsNalog, Banka bankaPoverioca) {
		MT910 mt910 = new MT910();
		mt910.setBankaPoverioca(bankaPoverioca);
		mt910.setDatumValute(rtgsNalog.getDatumValute());
		mt910.setIznos(rtgsNalog.getIznos());
		
		Optional<Valuta> valuta = valutaRepo.findById(rtgsNalog.getSifraValute());
		mt910.setValuta(valuta.get());
		
		porukaRepo.save(mt910);
		return true;
	}

}
