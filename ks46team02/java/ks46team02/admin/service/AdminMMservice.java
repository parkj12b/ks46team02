package ks46team02.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ks46team02.admin.mapper.MMmapper;
import ks46team02.farm.dto.MMRegInfoMentee;
import ks46team02.farm.dto.MMRegInfoMentor;

@Service
public class AdminMMservice {

	private final MMmapper mMMapper;
	
	
	private static final Logger log = LoggerFactory.getLogger(AdminMMservice.class);

	
	public AdminMMservice(MMmapper mMMapper) {
		this.mMMapper = mMMapper;
	}
	
	public List<MMRegInfoMentor> getMentorRegList(String status) {

		List<MMRegInfoMentor> mentorRegList = mMMapper.getMentorRegList(status);
		log.info("{}", mentorRegList);
		return mentorRegList;
	}

	public List<MMRegInfoMentee> getMenteeRegList(String status) {
		List<MMRegInfoMentee> menteeRegList = mMMapper.getMenteeRegList(status);
		return menteeRegList;
	}
	
}
