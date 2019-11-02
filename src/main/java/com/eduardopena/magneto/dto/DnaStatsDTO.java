package com.eduardopena.magneto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DnaStatsDTO {
	
	@JsonProperty("count_mutant_dna")
	private Integer totalMutants;
	@JsonProperty("count_human_dna")
	private Integer  totalHumans;
	private float ratio;
	
	public DnaStatsDTO() {
	}
	
	public DnaStatsDTO(Integer totalMutants, Integer totalHumans, float ratio) {
		this.totalMutants = totalMutants;
		this.totalHumans = totalHumans;
		this.ratio = ratio;
	}
	
	public Integer getTotalMutants() {
		return totalMutants;
	}
	public void setTotalMutants(Integer totalMutants) {
		this.totalMutants = totalMutants;
	}
	public Integer getTotalHumans() {
		return totalHumans;
	}
	public void setTotalHumans(Integer totalHumans) {
		this.totalHumans = totalHumans;
	}
	public float getRatio() {
		return ratio;
	}
	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	
	

}
