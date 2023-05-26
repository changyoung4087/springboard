package org.board.configs;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.board.models.member.LoginFailureHandler;
import org.board.models.member.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
public class SecurityConfig {

    /** 시큐리티 로그인화면 무력화 S */
    @Bean /** 인증 인가 접근제어 설정 */
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/member/login")
                .usernameParameter("userId")
                .passwordParameter("userPw")
                /** 로그인, 로그아웃 후 화면전환 시 member/login 경로를 Handler로 통제하기 위해 class를 만들어줌. */
                //.defaultSuccessUrl("/")
                .successHandler(new LoginSuccessHandler())
                //.failureForwardUrl("/member/login")
                .failureHandler(new LoginFailureHandler())
                /** 로그인, 로그아웃 후 화면전환 시 member/login 경로를 Handler로 통제하기 위해 class를 만들어줌. */
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/member/login");

        // url 패턴에따라 접근할 수 있는 인가관련된 기능 정의
        http.authorizeHttpRequests()
                .requestMatchers("/mypage/**").authenticated() // 회원정보 수정은 모두 마이페이지(회원 전용)
//                .requestMatchers("/admin/**").hasAuthority("ADMIN") // 관리자 전용
                .anyRequest().permitAll(); // 그 외 모든 페이지는 모든 회원이 접근 가능.

        // 이셉션 시 내가 원하는 경로로 이동.
        http.exceptionHandling()
//                .authenticationEntryPoint(new AuthenticationEntryPoint() {
//                    @Override
//                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//
//                    }
//                });
                // 위 소스 람다식으로 변경
                .authenticationEntryPoint((req, res, e) -> {
                    String URI = req.getRequestURI();

                    if(URI.indexOf("/admin") != -1) { // 관리자 페이지
                        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "NOT AUTHORIZED"); // 관리자페이지 401
                    }else {
                        String redirectURL = req.getContextPath() + "/member/login"; // 관리자페이지가 아니면 login
                        res.sendRedirect(redirectURL);
                    }
                });

        // 파일 레이어팝업 사용하기 위해 정의
        // 같은 도메인 내에서 아이프레임 공유
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return w -> w.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "errors/**");
    }
    /** 시큐리티 로그인화면 무력화 E */

    /** 비밀번호 암호화 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
