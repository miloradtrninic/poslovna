package com.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.poslovna.beans.PravnaLica;

public interface PravnaLicaRepo extends PagingAndSortingRepository<PravnaLica, String> {

}
