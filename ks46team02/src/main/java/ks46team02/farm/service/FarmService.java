package ks46team02.farm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import ks46team02.farm.controller.FarmController;
import ks46team02.farm.dto.Cage;
import ks46team02.farm.dto.Cycle;
import ks46team02.farm.dto.FarmInfo;
import ks46team02.farm.dto.FarmStatus;
import ks46team02.farm.dto.Feed;
import ks46team02.farm.dto.Production;
import ks46team02.farm.mapper.FarmMapper;

@Service
public class FarmService {
    private final FarmMapper farmMapper;
    public FarmService(FarmMapper farmMapper){
    	
        this.farmMapper = farmMapper;
    }
    
    /**
     * 
     * @param cycleCode
     * @return
     */
    public  List<Feed> getFeedListByCycleCode(String cycleCode){
        List<Feed> feedList = farmMapper.getFeedListByCycleCode(cycleCode);
        return feedList;
    }
    
    /**
     * 전체 생산량 검색 조회
     */
    public List<Production> getSearchProduction(String companyCode
									    		,String searchKey
												,String searchValue
												,String fromDate
												,String toDate){
    	if(searchKey != null) {
    		switch (searchKey) {
			case "productionCode":
				searchKey = "p.production_code";
				break;
			case "farmCode":
				searchKey = "p.farm_code";
				break;
			case "expectedCageProductionCode":
				searchKey = "ex.calculation_standard_code";
				break;
			case "calculationStandardCode":
				searchKey = "ex.calculation_standard_code";
				break;

			default:
				break;
			}
    	}
        List<Production> allProductionList = farmMapper.getSearchProduction(companyCode,searchKey,searchValue,fromDate,toDate);
        return allProductionList;
    }


    /**
     * 싸이클 검색및 조회
     * @return
     */
    public List<Cycle> getSearchCycle(String farmCode, String companyCode, String searchKey, String searchValue, String fromDate, String toDate){ 
    	if(searchKey != null) {
    		switch (searchKey) {
			case "cageCode":
				searchKey = "cy.expected_cage_production_code";
				break;
			case "estimatedProduction":
				searchKey = "estimated_production";
				break;
			case "dayDiffHarvest":
				searchKey = "estimated_harvest_date";
				break;

			default:
				break;
			}
    	}
    	List<Cycle> cycleList = farmMapper.getSearchCycle(farmCode,searchKey,searchValue,fromDate,toDate);
    	List<Production> productionList = farmMapper.getProductionList(companyCode);
    	for (int i = 0; i < cycleList.size(); i++) {
    	    Cycle cycle = cycleList.get(i);
    	    for (int x = 0; x < productionList.size(); x++) {
    	    	Production production = productionList.get(x);
    	        if (cycle.getCycleCode().equals(production.getExpectedCageProductionCode())) {
    	            cycle.setDayDiffHarvest("수확완료");
    	        }
    	    }
    	    cycleList.set(i, cycle);
    	    
    	}
    	
    	return cycleList;
    }
    
    /**
     * 전체 사육장 조회
     * @param companyCode
     * @return
     */
    public List<FarmInfo> getFarmList(String companyCode){
        List<FarmInfo> farmList = farmMapper.getFarmList(companyCode);
        return farmList;
    }
    
    /**
     * 하나의 사육장 정보 조회
     * @param farmCode
     * @return
     */
    public FarmInfo getFarmInfoByCode(String farmCode){
        FarmInfo farmInfo = farmMapper.getFarmInfoByCode(farmCode);
        return farmInfo;
    }
    
    /**
     * 하나의 사육장 생산량 조회
     * @param farmCode
     * @return
     */
    public List<Production> getProductionByCode(String farmCode){    		
        List<Production> productionList = farmMapper.getProductionByCode(farmCode);       
        return productionList;
    }
    
    /**
     * 전체 사육장 생산량 조회
     * @param companyCode
     * @return
     */
    public List<Production> getProductionList(String companyCode){
        List<Production> productionList = farmMapper.getProductionList(companyCode);
        return productionList;
    }
   
    

}
