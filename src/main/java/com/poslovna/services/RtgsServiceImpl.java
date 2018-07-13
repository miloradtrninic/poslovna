package com.poslovna.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovna.beans.AnalitikaIzvoda;
import com.poslovna.beans.Banka;
import com.poslovna.beans.DnevnoStanje;
import com.poslovna.beans.ObracunskiRacunBanke;
import com.poslovna.beans.RTGSNalog;
import com.poslovna.beans.Valuta;
import com.poslovna.dto.RtgsCreation;
import com.poslovna.exceptions.NelikvidanException;
import com.poslovna.exceptions.NepostojecaBankaException;
import com.poslovna.exceptions.NepoznataValutaExceptio;
import com.poslovna.exceptions.PojedinacnoPlacanjeException;
import com.poslovna.repository.BankaRepo;
import com.poslovna.repository.DnevnoStanjeRepo;
import com.poslovna.repository.ObracunskiRacunBankeRepo;
import com.poslovna.repository.RTGSNalogRepo;
import com.poslovna.repository.ValutaRepo;

@Service
@Transactional
public class RtgsServiceImpl implements RtgsService{
	@Autowired
	private BankaRepo bankaRepo;

	@Autowired
	private ObracunskiRacunBankeRepo obracunskiRacunRepo;

	@Autowired
	private DnevnoStanjeService dnevnoStanjeService;
	
	@Autowired
	private DnevnoStanjeRepo dnevnoStanjeRepo;

	@Autowired
	private PorukaService porukaService;
	
	@Autowired
	private AnalitikaIzvodaService analitikaIzvodaService;
	
	@Autowired
	ValutaRepo valutaRepo;
	
	@Autowired
	RTGSNalogRepo rtgsNalogRepo;

