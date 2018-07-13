package com.poslovna.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovna.beans.AnalitikaIzvoda;
import com.poslovna.beans.DnevnoStanje;
import com.poslovna.beans.Valuta;
import com.poslovna.exceptions.NepoznataValutaExceptio;
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
	public AnalitikaIzvoda createAnalitikaIzvoda(String sifraValute, Date datumValute,
			  double iznos, DnevnoStanje dnevnoStanjeDuznik, 
			  DnevnoStanje dnevnoStanjePoverilac, String svrha,
			  String racunDuznika, String racunPoverioca) throws NepoznataValutaExceptio {
		AnalitikaIzvoda analitika = new AnalitikaIzvoda();
		analitika.setDatumValute(datumValute);
		analitika.setDnevnoStanjeDuznik(dnevnoStanjeDuznik);
		analitika.setDnevnoStanjePoverilac(dnevnoStanjePoverilac);
		analitika.setIznos(iznos);
		analitika.setSvrhaPlacanja(svrha);
		analitika.setRacunDuznika(racunDuznika);
		analitika.setRacunPoverioca(racunPoverioca);
		Optional<Valuta> valuta = valutaRepo.findFirstBySifra(sifraValute);
		if(!valuta.isPresent())
			throw new NepoznataValutaExceptio("Valuta sa sifrom " + sifraValute + " ne postoji");
		analitika.setValuta(valuta.get());
		
		return analitikaIzvodaRepo.save(analitika);
	}
	
}
