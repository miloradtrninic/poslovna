package com.poslovna.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.poslovna.beans.DnevnoStanje;
import com.poslovna.beans.RacunPravnogLica;
import com.poslovna.dto.RtgsCreation;
import com.poslovna.repository.DnevnoStanjeRepo;

public class DnevnoStanjeServiceImpl implements DnevnoStanjeService{

	@Autowired 
	private DnevnoStanjeRepo dnevnoStanjeRepo;

	@Override
	public boolean changeDnevnoStanje(RtgsCreation rtgsNalog, RacunPravnogLica racun, boolean uKorist) {

		DnevnoStanje dnevnoStanje = new DnevnoStanje();
		Optional<DnevnoStanje> dnevnoStanjeOptional = dnevnoStanjeRepo.findOneByRacunPravnogLicaAndDatumPromene(racun, rtgsNalog.getDatumNaloga());
		dnevnoStanje.setDatumPromene(rtgsNalog.getDatumNaloga());
		if(!dnevnoStanjeOptional.isPresent()) {
			dnevnoStanje.setRacunPravnogLica(racun);
			dnevnoStanje.setPromeneUKorist(0.0);
		}else {
			dnevnoStanje = dnevnoStanjeOptional.get(); 
			dnevnoStanje.setPromeneUKorist(0.0);
		}
		if(uKorist) {
			dnevnoStanje.setPromeneUKorist(rtgsNalog.getIznos() + dnevnoStanje.getPromeneUKorist());
		}
		else {
			dnevnoStanje.setPromeneNaTeret(dnevnoStanje.getPromeneNaTeret() + rtgsNalog.getIznos());
		}
		dnevnoStanjeRepo.save(dnevnoStanje);
		
		return true;

	}

}
