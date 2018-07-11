package com.poslovna.repository;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.QValuta;
import com.poslovna.beans.Valuta;

public interface ValutaRepo extends PagingAndSortingRepository<Valuta, String>, QuerydslPredicateExecutor<Valuta>, QuerydslBinderCustomizer<QValuta>{
	@Override
    default public void customize(QuerydslBindings bindings, QValuta root) {
    
    }
	
	List<Valuta> findAll();
	Valuta findBySifra(String sifra);
}
