package com.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.RacunPravnogLica;

public interface RacunPravnogLicaRepo extends PagingAndSortingRepository<RacunPravnogLica, String>{
	RacunPravnogLica findOneByBrojRacuna(String brojRacuna);
}
