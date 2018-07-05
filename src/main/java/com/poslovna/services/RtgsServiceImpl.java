package com.poslovna.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.poslovna.beans.Banka;
import com.poslovna.beans.DnevnoStanje;
import com.poslovna.beans.ObracunskiRacunBanke;
import com.poslovna.dto.RtgsCreation;
import com.poslovna.repository.BankaRepo;
import com.poslovna.repository.ObracunskiRacunBankeRepo;

public class RtgsServiceImpl implements RtgsService{
	@Autowired
	private BankaRepo bankaRepo;

	@Autowired
	private ObracunskiRacunBankeRepo racunPravnogLicaRepo;

	@Autowired
	private DnevnoStanjeService dnevnoStanjeService;

	@Autowired
	private PorukaService porukaService;
	
	@Autowired
	private AnalitikaIzvodaService analitikaIzvodaService;

	@Override
	public boolean proccessRtgs(RtgsCreation rtgsNalog) {
		Banka bankaDuznik = bankaRepo.findOneBySwift(rtgsNalog.getSwiftDuznika());
		Banka bankaPoverioca = bankaRepo.findOneBySwift(rtgsNalog.getSwiftPoverioca());
		
		//Rezervacija novaca na racunu duznika
		ObracunskiRacunBanke racunDuznika = racunPravnogLicaRepo.findOneByBrojRacuna(rtgsNalog.getRacunDuznika());
		dnevnoStanjeService.changeDnevnoStanje(rtgsNalog, racunDuznika, false);

		//Skidane novaca sa racuna banke duznika
		ObracunskiRacunBanke racunBankeDuznika = racunPravnogLicaRepo.findOneByBrojRacuna(rtgsNalog.getObracunskiRacunDuznika());
		DnevnoStanje dnevnoStanjeDuznika = dnevnoStanjeService.changeDnevnoStanje(rtgsNalog, racunBankeDuznika, false);

		//Uplata novaca na racun banke poverioca
		ObracunskiRacunBanke racunBankePoverioca = racunPravnogLicaRepo.findOneByBrojRacuna(rtgsNalog.getObracunskiRacunPoverioca());
		DnevnoStanje dnevnoStanjePoverioca = dnevnoStanjeService.changeDnevnoStanje(rtgsNalog, racunBankePoverioca, true);

		//Kreiranje poruke o zaduzenju (MT900) i poruke o odobrenju (MT910)
		porukaService.createMT900(rtgsNalog, bankaDuznik);
		porukaService.createMT910(rtgsNalog, bankaPoverioca);

		//Uplata novaca na racun poverioca
		ObracunskiRacunBanke racunPoverioca = racunPravnogLicaRepo.findOneByBrojRacuna(rtgsNalog.getRacunPoverioca());
		dnevnoStanjeService.changeDnevnoStanje(rtgsNalog, racunPoverioca, true);

		analitikaIzvodaService.createAnalitikaIzvoda(rtgsNalog, racunDuznika, racunPoverioca, dnevnoStanjePoverioca, true);
		analitikaIzvodaService.createAnalitikaIzvoda(rtgsNalog, racunDuznika, racunPoverioca, dnevnoStanjeDuznika, true);
		
		return true;
	}

}
