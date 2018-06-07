package com.poslovna.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/upload")
public class FileUploadController {

	@PostMapping(value = "/rtgs")
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			System.out.println("Uploaded file name: " + file.getOriginalFilename());
 
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			//return ResponseEntity.status(HttpStatus.OK).body(message);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			//return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/get")
	public ResponseEntity<String> getString() {
		return new ResponseEntity<String>( "mrs",HttpStatus.OK);
	}
}
