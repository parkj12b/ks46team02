package ks46team02.contract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ks46team02.contract.dto.TerminateReasonDTO;
import ks46team02.contract.mapper.TerminateReasonMapper;

@Service
public class TerminateReasonService {
	
	@Autowired
	private TerminateReasonMapper terminatereasonmapper;
	
	public List<TerminateReasonDTO>getTerminateReasonList() {
		List<TerminateReasonDTO> terminateReasonList = terminatereasonmapper.getTerminateReasonList();
		return terminateReasonList;
	}
}
