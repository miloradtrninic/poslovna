package com.poslovna.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.poslovna.beans.RTGSNalog;
import com.poslovna.dto.RtgsCreation;
import com.poslovna.exceptions.NepoznataValutaExceptio;



public interface RtgsService {
	
	boolean proccessRtgs(RtgsCreation rtgsNalog) throws NepoznataValutaExceptio;
}
