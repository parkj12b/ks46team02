package ks46team02.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.controller.AdminController;
import ks46team02.admin.mapper.AddrMapper;
import ks46team02.common.dto.Addr;
import ks46team02.common.mapper.MainMapper;
@Service
@Transactional
public class AddrService {
	
private final AddrMapper addrMapper;
private final MainMapper mainMapper;

private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	public AddrService( AddrMapper addrMapper
					   ,MainMapper mainMapper) {
		this.addrMapper = addrMapper;
		this.mainMapper = mainMapper;
	}
	/* 배송지 등록 */
	public boolean addAddr(Addr addr) {
		 String column = "shipping_addr_code";
	     String table = "addr_manage";
	     String addrCode = mainMapper.autoIncrement(table, column);
	     addr.setAddrCode(addrCode);
	     String memberId = addr.getMemberId();
	     log.info("memberId :{}",memberId);
		 int account = addrMapper.getAddrAmountList(memberId);
		 log.info("account :{}",account);
		 if (account == 2) {
	            // 등록된 배송지가 2개인 경우, 등록하지 않음
	            return false;
	        } else if (account == 1) {
	            // 이전에 등록한 배송지가 있는 경우, secondary로 등록
	            addr.setAddrSeq("secondary");
	        } else if (account == 0){
	            // 배송지가 처음 등록되는 경우, primary로 등록
	            addr.setAddrSeq("primary");
	        }
	        addrMapper.addAddr(addr);
	        return true;
	    }
	
	/* 배송지 조회 */
	public List<Addr> getAddrList(){
		List<Addr> AddrList = addrMapper.getAddrList();
		return AddrList;
	}
	/* 특정 배송지 조회 */
	public Addr getAddrInfoById(String addrCode) {
		Addr adddrInfo = addrMapper.getAddrInfoById(addrCode);
		return adddrInfo;
	}
	/* 특정 회원 배송지 조회 */
	public List<Addr> getAddrInfoByMemberId(String memberId) {
		List<Addr> AddrList = addrMapper.getAddrMemberList(memberId);
		return AddrList;
	}
	}
