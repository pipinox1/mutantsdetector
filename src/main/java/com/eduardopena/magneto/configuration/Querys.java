package com.eduardopena.magneto.configuration;

public class Querys {
	
	public final static String QUERY_STATS= "select (select count(dna) from dna where is_mutant = false) as totalHuman,(select count(dna) from dna where is_mutant = true) as totalMutant;";
	
}
