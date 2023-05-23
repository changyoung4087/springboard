package org.board.repositories;

import org.board.entites.Member;
import org.board.entites.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {

    // 아이디로 회원 조회
    Member findByUserId(String userId);

    // 회원 아이디 존재유무 확인
    default boolean exists(String userId){
        QMember member = QMember.member;
        // exsits(Predicate predicate) 사용
        boolean result = exists(member.userId.eq(userId));

        return result;
    }
}
