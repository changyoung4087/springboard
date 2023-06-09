package org.board.controllers.members;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class JoinForm {
    @NotBlank // NotNull, isBlank 처리
    @Size(min=6, max=20)
    private String userId; // 아이디
    @NotBlank
    @Size(min=8)
    private String userPw; // 비밀번호
    @NotBlank
    private String userPwRe; // 비밀번호 확인
    @NotBlank
    private String userNm; // 회원명
    @NotBlank @Email
    private String email; // 이메일
    private String mobile; // 휴대전화번호
    private boolean[] agrees; // 약관동의
}
