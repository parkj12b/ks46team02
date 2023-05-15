package ks46team02.common.scheduled;

import ks46team02.admin.controller.AdminController;
import ks46team02.admin.dto.LoginHistory;
import ks46team02.admin.service.LoginHistoryService;
import ks46team02.admin.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonScheduler {

    private final MemberService memberService;

    private final LoginHistoryService loginHistoryService;

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    public CommonScheduler(MemberService memberService, LoginHistoryService loginHistoryService) {
        this.memberService = memberService;
        this.loginHistoryService = loginHistoryService;
    }

    // @Scheduled(cron = "0 15 10 15 11 ?") // 11월 15일 오전 10시 15분에 실행
    // 0 15 10 15 * ? 매월 15일 오전 10시 15분
    // 0 0/10 * * * ?

    @Scheduled(cron="0 30 3 10 * ?")
    // 자동으로 매월 15일 3시마다 로그인 기록으로 휴면회원 처리
    public void switchToDormantAccount(){
        System.out.println("실행");
        List<LoginHistory> loginDormentList= loginHistoryService.getDormentLoginList();
        log.info("loginDormentList:{}",loginDormentList);
        for (LoginHistory a : loginDormentList) {
            String memberId = a.getMemberId();
            memberService.modifyMemberDormant(memberId);
        }



    }
}
