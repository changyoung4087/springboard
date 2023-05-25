package org.board.models.member;

import lombok.RequiredArgsConstructor;
import org.board.commons.constants.Role;
import org.board.controllers.members.JoinForm;
import org.board.entites.Member;
import org.board.repositories.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 회원정보 추가, 수정
 * - 수정 시 비밀번호는 값이 있을때만 수정
 */
@Service
@RequiredArgsConstructor
public class MemberSaveService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(JoinForm joinForm){

        Member member = new ModelMapper().map(joinForm, Member.class);
        member.setRole(Role.USER);
        // 비밀번호 캐시화
        member.setUserPw(passwordEncoder.encode(joinForm.getUserPw()));

        memberRepository.saveAndFlush(member);
    }

}
