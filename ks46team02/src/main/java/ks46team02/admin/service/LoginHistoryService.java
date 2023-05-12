package ks46team02.admin.service;


import java.util.List;

import ks46team02.common.dto.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team02.admin.dto.LoginHistory;
import ks46team02.admin.mapper.LoginHistoryMapper;
import ks46team02.common.mapper.MainMapper;
@Service
@Transactional
public class LoginHistoryService {
	
private final LoginHistoryMapper loginHistoryMapper;
private final MainMapper mainMapper;
private  final MemberService memberService;
	
	public LoginHistoryService(LoginHistoryMapper loginHistoryMapper, MainMapper mainMapper, MemberService memberService) {
		this.loginHistoryMapper = loginHistoryMapper;
		this.mainMapper = mainMapper;

		this.memberService = memberService;
	}

	public List<LoginHistory> getloginHistoryList(){
		List<LoginHistory> loginHistoryList = loginHistoryMapper.getLoginHistoryList();
		return loginHistoryList;
	}
	/* 로그인 기록 등록 */
	public int addLoginHistory(LoginHistory loginHistory) {
		 String column = "login_code";
	     String table = "login_history";
	     String loginCode = mainMapper.autoIncrement(table, column);
	     loginHistory.setLoginCode(loginCode);
	     int result =loginHistoryMapper.addLoginHistory(loginHistory);
	        return result;
	    }

}
