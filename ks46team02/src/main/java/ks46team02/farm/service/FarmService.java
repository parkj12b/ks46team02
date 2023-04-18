package ks46team02.farm.service;


import ks46team02.farm.dto.*;
import ks46team02.farm.mapper.FarmMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmService {
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


    public  List<Cycle> getCycleList(){
        List<Cycle> cycleList = farmMapper.getCycleList();
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
