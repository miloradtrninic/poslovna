package com.poslovna.repository;

import java.util.Optional;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.Banka;
import com.poslovna.beans.QBanka;

public interface BankaRepo extends PagingAndSortingRepository<Banka, String>, QuerydslPredicateExecutor<Banka>, QuerydslBinderCustomizer<QBanka>{
	@Override
    default public void customize(QuerydslBindings bindings, QBanka root) {
    
    }
	Optional<Banka> findOneBySwift(String swift);
}
