package org.board.configs.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 사이트 설정유지
 *
 */
@Component
public class SiteConfigInterceptor implements HandlerInterceptor {
    /**
     * boolean preHandle()
     * 컨트롤러의 빈이 호출되기 전에 호출 // 통제가 가능. false면 다음단계로 넘어가지 않기 때문에
     * 인증용으로 주로 사용.
     * void postHandle()
     * 컨트롤 빈이 실행되고 나서 출력하기 전 모든 핸들을 반환하고 나서 호출됨
     * void afterCompletion()
     * 전체 모든것이 끝났을 때 호출
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 버전 1로 고정..
        request.setAttribute("cssJsVersion", 1);
        return true;
    }
}
