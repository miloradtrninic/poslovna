package com.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.PojedinacnoPlacanje;

public interface PojedinacnoPlacanjeRepo extends PagingAndSortingRepository<PojedinacnoPlacanje, Long> {

}
