package org.board.entites;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.*;
import org.board.commons.constants.Role;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(indexes={
        // 검색 index 걸어줌.
        @Index(name="idx_member_userNm", columnList="userNm"),
        @Index(name="idx_member_email", columnList="email")
})
public class Member extends BaseEntity {
    @Id @GeneratedValue // 자동증가
    private Long userNo; // 회원번호
    @Column(length=40, nullable = false, unique = true)
    private String userId; // 회원 아이디
    @Column(length=65, nullable = false)
    private String userPw; // 비밀번호
    @Column(length=40, nullable = false)
    private String userNm; // 회원명
    @Column(length=100, nullable = false)
    private String email; // 이메일
    @Column(length=11)
    private String mobile; // 휴대전화번호
    @Lob
    private String termsAgree; // 약관동의 내역 // json 형태로 구현

    /** 사용자 권한 추가 */
    @Enumerated(EnumType.STRING) // 이넘클래스는 Enumerted 를 정의해야됨.
    @Column(length = 10, nullable = false)
    private Role role = Role.USER;
}
