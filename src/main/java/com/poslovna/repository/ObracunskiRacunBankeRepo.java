package com.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.ObracunskiRacunBanke;

public interface ObracunskiRacunBankeRepo extends PagingAndSortingRepository<ObracunskiRacunBanke, String>{
	ObracunskiRacunBanke findOneByBrojRacuna(String brojRacuna);
}
