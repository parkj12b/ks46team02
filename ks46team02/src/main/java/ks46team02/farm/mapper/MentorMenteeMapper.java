package ks46team02.farm.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.farm.dto.MMRegInfo;

@Mapper
public interface MentorMenteeMapper {

	MMRegInfo getMentorMenteeRegisterStatus(String companyCode);

	
}
