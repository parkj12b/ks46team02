package ks46team02.eco.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.eco.dto.EcoImprovementDto;

@Mapper
public interface EcoImprovementMapper {
	
	/* 환경 개선량 조회 */
	public List<EcoImprovementDto> getEcoList();
}