	@Override
	public void proccessRtgs(RtgsCreation rtgsNalog) throws NepostojecaBankaException, PojedinacnoPlacanjeException, NelikvidanException, NepoznataValutaExceptio {
		Calendar danasnjiDan = new GregorianCalendar();
		danasnjiDan.set(Calendar.HOUR_OF_DAY, 0);
		danasnjiDan.set(Calendar.MINUTE, 0);
		danasnjiDan.set(Calendar.SECOND, 0);
		
		
		Optional<Valuta> valuta = valutaRepo.findFirstBySifra(rtgsNalog.getSifraValute());
		if(!valuta.isPresent()) {
			throw new NepoznataValutaExceptio("Ne postoji valuta sa sifrom " + rtgsNalog.getSifraValute());
		}
		Optional<Banka> bankaDuznik = bankaRepo.findOneBySwift(rtgsNalog.getSwiftDuznika());
		if(!bankaDuznik.isPresent()) {
			throw new NepostojecaBankaException("Banka ne postoji SWIFT: " + rtgsNalog.getSwiftDuznika());
		}
		Optional<Banka> bankaPoverioca = bankaRepo.findOneBySwift(rtgsNalog.getSwiftPoverioca());
		if(!bankaPoverioca.isPresent()) {
			throw new NepostojecaBankaException("Banka ne postoji SWIFT: " + rtgsNalog.getSwiftPoverioca());
		}
		if(!bankaDuznik.get().getRacun().getBrojRacuna().equals(rtgsNalog.getObracunskiRacunDuznika())) {
			throw new NepostojecaBankaException("Obracunski racun " + rtgsNalog.getObracunskiRacunDuznika() + " ne postoji.");
		}
		if(!bankaPoverioca.get().getRacun().getBrojRacuna().equals(rtgsNalog.getObracunskiRacunPoverioca())) {
			throw new NepostojecaBankaException("Obracunski racun " + rtgsNalog.getObracunskiRacunPoverioca() + " ne postoji.");
		}
		if(!bankaDuznik.get().getRacun().getValuta().getSifra().equals(valuta.get().getSifra())) {
			throw new NepoznataValutaExceptio("Valuta sa sifrom " + rtgsNalog.getSifraValute() + " ne odgovara racunu " + bankaDuznik.get().getRacun().getBrojRacuna());
		}
		if(!bankaPoverioca.get().getRacun().getValuta().getSifra().equals(valuta.get().getSifra())) {
			throw new NepoznataValutaExceptio("Valuta sa sifrom " + rtgsNalog.getSifraValute() + " ne odgovara racunu " + bankaPoverioca.get().getRacun().getBrojRacuna());
		}
		//Skidane novaca sa racuna banke duznika
		ObracunskiRacunBanke racunBankeDuznika = bankaDuznik.get().getRacun();
		
		Optional<DnevnoStanje> dnevnoStanjeDuznika = dnevnoStanjeRepo.findFirstByRacunPravnogLicaOrderByDatumPromeneDesc(bankaDuznik.get().getRacun());
		DnevnoStanje dnevnoStanjeDuznikaNovo = new DnevnoStanje();
		if(!dnevnoStanjeDuznika.isPresent()) {
			if(bankaDuznik.get().getRacun().getDozvoljeniMinus() < rtgsNalog.getIznos()) {
				throw new NelikvidanException("Banka duznik je u nedozvoljenom minusu!");
			}else {
				dnevnoStanjeDuznikaNovo.setDatumPromene(new Date());
				dnevnoStanjeDuznikaNovo.setNovoStanje(rtgsNalog.getIznos());
				dnevnoStanjeDuznikaNovo.setRacunPravnogLica(bankaDuznik.get().getRacun());
				dnevnoStanjeDuznikaNovo.addTeret(rtgsNalog.getIznos());
			}
		}else if(dnevnoStanjeDuznika.get().getNovoStanje() + bankaDuznik.get().getRacun().getDozvoljeniMinus() < rtgsNalog.getIznos()) {
			throw new NelikvidanException("Banka duznika nema dovoljno sredstava");
		}else {
			dnevnoStanjeDuznikaNovo = dnevnoStanjeDuznika.get();
		}
		if(dnevnoStanjeDuznikaNovo.getDatumPromene().after(danasnjiDan.getTime())) {
			dnevnoStanjeDuznikaNovo.setNovoStanje(dnevnoStanjeDuznikaNovo.getNovoStanje() - rtgsNalog.getIznos());
			dnevnoStanjeDuznikaNovo.addTeret(rtgsNalog.getIznos());
		}else {
			dnevnoStanjeDuznikaNovo = new DnevnoStanje();
			dnevnoStanjeDuznikaNovo.setDatumPromene(new Date());
			dnevnoStanjeDuznikaNovo.setPrethodnoStanje(dnevnoStanjeDuznika.get().getNovoStanje());
			dnevnoStanjeDuznikaNovo.setNovoStanje(dnevnoStanjeDuznika.get().getNovoStanje() - rtgsNalog.getIznos());
			dnevnoStanjeDuznikaNovo.addTeret(rtgsNalog.getIznos());
		}
		
		Optional<DnevnoStanje> dnevnoStanjePoverioca = dnevnoStanjeRepo.findFirstByRacunPravnogLicaOrderByDatumPromeneDesc(bankaPoverioca.get().getRacun());
		DnevnoStanje dnevnoStanjePoveriocaNovo = new DnevnoStanje();
		if(dnevnoStanjePoverioca.isPresent()) {
			if(dnevnoStanjePoverioca.get().getDatumPromene().after(danasnjiDan.getTime())) {
				dnevnoStanjePoveriocaNovo = dnevnoStanjePoverioca.get();
				dnevnoStanjePoverioca.get().setNovoStanje(dnevnoStanjePoveriocaNovo.getNovoStanje()+rtgsNalog.getIznos());
				dnevnoStanjePoveriocaNovo.addKorist(rtgsNalog.getIznos());
			}else {
				dnevnoStanjePoveriocaNovo.setDatumPromene(new Date());
				dnevnoStanjePoveriocaNovo.setNovoStanje(rtgsNalog.getIznos());
				dnevnoStanjePoveriocaNovo.setPrethodnoStanje(dnevnoStanjePoverioca.get().getNovoStanje());
				dnevnoStanjePoveriocaNovo.setRacunPravnogLica(bankaPoverioca.get().getRacun());
				dnevnoStanjePoveriocaNovo.addKorist(rtgsNalog.getIznos());
			}
		} else {
			dnevnoStanjePoveriocaNovo.setDatumPromene(new Date());
			dnevnoStanjePoveriocaNovo.setNovoStanje(rtgsNalog.getIznos());
			dnevnoStanjePoveriocaNovo.setRacunPravnogLica(bankaPoverioca.get().getRacun());
			dnevnoStanjePoveriocaNovo.addKorist(rtgsNalog.getIznos());
		}
		dnevnoStanjeRepo.save(dnevnoStanjeDuznikaNovo);
		dnevnoStanjeRepo.save(dnevnoStanjePoveriocaNovo);

		//Kreiranje poruke o zaduzenju (MT900) i poruke o odobrenju (MT910)
		porukaService.createMT900(rtgsNalog.getDatumValute(), rtgsNalog.getIznos(), rtgsNalog.getId(), valuta.get(), bankaDuznik.get());
		porukaService.createMT910(rtgsNalog.getDatumValute(), rtgsNalog.getIznos(), rtgsNalog.getId(), valuta.get(), bankaPoverioca.get());

		AnalitikaIzvoda analitika = analitikaIzvodaService
				.createAnalitikaIzvoda(rtgsNalog.getDatumNaloga(), rtgsNalog.getSifraValute(), 
									   rtgsNalog.getDatumValute(),
									   rtgsNalog.getIznos(),
									   dnevnoStanjeDuznikaNovo,
									   dnevnoStanjePoveriocaNovo,
									   rtgsNalog.getSvrhaPlacanja(),
									   rtgsNalog.getRacunDuznika(),
									   rtgsNalog.getRacunPoverioca());
		RTGSNalog nalog = new RTGSNalog();
		nalog.setAnalitikaIzvoda(analitika);
		rtgsNalogRepo.save(nalog);
	}

}
