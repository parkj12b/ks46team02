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
	
	public boolean harvestCheck();
	
	public List<Cycle> getAllCycleList();
	public List<Cycle> getCycleList(String farmCode);
 
    public List<Production> getAllProductionList(String companyCode);
    public List<Production> getProductionList(String farmCode, String searchKey, String searchValue ,String fromDate, String toDate);
   
    public FarmInfo getFarmInfoByCode(String farmCode);
    public List<Feed> getFeedList();
    public List<Cage> getCageList();
    public List<FarmInfo> getFarmList(String companyCode);
    public List<FarmStatus> getFarmStatusList();
}
