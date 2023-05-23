package org.board.controllers.members;

import lombok.RequiredArgsConstructor;
import org.board.commons.validators.MobileValidator;
import org.board.commons.validators.PasswordValidator;
import org.board.repositories.MemberRepository;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component // 디비도 필요하기 때문에 @Component 사용
@RequiredArgsConstructor
public class JoinValidator implements Validator, MobileValidator, PasswordValidator {

    private final MemberRepository memberRepository; // member DB
    @Override
    public boolean supports(Class<?> clazz) {
        /** 조인폼에 있는 회원가입만 검증을 하겠다. */
        return JoinForm.class.isAssignableFrom(clazz);
    }

    @Override // 커맨드객체 target, error 객체
    public void validate(Object target, Errors errors) {
        /**
         * 1. 아이디 중복 여부
         * 2. 비밀번호 복잡성 체크(알파벳(대문자, 소문자), 숫자, 특수문자))
         * 3. 비밀번호와 비밀번호 확인 일치
         * 4. 휴대전화번호(선택) - 입력된 경우 형식 체크 ** 정규식
         * 5. 휴대전화번호가 입력된 경우 숫자만 추출해서 다시 커맨드 객체에 저장
         * 6. 필수 약관동의만 체크
         */
        // 커맨드객체 target 에서 회원정보를 가져오기 위해 정의
        JoinForm joinForm = (JoinForm) target;
        String userId = joinForm.getUserId();
        String userPw = joinForm.getUserPw();
        String userPwRe = joinForm.getUserPwRe();
        String mobile = joinForm.getMobile();
        boolean[] agrees = joinForm.getAgrees(); // 필수 약관(다중..)

        // 1. 아이디 중복 여부
        if(userId != null && userId.isBlank() && memberRepository.exists(userId)){
            errors.rejectValue("userId", "Validation.duplicate.userId");
        }

        // 2. 비밀번호 복잡성 체크(알파벳(대문자, 소문자), 숫자, 특수문자))
        if(userPw != null && !userPw.isBlank()
                        && (!alphaCheck(userPw, false)
                            || numberCheck(userPw)
                            || specialCharsCheck(userPw))){
            errors.rejectValue("userPw", "Validation.complexity.password");

        }

        // 3. 비밀번호와 비밀번호 확인 일치
        if(userPw != null && userPw.isBlank()
                && userPwRe != null && userPwRe.isBlank()
                && !userPw.equals(userPwRe)) {
            errors.rejectValue("userPwRe", "Validation.incorrect.userPwRe");
        }

        // 4. 휴대전화번호(선택) - 입력된 경우 형식 체크 ** 정규식 (MobileValidator)

        if(mobile != null && !mobile.isBlank()) {
            if(!mobileNumCheck(mobile)){
                errors.rejectValue("mobile", "Validation.mobile");
            }
            // 5. 휴대전화번호가 입력된 경우 숫자만 추출해서 다시 커맨드 객체에 저장
            mobile = mobile.replaceAll("\\D", "");
            joinForm.setMobile(mobile);
        }

        // 6. 필수 약관동의만 체크
        if(agrees != null && agrees.length > 0) {
            for(boolean agree : agrees) {
                if(!agree){
                    errors.reject("Validation.joinForm.agree");
                    break;
                }
            }
        }
    }
}
