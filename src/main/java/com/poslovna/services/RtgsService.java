package com.poslovna.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.poslovna.beans.RTGSNalog;
import com.poslovna.dto.RtgsCreation;
import com.poslovna.exceptions.NelikvidanException;
import com.poslovna.exceptions.NepostojecaBankaException;
import com.poslovna.exceptions.NepoznataValutaExceptio;
import com.poslovna.exceptions.PojedinacnoPlacanjeException;



public interface RtgsService {
	
	void proccessRtgs(RtgsCreation rtgsNalog) throws NepostojecaBankaException, PojedinacnoPlacanjeException, NelikvidanException, NepoznataValutaExceptio;
}
