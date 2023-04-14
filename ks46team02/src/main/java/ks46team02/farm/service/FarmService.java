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

    public  List<Feed> getFeedList(){
        List<Feed> feedList = farmMapper.getFeedList();
        return feedList;
    }

    public  List<Production> getProductionList(){
        List<Production> productionList = farmMapper.getProductionList();
        return productionList;
    }


    public  List<Cycle> getCycleList(){
        List<Cycle> cycleList = farmMapper.getCycleList();
        return cycleList;
    }

    public  List<Cage> getCageList(){
        List<Cage> cageList = farmMapper.getCageList();
        return cageList;
    }
    public List<FarmInfo> getFarmList(){
        List<FarmInfo> farmList = farmMapper.getFarmList();
        return farmList;
    }
    public List<FarmStatus> getFarmStatusList(){
        List<FarmStatus> farmStatusList = farmMapper.getFarmStatusList();
        return farmStatusList;
    }

}
