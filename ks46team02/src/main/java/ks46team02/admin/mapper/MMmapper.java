package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.farm.dto.MMRegInfoMentee;
import ks46team02.farm.dto.MMRegInfoMentor;

@Mapper
public interface MMmapper {

	public List<MMRegInfoMentor> getMentorRegList(String status);

	public List<MMRegInfoMentee> getMenteeRegList(String status);
	
}
