package com.poslovna.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.beans.Banka;
import com.poslovna.dto.BankaView;
import com.poslovna.dto.DrzavaView;
import com.poslovna.repository.BankaRepo;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping(value="/banka")
public class BankaController {
	@Autowired
	BankaRepo repo;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(Pageable pageable, @QuerydslPredicate(root=Banka.class) Predicate predicate){
		Page<Banka> page = repo.findAll(predicate, pageable);
		return ResponseEntity.ok(page.map(d -> mapper.map(d, BankaView.class)));
	}
}
