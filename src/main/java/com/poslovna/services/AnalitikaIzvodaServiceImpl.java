package com.poslovna.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovna.beans.AnalitikaIzvoda;
import com.poslovna.beans.DnevnoStanje;
import com.poslovna.beans.ObracunskiRacunBanke;
import com.poslovna.beans.Valuta;
import com.poslovna.dto.RtgsCreation;
import com.poslovna.repository.AnalitikaIzvodaRepo;
import com.poslovna.repository.ValutaRepo;

@Service
@Transactional
public class AnalitikaIzvodaServiceImpl implements AnalitikaIzvodaService{
	@Autowired
	private AnalitikaIzvodaRepo analitikaIzvodaRepo;
	
	@Autowired
	private ValutaRepo valutaRepo;

	@Override
	public AnalitikaIzvoda createAnalitikaIzvoda(RtgsCreation rtgsNalog, ObracunskiRacunBanke racunDuznika, ObracunskiRacunBanke racunPoverioca, DnevnoStanje dnevnoStanje, boolean hitno) {
		AnalitikaIzvoda analitika = new AnalitikaIzvoda();
		analitika.setDatumValute(rtgsNalog.getDatumValute());
		analitika.setDnevnoStanje(dnevnoStanje);
		analitika.setDuznik(racunDuznika);
		analitika.setIznos(rtgsNalog.getIznos());
		analitika.setPoverilac(racunPoverioca);
		Optional<Valuta> valuta = valutaRepo.findById(rtgsNalog.getSifraValute());
		analitika.setValuta(valuta.get());
		
		return analitikaIzvodaRepo.save(analitika);
	}
	
}
