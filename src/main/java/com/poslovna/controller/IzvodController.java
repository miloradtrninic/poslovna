package com.poslovna.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.beans.AnalitikaIzvoda;
import com.poslovna.dto.AnalitikaIzvodaView;
import com.poslovna.dto.IzvodView;
import com.poslovna.repository.AnalitikaIzvodaRepo;

@RestController
@RequestMapping(value="/izvod")
public class IzvodController {
	
	@Autowired
	AnalitikaIzvodaRepo analitikaIzvodaRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getIzvod(Date pocetak, Date kraj, String brojRacuna) {
		List<AnalitikaIzvoda> analitike = analitikaIzvodaRepo.findAllByDateAndAccount(pocetak, kraj, brojRacuna);
		if(CollectionUtils.isEmpty(analitike)) {
			return ResponseEntity.noContent().build(); 
		}
		IzvodView izvod = new IzvodView();
		izvod.setBrojRacuna(brojRacuna);
		List<AnalitikaIzvodaView> analitikeView = analitike.stream().map(a -> mapper.map(a, AnalitikaIzvodaView.class)).collect(Collectors.toCollection(ArrayList::new));
		izvod.setAnalitike(analitikeView);
		return ResponseEntity.ok(izvod);
	}
}
