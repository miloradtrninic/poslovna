package com.poslovna.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.beans.ObracunskiRacunBanke;
import com.poslovna.beans.Ukidanje;
import com.poslovna.beans.Valuta;
import com.poslovna.dto.ObracunskiRacunCreation;
import com.poslovna.dto.ObracunskiRacunView;
import com.poslovna.dto.UkidanjeCreation;
import com.poslovna.repository.DnevnoStanjeRepo;
import com.poslovna.repository.ObracunskiRacunBankeRepo;
import com.poslovna.repository.UkidanjeRepo;
import com.poslovna.repository.ValutaRepo;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;


@RestController
@RequestMapping(value = "/obracunskiRacun")
public class ObracunskiRacunController {

	
	@Autowired
	private ObracunskiRacunBankeRepo orbRep;
	
	@Autowired 
	private ValutaRepo vatRep;
	
	@Autowired
	private UkidanjeRepo ukRep;
	
	@Autowired
	private DnevnoStanjeRepo dnRep;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
		
		
		
		return ResponseEntity.ok(modelMapper.map(orbRep.save(orb), ObracunskiRacunView.class));
	}
	
	
	@GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ObracunskiRacunBanke>> getAll(){
		
		List<ObracunskiRacunBanke> page = orbRep.findByVazeci(true);
	
		return new ResponseEntity<List<ObracunskiRacunBanke>>(page,HttpStatus.OK);
	}
	
	

	@PostMapping(value="/transfer", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
			   						consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> ukidanjeObracunskoRacunaa(@RequestBody UkidanjeCreation ukidanje){
		
		ObracunskiRacunBanke orbId = orbRep.findByBrojRacuna(ukidanje.getNaRacun());
		ObracunskiRacunBanke orbBrId = orbRep.findByBrojRacuna(ukidanje.getUkidaSe());
			
		int velicina = orbId.getDnevnaStanja().size() - 1; //prima
		int velicina1 = orbBrId.getDnevnaStanja().size() - 1; //salje
		orbId.getDnevnaStanja().get(velicina).setDatumPromene(new Date());
		orbId.getDnevnaStanja().get(velicina).setPrethodnoStanje(orbId.getDnevnaStanja().get(velicina).getNovoStanje());
		orbId.getDnevnaStanja().get(velicina).setNovoStanje(orbId.getDnevnaStanja().get(velicina).getNovoStanje() + orbBrId.getDnevnaStanja().get(velicina1).getNovoStanje());
		orbId.getDnevnaStanja().get(velicina).setPromeneUKorist(orbBrId.getDnevnaStanja().get(velicina1).getNovoStanje());
		orbId.getDnevnaStanja().get(velicina).setPromeneNaTeret(0.0);
		orbBrId.getDnevnaStanja().get(velicina1).setDatumPromene(new Date());
		orbBrId.getDnevnaStanja().get(velicina1).setPrethodnoStanje(orbBrId.getDnevnaStanja().get(velicina1).getNovoStanje());
		orbBrId.getDnevnaStanja().get(velicina1).setNovoStanje(0.0);
		orbBrId.getDnevnaStanja().get(velicina1).setPromeneUKorist(0.0);
		orbBrId.getDnevnaStanja().get(velicina1).setPromeneNaTeret(orbBrId.getDnevnaStanja().get(velicina1).getPrethodnoStanje());
		orbBrId.setVazeci(false);
		
		
		dnRep.save(orbId.getDnevnaStanja().get(velicina));
		dnRep.save(orbBrId.getDnevnaStanja().get(velicina1));
		
		orbRep.save(orbId);
		orbRep.save(orbBrId);
		
		Ukidanje u = new Ukidanje();
		u.setDatumUkidanja(new Date());
		u.setUkidaSe(orbBrId);
		u.setNaRacun(orbId);
		ukRep.save(u);
		
		return new ResponseEntity<String>("lala",HttpStatus.OK);
	}
	
	
	@GetMapping(value="/izvestaj1", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<String> izvestaj1() {
			try {
				 String status = "P";
				Connection conn;
				conn =
					       DriverManager.getConnection("jdbc:mysql://localhost/narodna_banka?" +
                                   "user=root&password=root");
				HashMap map = new HashMap();
				map.put("STATUS", status);
	            JasperReport jasReport = (JasperReport) JRLoader.loadObjectFromFile("C:\\Users\\Danijela\\JaspersoftWorkspace\\MyReports\\Izvjestaj1.jasper");
	            JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, map, conn);
	            File pdf = File.createTempFile("output3", ".pdf");
				JasperExportManager.exportReportToPdfStream(jasPrint, new FileOutputStream(pdf));
				System.out.println("Temp file : " + pdf.getAbsolutePath());
				
			}catch (Exception ex) {
				ex.printStackTrace();
				}
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}
	
	
	@GetMapping(value="/izvestaj2", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> izvestaj2() {
		try {
			 String status = "P";
			Connection conn;
			conn =
				       DriverManager.getConnection("jdbc:mysql://localhost/narodna_banka?" +
                               "user=root&password=root");
			HashMap map = new HashMap();
			map.put("STATUS", status);
            JasperReport jasReport = (JasperReport) JRLoader.loadObjectFromFile("C:\\Users\\Danijela\\JaspersoftWorkspace\\MyReports\\Izvjestaj2.jasper");
            JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, map, conn);
            File pdf = File.createTempFile("output3", ".pdf");
			JasperExportManager.exportReportToPdfStream(jasPrint, new FileOutputStream(pdf));
			System.out.println("Temp file : " + pdf.getAbsolutePath());
			
		}catch (Exception ex) {
			ex.printStackTrace();
			}
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
	 }
