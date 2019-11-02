package com.eduardopena.magneto.service;

import com.eduardopena.magneto.dto.DnaDTO;
import com.eduardopena.magneto.dto.DnaStatsDTO;

public interface DnaService {
	
	/**
	 * Save the DNA evaluation
	 * @param dna
	 * @return boolean
	 */
	public boolean save(DnaDTO dna);
	
	/**
	 * Check if the given DNA corresponds to the mutant
	 * @param dna
	 * @return boolean
	 */
	public boolean isMutant(String[] dna);
	
	/**
	 * Get States about dna
	 * @return DnaStatsDTO
	 */
	public DnaStatsDTO getStats();

}
