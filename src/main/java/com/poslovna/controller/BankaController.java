package com.poslovna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.poslovna.beans.AnalitikaIzvoda;
import com.poslovna.beans.Banka;
import com.poslovna.dto.AnalitikaIzvodaView;
import com.poslovna.dto.BankaCreation;
import com.poslovna.dto.BankaView;
import com.poslovna.dto.IzvodView;
import com.poslovna.repository.AnalitikaIzvodaRepo;
import com.poslovna.repository.BankaRepo;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping(value="/banka")
public class BankaController {
	@Autowired
	BankaRepo repo;
	
	@Autowired
	AnalitikaIzvodaRepo analitikaIzvodaRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(Pageable pageable, @QuerydslPredicate(root=Banka.class) Predicate predicate){
		Page<Banka> page = repo.findAll(predicate, pageable);
		return ResponseEntity.ok(page.map(d -> mapper.map(d, BankaView.class)));
	}
	@GetMapping(value="/allNoAccount", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(){
		Set<Banka> banke = repo.findAllByRacunIsNull();
		return ResponseEntity.ok(banke.stream().map(b -> mapper.map(b, BankaView.class)));
	}
	
	@PostMapping(value="/new", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> insert(@RequestBody BankaCreation creation) {
		Optional<Banka> bankaOpt = repo.findById(creation.getPib());
		if(bankaOpt.isPresent()) {
			return ResponseEntity.badRequest().body("Vec postoji banka sa PIBom " + creation.getPib());
		}
		bankaOpt = repo.findOneBySwift(creation.getSwift());
		if(bankaOpt.isPresent()) {
			return ResponseEntity.badRequest().body("Vec postoji banka sa SWIFT-om " + creation.getSwift());
		}
		Banka banka = new Banka();
		banka = mapper.map(creation, Banka.class);
		banka.setRacun(null);
		banka.setPIB(creation.getPib());
		return ResponseEntity.ok(mapper.map(repo.save(banka), BankaView.class));
	}

	
	@GetMapping(value="/izvod", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody void getIzvod(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("pocetak") @DateTimeFormat(pattern="yyyy-MM-dd") Date pocetak,
			@RequestParam("kraj")  @DateTimeFormat(pattern="yyyy-MM-dd") Date kraj,
			@RequestParam("brojRacuna") String brojRacuna) throws IOException {
		List<AnalitikaIzvoda> analitike = analitikaIzvodaRepo.findAllByDateAndAccount(pocetak, kraj, brojRacuna);

		IzvodView izvod = new IzvodView();
		izvod.setBrojRacuna(brojRacuna);
		List<AnalitikaIzvodaView> analitikeView = analitike.stream().map(a -> mapper.map(a, AnalitikaIzvodaView.class)).collect(Collectors.toCollection(ArrayList::new));
		izvod.setAnalitike(analitikeView);
		XmlMapper xmlMapper = new XmlMapper();
		byte[] file = xmlMapper.writeValueAsBytes(izvod);
		response.reset();
	    response.setContentType("text/xml");
	    response.setHeader("Content-Disposition", "attachment");
		response.getOutputStream().write(file);
	}
}
