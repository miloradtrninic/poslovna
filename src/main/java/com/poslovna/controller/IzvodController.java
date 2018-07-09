package com.poslovna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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
		response.getOutputStream().write(file);
	}
}
