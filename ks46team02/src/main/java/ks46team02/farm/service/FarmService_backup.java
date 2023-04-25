package ks46team02.farm.service;


import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ks46team02.farm.controller.FarmController;
import ks46team02.farm.dto.Cage;
import ks46team02.farm.dto.Cycle;
import ks46team02.farm.dto.FarmInfo;
import ks46team02.farm.dto.FarmStatus;
import ks46team02.farm.dto.Feed;
import ks46team02.farm.dto.Production;
import ks46team02.farm.mapper.FarmMapper;

@Service
public class FarmService_backup {
	private static final Logger log = LoggerFactory.getLogger(FarmController.class);
    private final FarmMapper farmMapper;
    public FarmService_backup(FarmMapper farmMapper){
        this.farmMapper = farmMapper;
    }
    
    
    public List<Cycle> getCycleList(String farmCode, String companyCode, String searchKey, String searchValue, String fromDate, String toDate){ 
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
    	List<Cycle> cycleList = farmMapper.getCycleList(farmCode,searchKey,searchValue,fromDate,toDate);
    	List<Production> productionList = farmMapper.getAllProductionList(companyCode);
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

    public List<FarmInfo> getFarmList(String companyCode){
        List<FarmInfo> farmList = farmMapper.getFarmList(companyCode);
        return farmList;
    }
    public  List<Feed> getFeedList(String cycleCode){
        List<Feed> feedList = farmMapper.getFeedList(cycleCode);
        return feedList;
    }
    public  List<Production> getProductionList(String farmCode){    		
        List<Production> productionList = farmMapper.getProductionList(farmCode);       
        return productionList;
    }
    public FarmInfo getFarmInfoByCode(String farmCode){
        FarmInfo farmInfo = farmMapper.getFarmInfoByCode(farmCode);
        return farmInfo;
    }

    public List<Production> getAllProductionList(String companyCode){
        List<Production> allProductionList = farmMapper.getAllProductionList(companyCode);
        return allProductionList;
    }


    public  List<Cycle> getAllCycleList(){
        List<Cycle> cycleList = farmMapper.getAllCycleList();
        return cycleList;
    }

    public  List<Cage> getCageList(){
        List<Cage> cageList = farmMapper.getCageList();
        return cageList;
    }

    public List<FarmStatus> getFarmStatusList(){
        List<FarmStatus> farmStatusList = farmMapper.getFarmStatusList();
        return farmStatusList;
    }

}
