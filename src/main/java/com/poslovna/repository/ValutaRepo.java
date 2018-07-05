package com.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.Valuta;

public interface ValutaRepo extends PagingAndSortingRepository<Valuta, String>{

}
