package com.eduardopena.magneto.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardopena.magneto.configuration.BadRequestException;
import com.eduardopena.magneto.dto.DnaDTO;
import com.eduardopena.magneto.dto.DnaStatsDTO;
import com.eduardopena.magneto.entity.Dna;
import com.eduardopena.magneto.repository.DnaRepository;
import com.eduardopena.magneto.service.DnaService;

/**
 * Contains the logic to work with DNA
 * 
 * @author edpena
 *
 */
@Service
public class DnaServiceImpl implements DnaService {

	@Autowired
	private DnaRepository dnaRepository;

	@Override
	public boolean save(DnaDTO dnaDTO) {
		if (!validate(dnaDTO)) {
			throw new BadRequestException("Error with the current DNA");
		}
		boolean mutant = isMutant(dnaDTO.getDna());
		Dna dnaEntity = new Dna();
		dnaEntity.setDna(Arrays.stream(dnaDTO.getDna()).collect(Collectors.joining(",")));
		dnaEntity.setMutant(mutant);
		dnaRepository.save(dnaEntity);
		return mutant;

	}
	int contadoVertical;
	int contadorHorizontal;
	public boolean isMutant(String[] dna) {
		int mutantCounter = 0;
		int allowedLengh = dna.length - 4;
		System.out.println(allowedLengh);

		for (int i = 0; i < dna.length; i++) {
			for (int j = 0; j < dna.length; j++) {
				if (j > allowedLengh) {
					break;
				}
				if (dna[j].toCharArray()[i] == dna[j + 1].toCharArray()[i]) {
					j++;
					if (dna[j].toCharArray()[i] == dna[j + 1].toCharArray()[i]) {
						j++;
						if (dna[j].toCharArray()[i] == dna[j + 1].toCharArray()[i]) {
							j++;
							System.out.println("Encontre Mutante Vertical" +i + " " + j);
							mutantCounter++;
							if (mutantCounter > 1)return true;
						} else {
							continue;
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			}
		}
		
		for (int i = 0; i < dna.length; i++) {
			for (int j = 0; j < dna.length; j++) {
				if (j > allowedLengh) {
					break;
				}
				if (dna[i].toCharArray()[j] == dna[i].toCharArray()[j + 1]) {
					j++;
					if (dna[i].toCharArray()[j] == dna[i].toCharArray()[j + 1]) {
						j++;
						if (dna[i].toCharArray()[j] == dna[i].toCharArray()[j + 1]) {
							j++;
							System.out.println("Encontre Mutante Horizontal" +i + " " + j);
							mutantCounter++;
							if (mutantCounter > 1) return true;
						} else {
							continue;
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			}
		}


		for (int i = 0; i < dna.length; i++) {
			for (int j = 0; j < dna.length; j++) {
				if (j <= allowedLengh) {
					if (i <= allowedLengh) {
						if (dna[i].toCharArray()[j] == dna[i + 1].toCharArray()[j + 1]
								&& dna[i].toCharArray()[j] == dna[i + 2].toCharArray()[j + 2]
								&& dna[i].toCharArray()[j] == dna[i + 3].toCharArray()[j + 3]) {
							mutantCounter++;
							if (mutantCounter > 1) return true;
	
						}
					}
					if (i >= 3) {
						if (dna[i].toCharArray()[j]== dna[i - 1].toCharArray()[j + 1]
								&& dna[i].toCharArray()[j] == dna[i - 2].toCharArray()[j + 2]
								&& dna[i].toCharArray()[j] == dna[i - 3].toCharArray()[j + 3]) {
							mutantCounter++;
							if (mutantCounter > 1) return true;
						}
					}

				}

			}
		}
		return mutantCounter > 1 ? true : false;
	}

	public boolean validate(DnaDTO dna) {
		List<String> dnaList = Arrays.asList(dna.getDna());
		if (dnaList.stream().allMatch(entry -> entry.length() == dna.getDna().length)
				&& dnaList.stream().allMatch((entry -> {
					return entry.matches("^[ACGT]+$");
				})) && dna.getDna().length >= 4) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public DnaStatsDTO getStats() {
		return dnaRepository.getStats();
	}

}
