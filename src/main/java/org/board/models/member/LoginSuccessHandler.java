package org.board.models.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
// 스프링 시큐리티에 적용되어있는 로그인, 로그아웃 성공 시 처리해줄 수 있는 인터페이스 AuthenticationSuccessHandler
    @Override // 회원정보 데이터를 불러올 수 있는 authentication
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        /**
         * 1. 세션에 담긴 로그인 에러메세지 삭제
         * 2. 로그인한 회원 정보 쉽게 확인하도록 세션처리
         * 3. 로그인 성공 시 메인페이지로 이동
         */

        HttpSession session = request.getSession();
        session.removeAttribute("userId");
        session.removeAttribute("requiredUserId");
        session.removeAttribute("requiredUserPw");
        session.removeAttribute("global");

        // 세션에 멤버정보 넣어주기.
        MemberInfo memberInfo = (MemberInfo) authentication.getPrincipal();
        session.setAttribute("memberInfo", memberInfo);

        String url = request.getContextPath() + "/";
        response.sendRedirect(url);
    }
}
