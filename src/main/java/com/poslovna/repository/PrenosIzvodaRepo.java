package com.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.PrenosIzvoda;

public interface PrenosIzvodaRepo extends PagingAndSortingRepository<PrenosIzvoda, Long> {

}
