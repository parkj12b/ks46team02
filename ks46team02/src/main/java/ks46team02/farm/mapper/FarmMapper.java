package ks46team02.farm.mapper;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks46team02.farm.dto.Cage;
import ks46team02.farm.dto.Cycle;
import ks46team02.farm.dto.FarmInfo;
import ks46team02.farm.dto.FarmStatus;
import ks46team02.farm.dto.Feed;
import ks46team02.farm.dto.Production;

@Mapper
public interface FarmMapper {
	
	// 전체 사육장 케이지 조회 및 검색 //
	public List<Cage> getSearchCageList(String companyCode,String searchKey ,String searchValue);
	// 한 사육장 생타 조회 //
	public List<FarmStatus> getFarmStatusList(String farmCode);
	// 전체 사육장 리스트 //
	public List<FarmInfo> getFarmList(String companyCode);
	// 한 사육장 정보 조회 //
	public FarmInfo getFarmInfoByCode(String farmCode);
	// 한 사육장 생산량 조회 //
	public List<Production> getProductionByCode(String farmCode);
	// 전체 사육장 생산량 조회 //
	public List<Production> getProductionList(String companyCode);
	// 한사육장 싸이클 조회 및 검색 //
	public List<Cycle> getSearchCycle(String farmCode, String searchKey, String searchValue, String fromDate, String toDate);
	// 사육장 검색 //
	public List<Production> getSearchProduction(String companyCode, String searchKey, String searchValue, String fromDate, String toDate);
	// 하나의 사이클 급여먹이 조회 //
	public List<Feed> getFeedListByCycleCode(String cycleCode);
	
}
