package com.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.KursnaLista;

public interface KursnaListaRepo extends PagingAndSortingRepository<KursnaLista, Long>{

}
