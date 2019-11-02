package com.eduardopena.magneto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eduardopena.magneto.configuration.BadRequestException;
import com.eduardopena.magneto.dto.DnaDTO;
import com.eduardopena.magneto.dto.DnaStatsDTO;
import com.eduardopena.magneto.service.DnaService;

@SpringBootTest
class DnaServiceIntegrationTests {
	
	@Autowired
	private DnaService dnaService;
	
	DnaDTO dto;
	DnaStatsDTO dnaStats;
	
	@BeforeEach
	void contextLoads() {
		dto = new DnaDTO();
	}
	
	@Test
	void testIsMutantWithCorrectData() {
		assertTrue(dnaService.isMutant(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}));
	}
	
	@Test
	void testIsMutantWithWrongData() {
		assertFalse(dnaService.isMutant(new String[]{"ATGCGA","CCGTGC","TTATGT","AGAAGG","CCCATA","TCACTG"}));
	}
	
	@Test
	void testIsMutantWithWrongArrayLenght() {
		dto.setDna(new String[]{"ATGCGA","CCGTGC","TTATGT","AGAAGG"});
		assertThrows(BadRequestException.class, () -> {
			dnaService.save(dto);
		  });	

	}
	
	@Test
	void testIsMutantWithWrongtWords() {
		dto.setDna(new String[]{"ATGZZA","CCGTGC","TTATLT","AGAAGG","CCCATA","TCACTG"});
		assertThrows(BadRequestException.class, () -> {
			dnaService.save(dto);
		  });	
	
	}
	
	@Test
	void testIsMutantWithShortDna() {
		dto.setDna(new String[]{"ATG","CCC","TTT"});
		assertThrows(BadRequestException.class, () -> {
			dnaService.save(dto);
		  });	
	
	}


}
