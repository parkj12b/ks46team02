package ks46team02.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.mapper.AddrMapper;
import ks46team02.admin.mapper.AdminMapper;
import ks46team02.common.dto.Addr;
@Service
@Transactional
public class AddrService {
	
private final AddrMapper addrMapper;
	
	public AddrService(AddrMapper addrMapper) {
		this.addrMapper = addrMapper;

	}
	
	public List<Addr> getAddrList(){
		List<Addr> AddrList = addrMapper.getAddrList();
		return AddrList;
	}
	public void removeAddr(String addrCode ) {
		addrMapper.removeAddrByCode(addrCode);
		}
	}
