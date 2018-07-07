package com.poslovna.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.NaseljenoMesto;
import com.poslovna.beans.QNaseljenoMesto;

public interface NaseljenoMestoRepo extends PagingAndSortingRepository<NaseljenoMesto, Long>, QuerydslPredicateExecutor<NaseljenoMesto>, QuerydslBinderCustomizer<QNaseljenoMesto>{
	@Override
    default public void customize(QuerydslBindings bindings, QNaseljenoMesto root) {
    
    }
}
