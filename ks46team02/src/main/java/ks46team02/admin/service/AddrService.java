package ks46team02.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.mapper.AddrMapper;
import ks46team02.common.dto.Addr;
import ks46team02.common.dto.Member;
@Service
@Transactional
public class AddrService {
	
private final AddrMapper addrMapper;
	
	public AddrService(AddrMapper addrMapper) {
		this.addrMapper = addrMapper;

	}
	/* 배송지 등록 */
	public int addAddr(Addr addr) {
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
	}
