package ks46team02.common.scheduled;

import ks46team02.admin.dto.LoginHistory;
import ks46team02.admin.service.LoginHistoryService;
import ks46team02.admin.service.MemberService;
import ks46team02.common.dto.Member;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonScheduler {

    private final MemberService memberService;
    private final LoginHistoryService loginHistoryService;

    public CommonScheduler(MemberService memberService, LoginHistoryService loginHistoryService) {
        this.memberService = memberService;
        this.loginHistoryService = loginHistoryService;
    }

    // @Scheduled(cron = "0 15 10 15 11 ?") // 11월 15일 오전 10시 15분에 실행
    // 0 15 10 15 * ? 매월 15일 오전 10시 15분
    // 0 0/10 * * * ?

    @Scheduled(cron="0 0 3 15 * ?")
    // 자동으로 매월 15일 3시마다 로그인 기록으로 휴면회원 처리
    public void switchToDormantAccount(){
        System.out.println("실행");
        List<LoginHistory> loginList = loginHistoryService.getloginHistoryList();
        for (LoginHistory a : loginList) {
            String memberId = a.getMemberId();
            memberService.modifyMemberDormant(memberId);
        }



    }
}
