package ks46team02.admin.service;

import jakarta.servlet.http.HttpSession;
import ks46team02.admin.dto.MemberLevel;
import ks46team02.admin.mapper.MemberLevelMapper;
import ks46team02.common.mapper.MainMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class MemberLevelService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberLevelService.class);

	
private final MemberLevelMapper memberLevelMapper;
private  final MainMapper mainMapper;

	public MemberLevelService(MemberLevelMapper memberLevelMapper,MainMapper mainMapper) {
		this.memberLevelMapper = memberLevelMapper;
		this.mainMapper = mainMapper;

	}
	/* 회원 등급 조회 */
	public List<MemberLevel> getMemberLevelList(){
		List<MemberLevel> MemberLevelList = memberLevelMapper.getMemberLevelList();
		return MemberLevelList;
	}
	/* 회원 등급 수정*/
	public void  modifyMemberLevel(MemberLevel memberLevel) {

		memberLevelMapper.modifyMemberLevel(memberLevel);
	}
	/* 회원 등급 등록 */
	public int addMemberLevel(MemberLevel memberLevel
	  						 ,HttpSession session) {
		String column = "position_level_code";
		String table = "company_position_level";
		String positionLevelCode = mainMapper.autoIncrement(table, column);
		log.info("positionLevelCode:{}",positionLevelCode);
		String adminId = (String)session.getAttribute("sessionId");
		memberLevel.setPositionLevelCode(positionLevelCode);
		memberLevel.setAdminId(adminId);
		int result = memberLevelMapper.addMemberLevel(memberLevel);
		return result;
	}

	
}
