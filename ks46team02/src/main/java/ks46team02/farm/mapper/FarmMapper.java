package ks46team02.farm.mapper;


import java.util.HashMap;
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

    // 먹이 급여 그래프 //
    public  List<Feed> getFeedGraph(String cycleCode);
    // 케이지 수정 //
    public int modifyCage(Cage cage);
    // 싸이클 수정 //
    public int modifyCycle(Cycle cycle);
    // 생산량 수정 //
    public int modifyProduction(Production production);
    // 생산량 코드로 생산량 정보 조회 //
    public Production getProductionByPCode(String productionCode);
    // 사육장 수정 //
    public int modifyFarm(FarmInfo farmInfo);
    // 생산량 등록 //
    public int addProduction(Production production);
    // 모달창 싸이클 조회 //
    public Cycle getCycleByCode(String cycleCode);
    // 사육장 코드에 따른 싸이클 조회 //
    public List<Cycle> getCycleListByCompanyCode(String companyCode);
    // 싸이클 등록 //
    public int addCycle(Cycle cycle);
    // 케이지 등록 //
	public int addCage(Cage cage);
    // 단위기준 조회 //
    HashMap <String, Object> getStandard(String standardCode);
	// 사육장 등록 //
    public int addFarm(FarmInfo farmInfo);
	// 케이지 코드로 케이지 정보 조회 //
	public Cage getCageByCode(String cageCode);
	// 한 사육장 케이지 조회 //
	public List<Cage> getCageListByCode(String farmCode);
    // 전체 사육장 케이지 조회 및 검색 //
    public List<Cage> getSearchCageList(String companyCode,String searchKey ,String searchValue);
    // 한 사육장 상태 조회 //
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
