package com.poslovna.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.Drzava;
import com.poslovna.beans.QDrzava;

public interface DrzavaRepo extends PagingAndSortingRepository<Drzava, String>, QuerydslPredicateExecutor<Drzava>, QuerydslBinderCustomizer<QDrzava>{
	@Override
    default public void customize(QuerydslBindings bindings, QDrzava root) {
    
    }
}
