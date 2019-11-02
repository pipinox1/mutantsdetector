package com.eduardopena.magneto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.eduardopena.magneto.dto.DnaStatsDTO;
import com.eduardopena.magneto.repository.DnaRepository;
import com.eduardopena.magneto.service.impl.DnaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DnaServiceMockedTest {

	@Mock
	private DnaRepository dnaRepository;;
	
	@InjectMocks
	private DnaServiceImpl dnaService;
	
	DnaStatsDTO dnaStats;

	@Test
	void testGetStatsMockedZeroValue() {
		dnaStats = new DnaStatsDTO(0,0,0f);
		when(dnaRepository.getStats()).thenReturn(dnaStats);
		DnaStatsDTO dnaStatsRetrieved = dnaService.getStats();
		assertEquals(0, dnaStatsRetrieved.getRatio());
		assertEquals(0, dnaStatsRetrieved.getTotalHumans());
		assertEquals(0, dnaStatsRetrieved.getTotalMutants());
	}
	
	@Test
	void testGetStatsMocked() {
		dnaStats = new DnaStatsDTO(40,100,0.4f);
		when(dnaRepository.getStats()).thenReturn(dnaStats);
		DnaStatsDTO dnaStatsRetrieved = dnaService.getStats();
		assertEquals(0.4f, dnaStatsRetrieved.getRatio());
		assertEquals(100, dnaStatsRetrieved.getTotalHumans());
		assertEquals(40, dnaStatsRetrieved.getTotalMutants());
	}
	
	
	
}
