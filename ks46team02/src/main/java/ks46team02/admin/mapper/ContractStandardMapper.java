package ks46team02.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.admin.dto.ContractStandard;
import ks46team02.admin.dto.ContractStandard;


@Mapper
public interface ContractStandardMapper {
	/* 승인 기준 조회*/
	public List<ContractStandard> getContractStandardList();

}
