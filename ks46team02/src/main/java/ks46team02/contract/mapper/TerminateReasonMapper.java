package ks46team02.contract.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.contract.dto.TerminateReasonDTO;

@Mapper
public interface TerminateReasonMapper {

	/* 계약 파기 사유유형 조회 */
	public List<TerminateReasonDTO> getTerminateReasonList();
}
