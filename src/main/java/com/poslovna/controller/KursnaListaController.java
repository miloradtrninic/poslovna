package com.poslovna.controller;

import java.util.HashSet;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.beans.Banka;
import com.poslovna.beans.KursValuta;
import com.poslovna.beans.KursnaLista;
import com.poslovna.beans.Valuta;
import com.poslovna.dto.KursValutaCreation;
import com.poslovna.dto.KursnaListaCreation;
import com.poslovna.dto.KursnaListaView;
import com.poslovna.repository.BankaRepo;
import com.poslovna.repository.KursnaListaRepo;
import com.poslovna.repository.ValutaRepo;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/kursnalista")
@Slf4j
public class KursnaListaController {
	@Autowired
	KursnaListaRepo repo;
	
	@Autowired
	BankaRepo bankaRepo;
	
	@Autowired
	ValutaRepo valutaRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(Pageable pageable, @QuerydslPredicate(root=KursnaLista.class) Predicate predicate){
		Page<KursnaLista> page = repo.findAll(predicate, pageable);
		return ResponseEntity.ok(page.map(d -> mapper.map(d, KursnaListaView.class)));
	}
	
	@PostMapping(value="/new", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
							   consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> creation(@RequestBody KursnaListaCreation entity){
		KursnaLista kursnaLista = new KursnaLista();
		Optional<Banka> banka = bankaRepo.findById(entity.getPIB());
		if(!banka.isPresent()) {
			ResponseEntity.notFound().build();
		}
		kursnaLista.setBanka(banka.get());
		kursnaLista.setNazivKursneListe(entity.getNaziv());
		kursnaLista.setPrimenjujeSeOd(entity.getPrimenjujeSeOd());
		HashSet<KursValuta> valute = new HashSet<>();
		for(KursValutaCreation kursValutaCreation : entity.getValute()) {
			KursValuta kursValuta = new KursValuta();
			kursValuta.setKupovni(kursValutaCreation.getKupovni());
			kursValuta.setProdajni(kursValutaCreation.getProdajni());
			kursValuta.setSrednji(kursValutaCreation.getSrednji());
			Optional<Valuta> osnovna = valutaRepo.findFirstBySifra(kursValutaCreation.getOsnovnaValuta());
			Optional<Valuta> prema = valutaRepo.findFirstBySifra(kursValutaCreation.getPremaValuti());
			kursValuta.setOsnovnaValuta(osnovna.get());
			kursValuta.setPremaValuti(prema.get());
			valute.add(kursValuta);
			kursValuta.setKursnaLista(kursnaLista);
		}
		repo.save(kursnaLista);
		kursnaLista.setValute(valute);
		return ResponseEntity.ok(mapper.map(repo.save(kursnaLista), KursnaListaView.class));
	}
	
	@PutMapping(value="/update", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
			   consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> edit(@RequestBody KursnaLista entity){
		Optional<KursnaLista> optionalEnt = repo.findById(entity.getId());
		if(!optionalEnt.isPresent()) {
			ResponseEntity.notFound().build();
		}
		optionalEnt.get().setNazivKursneListe(entity.getNazivKursneListe());
		return ResponseEntity.ok(mapper.map(repo.save(optionalEnt.get()), KursnaListaView.class));
	}
}
