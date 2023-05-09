package ks46team02.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.dto.LoginHistory;
import ks46team02.admin.mapper.LoginHistoryMapper;
@Service
@Transactional
public class LoginHistoryService {
	
private final LoginHistoryMapper loginHistoryMapper;
	
	public LoginHistoryService(LoginHistoryMapper loginHistoryMapper) {
		this.loginHistoryMapper = loginHistoryMapper;

	}
	
	public List<LoginHistory> getloginHistoryList(){
		List<LoginHistory> loginHistoryList = loginHistoryMapper.getLoginHistoryList();
		return loginHistoryList;
	}
	
}
