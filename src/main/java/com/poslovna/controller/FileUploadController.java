package com.poslovna.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.poslovna.dto.ClearingCreation;
import com.poslovna.dto.RtgsCreation;
import com.poslovna.exceptions.NelikvidanException;
import com.poslovna.exceptions.NepostojecaBankaException;
import com.poslovna.exceptions.NepostojeciRacunException;
import com.poslovna.exceptions.NepoznataValutaExceptio;
import com.poslovna.exceptions.PojedinacnoPlacanjeException;
import com.poslovna.services.ClearingSerivce;
import com.poslovna.services.RtgsService;

@RestController
@RequestMapping(value = "/upload")
public class FileUploadController {
	@Autowired
	private RtgsService rtgsService;
	@Autowired
	private ClearingSerivce clearingService;
	

	@PostMapping(value = "/rtgs")
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		File f = null;
		try {
			System.out.println("Uploaded file name: " + file.getOriginalFilename());
			f= Files.createTempFile("temp", file.getOriginalFilename()).toFile();
			file.transferTo(f);
		    XmlMapper xmlMapper = new XmlMapper();
		    String rtgsXml = inputStreamToString(new FileInputStream(f));
		    RtgsCreation rtgsCreation = xmlMapper.readValue(rtgsXml, RtgsCreation.class);
		    rtgsService.proccessRtgs(rtgsCreation);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NelikvidanException | PojedinacnoPlacanjeException | NepostojeciRacunException
				| NepostojecaBankaException | NepoznataValutaExceptio e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/clearing")
	public ResponseEntity<?> clearingFileUpload(@RequestParam("file") MultipartFile file) {
		File f = null;
			try {
				f= Files.createTempFile("temp", file.getOriginalFilename()).toFile();
				file.transferTo(f);
			    XmlMapper xmlMapper = new XmlMapper();
			    String rtgsXml = inputStreamToString(new FileInputStream(f));
			    xmlMapper.setSerializationInclusion(Include.NON_EMPTY);
			    ClearingCreation clearingCreation = xmlMapper.readValue(rtgsXml, ClearingCreation.class);
				clearingService.processClearing(clearingCreation);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (NelikvidanException | PojedinacnoPlacanjeException | NepostojeciRacunException
					| NepostojecaBankaException | NepoznataValutaExceptio e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			} catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	//Utility method
	public static String inputStreamToString(InputStream is) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    String line;
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    while ((line = br.readLine()) != null) {
	        sb.append(line);
	    }
	    br.close();
	    return sb.toString();
	}
}
