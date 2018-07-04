package com.poslovna.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.poslovna.beans.RTGSNalog;
import com.poslovna.dto.RtgsCreation;

@Service
@Transactional
public interface RtgsService {
	
	public boolean proccessRtgs(RtgsCreation rtgsNalog);
}
