package com.poslovna.controller;

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

import com.poslovna.beans.Drzava;
import com.poslovna.beans.NaseljenoMesto;
import com.poslovna.dto.DrzavaView;
import com.poslovna.dto.NaseljenoMestoCreation;
import com.poslovna.dto.NaseljenoMestoView;
import com.poslovna.repository.DrzavaRepo;
import com.poslovna.repository.NaseljenoMestoRepo;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping(value = "/naseljenomesto")
public class NaseljenoMestoController {
	@Autowired
	NaseljenoMestoRepo repo;

	@Autowired
	DrzavaRepo drzavaRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(Pageable pageable, @QuerydslPredicate(root=NaseljenoMesto.class) Predicate predicate){
		Page<NaseljenoMesto> page = repo.findAll(predicate, pageable);
		return ResponseEntity.ok(page);
	}

	@PostMapping(value="/new", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> creation(@RequestBody NaseljenoMestoCreation entity){
		NaseljenoMesto naseljenoMesto = new NaseljenoMesto();
		naseljenoMesto.setNaziv(entity.getNaziv());
		naseljenoMesto.setPttOznaka(entity.getPttOznaka());
		naseljenoMesto.setSifraMesta(entity.getSifraMesta());
		Optional<Drzava> optionalEnt = drzavaRepo.findById(entity.getSifraDrzave());
		if(!optionalEnt.isPresent()) {
			ResponseEntity.notFound().build();
		}
		naseljenoMesto.setDrzava(optionalEnt.get());
		return ResponseEntity.ok(mapper.map(repo.save(naseljenoMesto), NaseljenoMestoView.class));
	}

	@PutMapping(value="/update", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> edit(@RequestBody NaseljenoMestoView entity) {
		Optional<NaseljenoMesto> optionalEnt = repo.findById(entity.getId());
		if(!optionalEnt.isPresent()) {
			ResponseEntity.notFound().build();
		}
		optionalEnt.get().setNaziv(entity.getNaziv());
		optionalEnt.get().setPttOznaka(entity.getPttOznaka());
		optionalEnt.get().setSifraMesta(entity.getSifraMesta());
		return ResponseEntity.ok(repo.save(optionalEnt.get()));
	}
}
