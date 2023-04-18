package ks46team02.farm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ks46team02.farm.dto.MMContractInfo;
import ks46team02.farm.mapper.MentorMenteeMapper;

@Service
public class MentorMenteeService {

	private final MentorMenteeMapper mentorMenteeMapper;
	
	public MentorMenteeService(MentorMenteeMapper mentorMenteeMapper) {
		this.mentorMenteeMapper = mentorMenteeMapper;
	}
	
	public Map<String, Object> getMentorMenteeRegisterStatus(String companyCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		int mmRegType = mentorMenteeMapper.getMMRegType(companyCode);
		Object mmRegInfo;
		if(mmRegType == 1) {
			mmRegInfo = mentorMenteeMapper.getMMRegStatMentor(companyCode);			
		} else if(mmRegType == 2) {
			mmRegInfo = mentorMenteeMapper.getMMRegStatMentee(companyCode);
		} else {
			return null;
		}
		map.put("mmRegInfo", mmRegInfo);
		map.put("mmRegType", mmRegType);
		
		return map;
		
	}
	
	public Integer getMMRegType(String companyCode) {
		return mentorMenteeMapper.getMMRegType(companyCode);
	}

	public List<MMContractInfo> getMMContractList(String companyCode) {

		List<MMContractInfo> mmContractInfo = mentorMenteeMapper.getMMContractInfo(companyCode);
		
		return mmContractInfo;
	}
	
	
	
}
