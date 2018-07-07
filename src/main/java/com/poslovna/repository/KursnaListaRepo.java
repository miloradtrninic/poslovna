package com.poslovna.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.KursnaLista;
import com.poslovna.beans.QKursnaLista;

public interface KursnaListaRepo extends PagingAndSortingRepository<KursnaLista, Long>, QuerydslPredicateExecutor<KursnaLista>, QuerydslBinderCustomizer<QKursnaLista>{
	@Override
    default public void customize(QuerydslBindings bindings, QKursnaLista root) {
    
    }
}
