package com.poslovna.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.poslovna.beans.AnalitikaIzvoda;
import com.poslovna.beans.DnevnoStanje;
import com.poslovna.beans.RacunPravnogLica;
import com.poslovna.beans.Valuta;
import com.poslovna.dto.RtgsCreation;
import com.poslovna.repository.AnalitikaIzvodaRepo;
import com.poslovna.repository.ValutaRepo;

public class AnalitikaIzvodaServiceImpl implements AnalitikaIzvodaService{
	@Autowired
	private AnalitikaIzvodaRepo analitikaIzvodaRepo;
	
	@Autowired
	private ValutaRepo valutaRepo;

	@Override
	public AnalitikaIzvoda createAnalitikaIzvoda(RtgsCreation rtgsNalog, RacunPravnogLica racunDuznika, RacunPravnogLica racunPoverioca, DnevnoStanje dnevnoStanje, boolean hitno) {
		AnalitikaIzvoda analitika = new AnalitikaIzvoda();
		analitika.setDatumValute(rtgsNalog.getDatumValute());
		analitika.setDnevnoStanje(dnevnoStanje);
		analitika.setDuznik(racunDuznika);
		analitika.setHitno(hitno);
		analitika.setIznos(rtgsNalog.getIznos());
		analitika.setModelOdobrenja(rtgsNalog.getModelOdobrenja());
		analitika.setModelZaduzenja(rtgsNalog.getModelZaduzenja());
		analitika.setPoverilac(racunPoverioca);
		analitika.setPozivNaBroj(rtgsNalog.getPozivNaBrojZaduzenja());
		analitika.setPozivNaBrojOdobrenja(rtgsNalog.getPozivNaBrojOdobrenja());
		analitika.setPrimljen(rtgsNalog.getDatumNaloga());
		analitika.setSvrhaPlacanja(rtgsNalog.getSvrhaPlacanja());
	
		Optional<Valuta> valuta = valutaRepo.findById(rtgsNalog.getSifraValute());
		analitika.setValuta(valuta.get());
		
		return analitikaIzvodaRepo.save(analitika);
	}
	
}
