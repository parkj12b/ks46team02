package ks46team02.farm.mapper;


import ks46team02.farm.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FarmMapper {

    public List<Feed> getFeedList();
    public List<Production> getProductionList();
    public List<Cycle> getCycleList();
    public List<Cage> getCageList();
    public List<FarmInfo> getFarmList();
    public List<FarmStatus> getFarmStatusList();
}
