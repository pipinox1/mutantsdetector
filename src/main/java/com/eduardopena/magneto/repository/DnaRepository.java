package com.eduardopena.magneto.repository;

import com.eduardopena.magneto.dto.DnaStatsDTO;
import com.eduardopena.magneto.entity.Dna;

public interface DnaRepository {

	public void save(Dna dna) ;
	
	public DnaStatsDTO getStats();
	
	public boolean existDna(String dna);
}
