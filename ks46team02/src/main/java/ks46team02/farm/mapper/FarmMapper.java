package ks46team02.farm.mapper;


import ks46team02.farm.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FarmMapper {

    public List<Production> getAllProductionList(String companyCode);
    public FarmInfo getFarmInfoByCode(String farmCode);
    public List<Feed> getFeedList();
    public List<Production> getProductionList(String farmCode);
    public List<Cycle> getCycleList();
    public List<Cage> getCageList();
    public List<FarmInfo> getFarmList(String companyCode);
    public List<FarmStatus> getFarmStatusList();
}
