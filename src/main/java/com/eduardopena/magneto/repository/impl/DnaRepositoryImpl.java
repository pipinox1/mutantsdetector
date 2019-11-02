package com.eduardopena.magneto.repository.impl;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eduardopena.magneto.configuration.Querys;
import com.eduardopena.magneto.dto.DnaStatsDTO;
import com.eduardopena.magneto.entity.Dna;
import com.eduardopena.magneto.repository.DnaRepository;

@Repository
@Transactional
public class DnaRepositoryImpl implements DnaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Dna dna) {
		entityManager.persist(dna);
	}


	@SuppressWarnings("unchecked")
	@Override
	public DnaStatsDTO getStats() {

		String sqlFragment = Querys.QUERY_STATS;
		Query query = entityManager.createNativeQuery(sqlFragment);
		 List<Object[]> response = query.getResultList();
		DnaStatsDTO dnaStats = new DnaStatsDTO();
		if(response.get(0) != null) {
			dnaStats.setTotalHumans(((BigInteger)response.get(0)[0]).intValue());
			dnaStats.setTotalMutants(((BigInteger)response.get(0)[1]).intValue());
			dnaStats.setRatio(dnaStats.getTotalMutants() == 0 ? 0f : Float.parseFloat(new DecimalFormat("##.##").format(((BigInteger)response.get(0)[1]).floatValue()/((BigInteger)response.get(0)[0]).floatValue())));
		}
	     return dnaStats;
	}

}
