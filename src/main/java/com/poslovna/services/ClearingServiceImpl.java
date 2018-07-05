package com.poslovna.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovna.beans.Banka;
import com.poslovna.beans.DnevnoStanje;
import com.poslovna.dto.ClearingCreation;
import com.poslovna.exceptions.NelikvidanException;
import com.poslovna.exceptions.NepostojecaBankaException;
import com.poslovna.exceptions.PojedinacnoPlacanjeException;
import com.poslovna.repository.BankaRepo;
import com.poslovna.repository.DnevnoStanjeRepo;
import com.poslovna.repository.ObracunskiRacunBankeRepo;

@Service
@Transactional
public class ClearingServiceImpl implements ClearingSerivce{
	@Autowired
	BankaRepo bankaRepo;
	
	@Autowired
	ObracunskiRacunBankeRepo obracunskiRacunRepo;
	
	@Autowired
	DnevnoStanjeRepo dnevnoStanjeRepo;
	
	@Override
	public void processClearing(ClearingCreation clearing) throws NepostojecaBankaException, PojedinacnoPlacanjeException, NelikvidanException {
		Optional<Banka> duznik = bankaRepo.findOneBySwift(clearing.getSwiftDuznik());
		if(!duznik.isPresent()) {
			throw new NepostojecaBankaException("Bank ne postoji SWIFT: " + clearing.getSwiftDuznik());
		}
		Optional<Banka> poverilac = bankaRepo.findOneBySwift(clearing.getSwitftPoverilac());
		if(!poverilac.isPresent()) {
			throw new NepostojecaBankaException("Bank ne postoji SWIFT: " + clearing.getSwitftPoverilac());
		}
		if(!duznik.get().getRacun().getBrojRacuna().equals(clearing.getRacunDuznik())) {
			throw new NepostojecaBankaException("Obracunski racun "+ clearing.getRacunDuznik() + " ne postoji");
		}
		if(!poverilac.get().getRacun().getBrojRacuna().equals(clearing.getRacunPoverilac())) {
			throw new NepostojecaBankaException("Obracunski racun "+ clearing.getRacunPoverilac() + " ne postoji");
		}
		ArrayList<String> placanjaPrevelika = clearing.getPlacanja()
			.stream()
			.filter(p -> p.getIznos() > 250000.0)
			.map(p -> p.getId())
			.collect(Collectors.toCollection(ArrayList::new));
		Double sumaPlacanja = clearing.getPlacanja()
				.stream()
				.mapToDouble(p -> p.getIznos())
				.sum();
		if(sumaPlacanja == clearing.getIznos()) {
			throw new PojedinacnoPlacanjeException("Suma iznosa pojedinacnih placanja nije jednaka sumi kliringa.");
		}
		if(!placanjaPrevelika.isEmpty()) {
			throw new PojedinacnoPlacanjeException("Postoje pojedinacna placanja veca od 250000, ID-jevi placanja " + placanjaPrevelika.toString());
		}
		Optional<DnevnoStanje> dnevnoStanjeDuznik = dnevnoStanjeRepo.findFirstByRacunPravnogLicaOrderByDatumPromeneDesc(duznik.get().getRacun());
		if(!dnevnoStanjeDuznik.isPresent()) {
			if(duznik.get().getRacun().getDozvoljeniMinus() < clearing.getIznos()) {
				throw new NelikvidanException("Banka duznik nema dovoljno sredstava.");
			}
		} else if(dnevnoStanjeDuznik.get().getNovoStanje() < clearing.getIznos()) {
			throw new NelikvidanException("Banka duznik nema dovoljno sredstava.");
		}
		Double novoStanjeDuznik = dnevnoStanjeDuznik.get().getNovoStanje() - clearing.getIznos(); 
		dnevnoStanjeDuznik.get().setNovoStanje(novoStanjeDuznik);
		
		Optional<DnevnoStanje> dnevnoStanjePoverilac = dnevnoStanjeRepo.findFirstByRacunPravnogLicaOrderByDatumPromeneDesc(poverilac.get().getRacun());
		//TODO ispitati da li je pronadjeni datum danasnji, ako nije preuzeti vrednost iz prethodnog i napraviti row, ako jesete ostavi ovaj kod
		if(dnevnoStanjePoverilac.isPresent()) {
			dnevnoStanjePoverilac.get().setNovoStanje(dnevnoStanjePoverilac.get().getNovoStanje() + clearing.getIznos());
			dnevnoStanjeRepo.save(dnevnoStanjePoverilac.get());
		} else {
			DnevnoStanje dnevnoStanje = new DnevnoStanje();
			dnevnoStanje.setDatumPromene(new Date());
			dnevnoStanje.setNovoStanje(clearing.getIznos());
			dnevnoStanje.setPrethodnoStanje(0.0);
			
		}
	}

}
