package org.board.commons;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.board.commons.constants.Role;
import org.board.entites.Member;
import org.board.models.member.MemberInfo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtil {
    private final HttpSession session;

    /**
     * 로그인 여부
     * @return
     */
    public boolean isLogin() {
        return getMember() != null;
    }

    /**
     * 관리자 여부
     * @return
     */
    public boolean siAdmin() {
        // 로그인 했을 때 member 에 있는 getRole 이 ADMIN이랑 일치하면 관리자
        return isLogin() && getMember().getRole() == Role.ADMIN;
    }
    public MemberInfo getMember() {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        return memberInfo;
    }
    public Member getEntity() {

        if(isLogin()){
            Member member = new ModelMapper().map(getMember(), Member.class);
            return member;
        }

        return null;
    }
}
