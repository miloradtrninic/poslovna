package com.poslovna.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.QVrstaPlacanja;
import com.poslovna.beans.VrstaPlacanja;

public interface VrstaPlacanjaRepo extends PagingAndSortingRepository<VrstaPlacanja, Long>, QuerydslPredicateExecutor<VrstaPlacanja>, QuerydslBinderCustomizer<QVrstaPlacanja>{
	@Override
    default public void customize(QuerydslBindings bindings, QVrstaPlacanja root) {
    
    }
}
