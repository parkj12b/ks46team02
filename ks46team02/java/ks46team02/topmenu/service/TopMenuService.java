package ks46team02.topmenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ks46team02.customerservice.dto.QuestionTypeDto;
import ks46team02.topmenu.mapper.TopMenuMapper;

@Service
public class TopMenuService {

	@Autowired
	private TopMenuMapper topmenumapper;
	
	public List<QuestionTypeDto> getTopMenuCustomerServiceList() {
		List<QuestionTypeDto> topMenuList = topmenumapper.getTopMenuCustomerServiceList();
		
		return topMenuList;
	}
}
