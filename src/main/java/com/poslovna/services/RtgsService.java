package com.poslovna.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.poslovna.beans.RTGSNalog;
import com.poslovna.dto.RtgsCreation;



public interface RtgsService {
	
	boolean proccessRtgs(RtgsCreation rtgsNalog);
}
