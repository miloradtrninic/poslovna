package com.poslovna.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.DnevnoStanje;
import com.poslovna.beans.ObracunskiRacunBanke;

public interface DnevnoStanjeRepo extends PagingAndSortingRepository<DnevnoStanje, Long>{
	Optional<DnevnoStanje> findOneByRacunPravnogLicaAndDatumPromene(ObracunskiRacunBanke racunPravnogLica, Date datumPromene);
}
