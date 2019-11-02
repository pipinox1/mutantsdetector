package com.eduardopena.magneto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.eduardopena.magneto.dto.DnaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class MutantControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	ObjectMapper objectmapper;

	DnaDTO dto;


	@BeforeEach
	void contextLoads() {
		dto = new DnaDTO();
	}

	@Test
	public void testPostMutantDnaofMutant() throws Exception {
		dto.setDna(new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" });
		mvc.perform(
				post("/mutant").content(objectmapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testPostMutantDnaofHuman() throws Exception {
		dto.setDna(new String[] { "ATGCGA", "CCGTAC", "TTATGT", "AGAAGG", "CCCATA", "TCACTG" });
		mvc.perform(
				post("/mutant").content(objectmapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	@Test
	public void testPostMutantDnaIncorrectWords() throws Exception {
		dto.setDna(new String[] { "ATGCGA", "CCVBGC", "TTATGT", "AGAAGG", "CCCATA", "TCACTG" });
		mvc.perform(
				post("/mutant").content(objectmapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testPostMutantDnaIncocorrectArrayLengt() throws Exception {
		dto.setDna(new String[] { "ATGCGA", "CCVBGC", "CCCATA", "TCACTG" });
		mvc.perform(
				post("/mutant").content(objectmapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
