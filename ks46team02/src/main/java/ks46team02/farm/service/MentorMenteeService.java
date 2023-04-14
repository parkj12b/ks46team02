package ks46team02.farm.service;

import org.springframework.stereotype.Service;

import ks46team02.farm.dto.MMRegInfo;
import ks46team02.farm.mapper.MentorMenteeMapper;

@Service
public class MentorMenteeService {

	private final MentorMenteeMapper mentorMenteeMapper;
	
	public MentorMenteeService(MentorMenteeMapper mentorMenteeMapper) {
		this.mentorMenteeMapper = mentorMenteeMapper;
	}
	
	public MMRegInfo getMentorMenteeRegisterStatus(String companyCode) {
		
		MMRegInfo mmRegInfo = mentorMenteeMapper.getMentorMenteeRegisterStatus(companyCode);
		
		return mmRegInfo;
	}
	
	
	
}
