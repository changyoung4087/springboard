package org.board.models.member;

import lombok.Builder;
import lombok.Data;
import org.board.commons.constants.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data @Builder
public class MemberInfo implements UserDetails {

    private Long userNo; // 회원번호
    private String userId; // 아이디
    private String userPw; // 비밀번호
    private String userNm; // 회원명
    private String email; // 이메일
    private String mobile; // 휴대전화번호
    private Role role;
    private Collection<GrantedAuthority> authorities; // 설정할 때 필요 ???
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override // 시큐리티는 getPassword 와 getUsername 으로 로그인을 하게됨.. UserDetails 가 중요함.
    public String getPassword() {
        return userPw;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
