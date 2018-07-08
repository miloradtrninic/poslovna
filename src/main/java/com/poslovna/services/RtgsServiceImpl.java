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
import com.poslovna.dto.RtgsCreation;
import com.poslovna.exceptions.NelikvidanException;
import com.poslovna.exceptions.NepostojecaBankaException;
import com.poslovna.exceptions.NepoznataValutaExceptio;
import com.poslovna.exceptions.PojedinacnoPlacanjeException;
import com.poslovna.repository.BankaRepo;
import com.poslovna.repository.DnevnoStanjeRepo;
import com.poslovna.repository.ObracunskiRacunBankeRepo;

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

	@Override
	public void proccessRtgs(RtgsCreation rtgsNalog) throws NepostojecaBankaException, PojedinacnoPlacanjeException, NelikvidanException, NepoznataValutaExceptio {
		Calendar danasnjiDan = new GregorianCalendar();
		danasnjiDan.set(Calendar.HOUR_OF_DAY, 0);
		danasnjiDan.set(Calendar.MINUTE, 0);
		danasnjiDan.set(Calendar.SECOND, 0);
		
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

		//Skidane novaca sa racuna banke duznika
		ObracunskiRacunBanke racunBankeDuznika = bankaDuznik.get().getRacun();
		
		Optional<DnevnoStanje> dnevnoStanjeDuznika = dnevnoStanjeRepo.findFirstByRacunPravnogLicaOrderByDatumPromeneDesc(bankaDuznik.get().getRacun());
		DnevnoStanje dnevnoStanjeDuznikaNovo = new DnevnoStanje();
		if(!dnevnoStanjeDuznika.isPresent()) {
			if(bankaDuznik.get().getRacun().getDozvoljeniMinus() < rtgsNalog.getIznos()) {
				throw new NelikvidanException("Banka duznik je u nedozvoljenom minusu!");
			}else {
				dnevnoStanjeDuznikaNovo.setDatumPromene(new Date());
				dnevnoStanjeDuznikaNovo.setPrethodnoStanje(0.0);
				dnevnoStanjeDuznikaNovo.setNovoStanje(rtgsNalog.getIznos());
				dnevnoStanjeDuznikaNovo.setPromeneNaTeret(rtgsNalog.getIznos());
				dnevnoStanjeDuznikaNovo.setRacunPravnogLica(bankaDuznik.get().getRacun());
			}
		}else if(dnevnoStanjeDuznika.get().getNovoStanje() + bankaDuznik.get().getRacun().getDozvoljeniMinus() < rtgsNalog.getIznos()) {
			throw new NelikvidanException("Banka duznika nema dovoljno sredstava");
		}
		dnevnoStanjeDuznikaNovo = dnevnoStanjeDuznika.get();
		if(dnevnoStanjeDuznika.get().getDatumPromene().after(danasnjiDan.getTime())) {
			dnevnoStanjeDuznikaNovo.setNovoStanje(dnevnoStanjeDuznika.get().getNovoStanje() - rtgsNalog.getIznos());
			dnevnoStanjeDuznikaNovo.setPromeneNaTeret(dnevnoStanjeDuznika.get().getPromeneNaTeret() + rtgsNalog.getIznos());
		}else {
			dnevnoStanjeDuznikaNovo = new DnevnoStanje();
			dnevnoStanjeDuznikaNovo.setDatumPromene(new Date());
			dnevnoStanjeDuznikaNovo.setPrethodnoStanje(dnevnoStanjeDuznika.get().getNovoStanje());
			dnevnoStanjeDuznikaNovo.setNovoStanje(dnevnoStanjeDuznika.get().getNovoStanje() - rtgsNalog.getIznos());
			dnevnoStanjeDuznikaNovo.setPromeneNaTeret(rtgsNalog.getIznos());
			dnevnoStanjeDuznikaNovo.setPromeneUKorist(0.0);
		}
		
		Optional<DnevnoStanje> dnevnoStanjePoverioca = dnevnoStanjeRepo.findFirstByRacunPravnogLicaOrderByDatumPromeneDesc(bankaPoverioca.get().getRacun());
		DnevnoStanje dnevnoStanjePoveriocaNovo = new DnevnoStanje();
		if(dnevnoStanjePoverioca.isPresent()) {
			if(dnevnoStanjePoverioca.get().getDatumPromene().after(danasnjiDan.getTime())) {
				dnevnoStanjePoveriocaNovo = dnevnoStanjePoverioca.get();
				dnevnoStanjePoverioca.get().setNovoStanje(dnevnoStanjePoverioca.get().getNovoStanje()+rtgsNalog.getIznos());
			}else {
				dnevnoStanjePoveriocaNovo.setDatumPromene(new Date());
				dnevnoStanjePoveriocaNovo.setNovoStanje(rtgsNalog.getIznos());
				dnevnoStanjePoveriocaNovo.setPrethodnoStanje(dnevnoStanjePoverioca.get().getNovoStanje());
				dnevnoStanjePoveriocaNovo.setRacunPravnogLica(bankaPoverioca.get().getRacun());
			}
		}
		dnevnoStanjeRepo.save(dnevnoStanjeDuznikaNovo);
		dnevnoStanjeRepo.save(dnevnoStanjePoveriocaNovo);

		

		//Kreiranje poruke o zaduzenju (MT900) i poruke o odobrenju (MT910)
		porukaService.createMT900(rtgsNalog, bankaDuznik.get());
		porukaService.createMT910(rtgsNalog, bankaPoverioca.get());

		AnalitikaIzvoda analitika = analitikaIzvodaService
				.createAnalitikaIzvoda(rtgsNalog.getSifraValute(), 
									   rtgsNalog.getDatumValute(),
									   rtgsNalog.getIznos(),
									   dnevnoStanjeDuznikaNovo,
									   dnevnoStanjePoveriocaNovo,
									   rtgsNalog.getSvrhaPlacanja(),
									   rtgsNalog.getRacunDuznika(),
									   rtgsNalog.getRacunPoverioca());
	}

}
