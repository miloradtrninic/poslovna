package com.poslovna.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.poslovna.beans.Banka;
import com.poslovna.beans.RacunPravnogLica;
import com.poslovna.dto.RtgsCreation;
import com.poslovna.repository.BankaRepo;
import com.poslovna.repository.DnevnoStanjeRepo;
import com.poslovna.repository.RacunPravnogLicaRepo;

public class RtgsServiceImpl implements RtgsService{
	@Autowired
	private BankaRepo bankaRepo;
	
	@Autowired
	private RacunPravnogLicaRepo racunPravnogLicaRepo;
	
	@Autowired
	private DnevnoStanjeRepo dnevnoStanjeRepo;
	
	@Autowired
	private DnevnoStanjeService dnevnoStanjeService;

	@Override
	public boolean proccessRtgs(RtgsCreation rtgsNalog) {
		Banka bankaDuznik = bankaRepo.findOneBySwift(rtgsNalog.getSwiftDuznika());
		RacunPravnogLica racunDuznika = racunPravnogLicaRepo.findOneByBrojRacuna(rtgsNalog.getRacunDuznika());
		
		
		RacunPravnogLica racunBankeDuznika = racunPravnogLicaRepo.findOneByBrojRacuna(rtgsNalog.getObracunskiRacunDuznika());
		dnevnoStanjeService.changeDnevnoStanje(rtgsNalog, racunBankeDuznika, false);
		
		
		
		
	}

}
