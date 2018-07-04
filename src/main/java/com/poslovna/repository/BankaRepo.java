package com.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.Banka;

public interface BankaRepo extends PagingAndSortingRepository<Banka, String>{
	Banka findOneBySwift(String swift);
}
