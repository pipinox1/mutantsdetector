package com.eduardopena.magneto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eduardopena.magneto.dto.DnaDTO;
import com.eduardopena.magneto.dto.DnaStatsDTO;
import com.eduardopena.magneto.service.DnaService;

@RestController
public class MutantController {
	
	@Autowired
	private DnaService dnaService;
	
	@PostMapping(value = "/mutant")
	public ResponseEntity<Object> isMutant(@RequestBody DnaDTO dna) {
		if(dnaService.save(dna)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping(value = "/stats")
	public ResponseEntity<DnaStatsDTO> getStats() {
			return new ResponseEntity<>(dnaService.getStats(),HttpStatus.OK);
	}


}
