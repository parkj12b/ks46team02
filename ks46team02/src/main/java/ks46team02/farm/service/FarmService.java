package ks46team02.farm.service;



import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import ks46team02.common.mapper.MainMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.stereotype.Service;


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
    private final MainMapper mainMapper;
    public FarmService(FarmMapper farmMapper
                        ,MainMapper mainMapper){

        this.farmMapper = farmMapper;
        this.mainMapper = mainMapper;
    }
    
    
	private static final Logger log = LoggerFactory.getLogger(FarmService.class);
    final double standardEggWeight = 0.089;


    /**
     * 생산량 등록
     */
    public int addProduction(Production production){
        String column = "production_code";
        String table = "production";
        String productionCode = mainMapper.autoIncrement(table, column);
        String cycleCode = production.getExpectedCageProductionCode();
        Cycle cycle = farmMapper.getCycleByCode(cycleCode);
        String farmCode =  cycle.getFarmCode();
        double realProduction = production.getRealProduction();
        double estimatedProduction = cycle.getEstimatedProduction();
        double lossLate = (realProduction/estimatedProduction)*100;

        DecimalFormat df = new DecimalFormat("#.##");
        lossLate = Double.valueOf(df.format(lossLate));

        production.setFarmCode(farmCode);
        production.setProductionCode(productionCode);
        production.setLossRate(lossLate);
        log.info("controller에서 넘어온 데이터 : {}", production);
        int result = farmMapper.addProduction(production);
        return result;
    }

    /**
     * 싸이클 등록
     */
    public int addCycle(Cycle cycle){
        String standardCode = cycle.getCalculationStandardCode();
        String column = "expected_cage_production_code";
        String table = "cage_cycle";
        String cycleCode = mainMapper.autoIncrement(table, column);


        HashMap<String, Object> standard = farmMapper.getStandard(standardCode);
        int standardPeriod = (int) standard.get("standard_period");
        String harvestStartDate = cycle.getHarvestStartDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(harvestStartDate, formatter);
        LocalDate estimatedHarvestDate = startDate.plusDays(standardPeriod);
        String estimatedHarvestDateString = estimatedHarvestDate.format(formatter);

        double output = (double) standard.get("standard_output");
        double inputEgg = cycle.getInputEgg();
        double estimatedProduction = output*inputEgg;

        cycle.setEstimatedProduction(estimatedProduction);
        cycle.setEstimatedHarvestDate(estimatedHarvestDateString);
        cycle.setCycleCode(cycleCode);
        log.info("화면에서 전달받은 데이터 : {}", cycle);
        int result = farmMapper.addCycle(cycle);
        return result;
    }

    /**
     * 케이지 등록
     */
    public int addCage(Cage cage){
        log.info("화면에서 전달받은 데이터 : {}", cage);
        String column = "cage_code";
        String table = "cage";
        String cageCode = mainMapper.autoIncrement(table, column);

        int cageNum = cage.getCageNum();
        double CageVolume = cage.getCageVolume();
        double cageTotal = cageNum*CageVolume;
        double optimalInputEgg = cageTotal*standardEggWeight;

        cage.setCageCode(cageCode);
        cage.setCageTotal(cageTotal);
        cage.setOptimalInputEgg(optimalInputEgg);
        log.info("입력값 : {}", cage);
        int result = farmMapper.addCage(cage);
        return result;
    }

    /**
     * 사육장 등록
     */
    public int addFarm (FarmInfo farmInfo) {
        String column = "farm_code";
        String table = "farm_info";
        String farmCode = mainMapper.autoIncrement(table, column);
        log.info(farmCode);
        farmInfo.setFarmCode(farmCode);
        int result = farmMapper.addFarm(farmInfo);
        return result;
    }


	/*
	 * 케이지 코드로 하나의 케이지 정보 조회
	 */
	public Cage getCageByCode(String cageCode) {
		Cage cage = farmMapper.getCageByCode(cageCode);
		return cage;
	}
	
	/**
	 * 하나의 사육장 케이지 조회
	 */
	public List<Cage> getCageListByCode(String farmCode) {
		List<Cage> cageList = farmMapper.getCageListByCode(farmCode);
		return cageList;
	}

    /**
     * 전체 사육장 케이지 조회
     */
    public  List<Cage> getSearchCageList(String companyCode
            ,String searchKey
            ,String searchValue){
        if(searchKey != null) {
            switch (searchKey) {
                case "farmCode":
                    searchKey = "farm_code";
                    break;
                case "cageCode":
                    searchKey = "cage_code";
                    break;
                case "cageName":
                    searchKey = "cage_ame";
                    break;
                case "cageVolume":
                    searchKey = "cage_volume";
                    break;

                default:
                    break;
            }
        }
        List<Cage> cageList = farmMapper.getSearchCageList(companyCode,searchKey,searchValue);
        return cageList;
    }


    /**
     * 한 사육장 상태 조회
     * @return
     */
    public List<FarmStatus> getFarmStatusList(String farmCode){
        List<FarmStatus> farmStatusList = farmMapper.getFarmStatusList(farmCode);
        return farmStatusList;
    }

    /**
     * 싸이클 코드에대한 먹이 급여 조회
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
