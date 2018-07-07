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

import com.poslovna.beans.VrstaPlacanja;
import com.poslovna.dto.DrzavaView;
import com.poslovna.dto.VrstaPlacanjaCreation;
import com.poslovna.repository.VrstaPlacanjaRepo;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping(value="/vrstaplacanja")
public class VrstaPlacanjaController {

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	VrstaPlacanjaRepo repo;
	
	@GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(Pageable pageable, @QuerydslPredicate(root=VrstaPlacanja.class) Predicate predicate){
		Page<VrstaPlacanja> page = repo.findAll(predicate, pageable);
		return ResponseEntity.ok(page.map(d -> mapper.map(d, DrzavaView.class)));
	}
	
	@PostMapping(value="/new", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(@RequestBody VrstaPlacanjaCreation newEntity){
		VrstaPlacanja vrstaPlacanja = new VrstaPlacanja();
		vrstaPlacanja.setNazivVrstePlacanja(newEntity.getNazivVrstePlacanja());
		return ResponseEntity.ok(repo.save(vrstaPlacanja));
	}
	
	@PutMapping(value="/edit", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
			   consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> edit(@RequestBody VrstaPlacanja entity){
		Optional<VrstaPlacanja> optionalEnt = repo.findById(entity.getOznakaVrste());
		if(!optionalEnt.isPresent()) {
			ResponseEntity.notFound().build();
		}
		optionalEnt.get().setNazivVrstePlacanja(entity.getNazivVrstePlacanja());
		return ResponseEntity.ok(mapper.map(repo.save(optionalEnt.get()), DrzavaView.class));
	}
}
