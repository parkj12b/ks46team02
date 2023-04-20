package ks46team02.farm.service;


import java.util.Date;
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
public class FarmService {
	private static final Logger log = LoggerFactory.getLogger(FarmController.class);
    private final FarmMapper farmMapper;
    public FarmService(FarmMapper farmMapper){
        this.farmMapper = farmMapper;
    }

    public List<FarmInfo> getFarmList(String companyCode){
        List<FarmInfo> farmList = farmMapper.getFarmList(companyCode);
        return farmList;
    }
    public  List<Feed> getFeedList(){
        List<Feed> feedList = farmMapper.getFeedList();
        return feedList;
    }
    public  List<Production> getProductionList(String farmCode, String searchKey, String searchValue, String fromDate, String toDate){
    	if(searchKey != null) {
    		switch (searchKey) {
			case "productionCode":
				searchKey = "production_code";
				break;
			case "cycleCode":
				searchKey = "expected_cage_production_code";
				break;

			default:
				break;
			}
    	}
        List<Production> productionList = farmMapper.getProductionList(farmCode, searchKey, searchValue, fromDate, toDate);
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
