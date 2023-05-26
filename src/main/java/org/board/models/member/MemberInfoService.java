package org.board.models.member;

import lombok.RequiredArgsConstructor;
import org.board.entites.Member;
import org.board.repositories.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {
    private final MemberRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = repository.findByUserId(username);
        if(member == null) {
            throw new UsernameNotFoundException(username);
        }

        // 권한 설정
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(member.getRole().toString()));

        return MemberInfo.builder()
                .userNo(member.getUserNo())
                .userId(member.getUserId())
                .userPw(member.getUserPw())
                .userNm(member.getUserNm())
                .email(member.getEmail())
                .mobile(member.getMobile())
                .role(member.getRole())
                .authorities(authorities)
                .build();
    }
}
