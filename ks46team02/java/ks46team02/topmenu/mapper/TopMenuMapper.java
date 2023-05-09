package ks46team02.topmenu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.customerservice.dto.QuestionTypeDto;

@Mapper
public interface TopMenuMapper {
	
	public List<QuestionTypeDto> getTopMenuCustomerServiceList();
}
