package com.poslovna.controller;


import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.beans.Drzava;
import com.poslovna.beans.Valuta;
import com.poslovna.dto.ValutaCreation;
import com.poslovna.dto.ValutaView;
import com.poslovna.repository.DrzavaRepo;
import com.poslovna.repository.ValutaRepo;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping(value = "/valuta")
public class ValutaController {
	
	@Autowired
	ValutaRepo valutaRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	DrzavaRepo drzavaRepo;
	
	@GetMapping(value="/all1", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Valuta>> getAllA(Pageable pageable, @QuerydslPredicate(root=Valuta.class) Predicate predicate){
		
		List<Valuta> page = valutaRepo.findAll();
	
		return new ResponseEntity<List<Valuta>>(page,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(Pageable pageable, @QuerydslPredicate(root=Valuta.class) Predicate predicate){
		Page<Valuta> page = valutaRepo.findAll(predicate, pageable);
		return ResponseEntity.ok(page.map(v -> mapper.map(v, ValutaView.class)));
	}
	
	@PostMapping(value="/new", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
			   				   consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> creation(@RequestBody ValutaCreation newEntity){
		Valuta valuta = new Valuta();
		Optional<Drzava> drzava = drzavaRepo.findById(newEntity.getSifraDrzave());
		if(!drzava.isPresent()){
			return ResponseEntity.notFound().build();
		}
		valuta.setDrzava(drzava.get());
		valuta.setDomaca(newEntity.isDomaca());
		valuta.setNaziv(newEntity.getNaziv());
		valuta.setSifra(newEntity.getSifra());
		return ResponseEntity.ok(mapper.map(valutaRepo.save(valuta), ValutaView.class));
	}
	
	
	@PutMapping(value="/update", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> edit(@RequestBody Valuta valuta){
		Optional<Valuta> valutaOpt = valutaRepo.findById(valuta.getSifra());
		if(!valutaOpt.isPresent()) {
			ResponseEntity.notFound().build();
		}
		valutaOpt.get().setDomaca(valuta.getDomaca());
		valutaOpt.get().setNaziv(valuta.getNaziv());
		valutaRepo.save(valutaOpt.get());
		return ResponseEntity.ok(mapper.map(valutaOpt.get(), ValutaView.class));
	}
}