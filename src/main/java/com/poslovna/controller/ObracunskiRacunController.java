package com.poslovna.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.beans.ObracunskiRacunBanke;
import com.poslovna.beans.Valuta;
import com.poslovna.dto.ObracunskiRacunCreation;
import com.poslovna.repository.ObracunskiRacunBankeRepo;
import com.poslovna.repository.ValutaRepo;
import com.querydsl.core.types.Predicate;


@RestController
@RequestMapping(value = "/obracunskiRacun")
public class ObracunskiRacunController {

	
	@Autowired
	private ObracunskiRacunBankeRepo orbRep;
	
	@Autowired 
	private ValutaRepo vatRep;
	
	
	@PostMapping(value="/new", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
							   consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> creation(@RequestBody ObracunskiRacunCreation entity){
		
		ObracunskiRacunBanke orb = new ObracunskiRacunBanke();
		Valuta v = vatRep.findBySifra(entity.getValuta());
		
		orb.setBrojRacuna(entity.getBrojRacuna());
		orb.setDatumOtvaranja(new Date());
		orb.setVazeci(true);
		orb.setValuta(v);
		orb.setDozvoljeniMinus(Double.parseDouble(entity.getDozvoljeniMinus()));
		//TODO: VIDI DNEVNO STANJE
		
		orbRep.save(orb);
		
		return ResponseEntity.ok("lala");
	}
	
	
	@GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ObracunskiRacunBanke>> getAll(Pageable pageable, @QuerydslPredicate(root=Valuta.class) Predicate predicate){
		
		List<ObracunskiRacunBanke> page = orbRep.findAll();
	
		return new ResponseEntity<List<ObracunskiRacunBanke>>(page,HttpStatus.OK);
	}
}
