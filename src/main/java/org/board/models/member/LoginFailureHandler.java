package org.board.models.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    // 스프링 시큐리티에 적용되어있는 로그인, 로그아웃 실패 시 처리해줄 수 있는 인터페이스 AuthenticationFailureHandler
    @Override // 매개변수 :: request : 요청데이터, exception 인증실패 시 발생하는 예외가 나옴.
              //            response : 응답헤더에 로케이션을 싣고 페이지 이동
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        /** 새로고침 시 에러메시지 초기화 */
        session.removeAttribute("userId");
        session.removeAttribute("requiredUserId");
        session.removeAttribute("requiredUserPw");
        session.removeAttribute("global");
        /** 새로고침 시 에러메시지 초기화 */

        session.setAttribute("userId", userId);

        try {
            if(userId == null || userId.isBlank()){
                throw  new LoginValidationException("requiredUserId", "NotBlank.userId");
            }
            if(userPw == null || userPw.isBlank()){
                throw new LoginValidationException("requiredUserPw", "NotBlank.userPw");
            }

            throw new LoginValidationException("global", "Validation.login.fail");
        }catch(LoginValidationException e){
            session.setAttribute(e.getField(), e.getMessage());
        }
        // 로그인 실패 시 로그인화면으로 이동
        response.sendRedirect(request.getContextPath() + "/member/login");
    }

}
