package com.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.VrstaPlacanja;

public interface VrstaPlacanjaRepo extends PagingAndSortingRepository<VrstaPlacanja, Long> {

}
