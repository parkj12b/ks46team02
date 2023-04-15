package ks46team02.farm.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.farm.dto.MMRegInfoMentee;
import ks46team02.farm.dto.MMRegInfoMentor;

@Mapper
public interface MentorMenteeMapper {

	public MMRegInfoMentee getMMRegStatMentee(String companyCode);
	public MMRegInfoMentor getMMRegStatMentor(String companyCode);

	public int getMMRegType(String companyCode);

	
}
