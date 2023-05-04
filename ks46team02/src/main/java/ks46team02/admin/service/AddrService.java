package ks46team02.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.mapper.AddrMapper;
import ks46team02.common.dto.Addr;
import ks46team02.common.mapper.MainMapper;
@Service
@Transactional
public class AddrService {
	
private final AddrMapper addrMapper;
private final MainMapper mainMapper;
	
	public AddrService( AddrMapper addrMapper
					   ,MainMapper mainMapper) {
		this.addrMapper = addrMapper;
		this.mainMapper = mainMapper;
	}
	/* 배송지 등록 */
	public int addAddr(Addr addr) {
		 String column = "shipping_addr_code";
	     String table = "addr_manage";
	     String addrCode = mainMapper.autoIncrement(table, column);
	     addr.setAddrCode(addrCode);
	     int result = addrMapper.addAddr(addr);
	     return result;
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
