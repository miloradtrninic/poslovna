package com.poslovna.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.Banka;

public interface BankaRepo extends PagingAndSortingRepository<Banka, String>{
	Optional<Banka> findOneBySwift(String swift);
}
