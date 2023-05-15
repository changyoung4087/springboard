package org.board.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /** 시큐리티 로그인화면 무력화 S */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return w -> w.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "errors/**");
    }
    /** 시큐리티 로그인화면 무력화 E */
}
