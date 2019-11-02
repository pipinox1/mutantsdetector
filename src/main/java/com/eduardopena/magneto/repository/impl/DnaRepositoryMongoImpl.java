package com.eduardopena.magneto.repository.impl;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.eduardopena.magneto.dto.DnaStatsDTO;
import com.eduardopena.magneto.entity.Dna;
import com.eduardopena.magneto.repository.DnaRepository;

@Repository
public class DnaRepositoryMongoImpl implements DnaRepository {

	@Autowired
	private MongoOperations opps;

	@Override
	public void save(Dna dna) {
		opps.save(dna);
	}

	@Override
	public DnaStatsDTO getStats() {
		DnaStatsDTO dnaStats = new DnaStatsDTO();
		Query q = new Query();
		q.addCriteria(Criteria.where("isMutant").is(true));
		dnaStats.setTotalMutants(opps.find(q, Dna.class).size());
		q = new Query();
		q.addCriteria(Criteria.where("isMutant").is(false));
		dnaStats.setTotalHumans(opps.find(q, Dna.class).size());
		dnaStats.setRatio(dnaStats.getTotalHumans() == 0 ? 0f : Float.parseFloat(new DecimalFormat("##.##").format(dnaStats.getTotalMutants()/dnaStats.getTotalHumans())));
		return dnaStats;
	}

	@Override
	public boolean existDna(String dna) {
		Query q = new Query();
		q.addCriteria(Criteria.where("dna").is(dna));
		if (opps.find(q, Dna.class).size() >= 1) {
			return true;
		} else {
			return false;
		}
	}

}
