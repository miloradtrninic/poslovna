package com.poslovna.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.poslovna.beans.AnalitikaIzvoda;

public interface AnalitikaIzvodaRepo extends PagingAndSortingRepository<AnalitikaIzvoda, Long>{
	
	@Query(value="select * from analitika_izvoda ai join dnevno_stanje ds on ai.dnevno_stanje_duznik_id = ds.id "
			+"join dnevno_stanje ds2 on ai.dnevno_stanje_poverilac_id = ds2.id "
			+"where (ds.racun_pravnog_lica_broj_racuna like :accountNumber or ds2.racun_pravnog_lica_broj_racuna like :accountNumber) "
			+"and ds.datum_promene between :start and :end "
			+"and ds2.datum_promene between :start and :end", nativeQuery=true)
	List<AnalitikaIzvoda> findAllByDateAndAccount(@Param("start") Date start,@Param("end")  Date end,@Param("accountNumber") String accountNumber);
}
