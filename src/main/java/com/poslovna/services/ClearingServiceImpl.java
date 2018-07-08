package com.poslovna.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovna.beans.AnalitikaIzvoda;
import com.poslovna.beans.Banka;
import com.poslovna.beans.DnevnoStanje;
import com.poslovna.dto.ClearingCreation;
import com.poslovna.dto.PojedinacnoPlacanjeCreation;
import com.poslovna.exceptions.NelikvidanException;
import com.poslovna.exceptions.NepostojecaBankaException;
import com.poslovna.exceptions.NepoznataValutaExceptio;
import com.poslovna.exceptions.PojedinacnoPlacanjeException;
import com.poslovna.repository.BankaRepo;
import com.poslovna.repository.DnevnoStanjeRepo;
import com.poslovna.repository.ObracunskiRacunBankeRepo;
import com.poslovna.repository.ValutaRepo;

@Service
@Transactional
public class ClearingServiceImpl implements ClearingSerivce{
	@Autowired
	BankaRepo bankaRepo;
	
	@Autowired
	ObracunskiRacunBankeRepo obracunskiRacunRepo;
	
	@Autowired
	DnevnoStanjeRepo dnevnoStanjeRepo;
	
	@Autowired
	ValutaRepo valutaRepo;
	
	@Autowired
	AnalitikaIzvodaService analitikaService;
	
	@Override
	public void processClearing(ClearingCreation clearing) throws NepostojecaBankaException, PojedinacnoPlacanjeException, NelikvidanException, NepoznataValutaExceptio {
		Calendar danasnjiDan = new GregorianCalendar();
		danasnjiDan.set(Calendar.HOUR_OF_DAY, 0);
		danasnjiDan.set(Calendar.MINUTE, 0);
		danasnjiDan.set(Calendar.SECOND, 0);
		
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
		ArrayList<String> placanjaPrevelika = clearing.getPlacanja().stream()
													  .filter(p -> p.getIznos() > 250000.0)
													  .map(p -> p.getId())
													  .collect(Collectors.toCollection(ArrayList::new));
		Double sumaPlacanja = clearing.getPlacanja().stream()
									  .mapToDouble(p -> p.getIznos())
									  .sum();
		if(sumaPlacanja == clearing.getUkupanIznos()) {
			throw new PojedinacnoPlacanjeException("Suma iznosa pojedinacnih placanja nije jednaka sumi kliringa.");
		}
		if(!placanjaPrevelika.isEmpty()) {
			throw new PojedinacnoPlacanjeException("Postoje pojedinacna placanja veca od 250000, ID-jevi placanja " + placanjaPrevelika.toString());
		}
		Optional<DnevnoStanje> dnevnoStanjeDuznikOpt = dnevnoStanjeRepo.findFirstByRacunPravnogLicaOrderByDatumPromeneDesc(duznik.get().getRacun());
		DnevnoStanje dnevnoStanjeDuznik = new DnevnoStanje();
		if(!dnevnoStanjeDuznikOpt.isPresent()) {
			if(duznik.get().getRacun().getDozvoljeniMinus() < clearing.getUkupanIznos()) {
				throw new NelikvidanException("Banka duznik nema dovoljno sredstava.");
			} else {
				dnevnoStanjeDuznik = new DnevnoStanje();
				dnevnoStanjeDuznik.setDatumPromene(new Date());
				dnevnoStanjeDuznik.setPrethodnoStanje(0.0);
				dnevnoStanjeDuznik.setNovoStanje(-clearing.getUkupanIznos());
				dnevnoStanjeDuznik.setPromeneNaTeret(clearing.getUkupanIznos());
				dnevnoStanjeDuznik.setPromeneUKorist(0.0);
			}
		} else if(dnevnoStanjeDuznikOpt.get().getNovoStanje() + duznik.get().getRacun().getDozvoljeniMinus() < clearing.getUkupanIznos()) {
			throw new NelikvidanException("Banka duznik nema dovoljno sredstava.");
		} else {
			dnevnoStanjeDuznik = dnevnoStanjeDuznikOpt.get();
		}
		if(dnevnoStanjeDuznik.getDatumPromene().after(danasnjiDan.getTime())) {
			dnevnoStanjeDuznik.setNovoStanje(dnevnoStanjeDuznikOpt.get().getNovoStanje() - clearing.getUkupanIznos());
			dnevnoStanjeDuznik.setPromeneNaTeret(dnevnoStanjeDuznikOpt.get().getPromeneNaTeret() + clearing.getUkupanIznos());
		} else {
			dnevnoStanjeDuznik = new DnevnoStanje();
			dnevnoStanjeDuznik.setDatumPromene(new Date());
			dnevnoStanjeDuznik.setPrethodnoStanje(dnevnoStanjeDuznikOpt.get().getNovoStanje());
			dnevnoStanjeDuznik.setNovoStanje(dnevnoStanjeDuznikOpt.get().getNovoStanje() - clearing.getUkupanIznos());
			dnevnoStanjeDuznik.setPromeneNaTeret(clearing.getUkupanIznos());
			dnevnoStanjeDuznik.setPromeneUKorist(0.0);
		}
		
		Optional<DnevnoStanje> dnevnoStanjePoverilacOpt = dnevnoStanjeRepo.findFirstByRacunPravnogLicaOrderByDatumPromeneDesc(poverilac.get().getRacun());
		DnevnoStanje dnevnoStanjePoverilac = new DnevnoStanje();
		if(dnevnoStanjePoverilacOpt.isPresent()) {
			if(dnevnoStanjePoverilacOpt.get().getDatumPromene().after(danasnjiDan.getTime())) {
				dnevnoStanjePoverilac = dnevnoStanjePoverilacOpt.get();
				dnevnoStanjePoverilacOpt.get().setNovoStanje(dnevnoStanjePoverilacOpt.get().getNovoStanje() + clearing.getUkupanIznos());
			} else {
				dnevnoStanjePoverilac.setDatumPromene(new Date());
				dnevnoStanjePoverilac.setNovoStanje(clearing.getUkupanIznos());
				dnevnoStanjePoverilac.setPrethodnoStanje(dnevnoStanjePoverilacOpt.get().getNovoStanje());
				dnevnoStanjePoverilac.setRacunPravnogLica(poverilac.get().getRacun());
			}
		} else {
			dnevnoStanjePoverilac.setDatumPromene(new Date());
			dnevnoStanjePoverilac.setNovoStanje(clearing.getUkupanIznos());
			dnevnoStanjePoverilac.setPrethodnoStanje(0.0);
			dnevnoStanjePoverilac.setRacunPravnogLica(poverilac.get().getRacun());
			
		}
		dnevnoStanjeRepo.save(dnevnoStanjeDuznik);
		dnevnoStanjeRepo.save(dnevnoStanjePoverilac);
		for(PojedinacnoPlacanjeCreation placanje:clearing.getPlacanja()) {
			AnalitikaIzvoda analitika = analitikaService
					.createAnalitikaIzvoda(placanje.getSifraValute(), 
										   clearing.getDatumValute(),
										   placanje.getIznos(),
										   dnevnoStanjeDuznik,
										   dnevnoStanjePoverilac,
										   placanje.getSvrha(),
										   clearing.getRacunDuznik(),
										   clearing.getRacunPoverilac());
		}
		
		
		
	}

}
