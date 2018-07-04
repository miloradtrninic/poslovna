package com.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.Poruka;

public interface PorukaRepo extends PagingAndSortingRepository<Poruka, Long>{

}
