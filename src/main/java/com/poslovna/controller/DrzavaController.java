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
import com.poslovna.dto.DrzavaCreation;
import com.poslovna.dto.DrzavaView;
import com.poslovna.repository.DrzavaRepo;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping(value = "/drzava")
public class DrzavaController {
	@Autowired
	DrzavaRepo repo;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(Pageable pageable, @QuerydslPredicate(root=Drzava.class) Predicate predicate){
		Page<Drzava> page = repo.findAll(predicate, pageable);
		return ResponseEntity.ok(page.map(d -> mapper.map(d, DrzavaView.class)));
	}
	
	@PostMapping(value="/new", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
							   consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> creation(@RequestBody DrzavaCreation entity){
		Drzava drzava = new Drzava();
		drzava.setNazivDrzave(entity.getNazivDrzave());
		drzava.setSifraDrzave(entity.getSifraDrzave());
		return ResponseEntity.ok(mapper.map(repo.save(drzava), DrzavaView.class));
	}
	
	@PutMapping(value="/edit", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
							   consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> edit(@RequestBody DrzavaCreation entity){
		Optional<Drzava> optionalEnt = repo.findById(entity.getSifraDrzave());
		if(!optionalEnt.isPresent()) {
			ResponseEntity.notFound().build();
		}
		optionalEnt.get().setNazivDrzave(entity.getNazivDrzave());
		return ResponseEntity.ok(mapper.map(repo.save(optionalEnt.get()), DrzavaView.class));
	}
}
