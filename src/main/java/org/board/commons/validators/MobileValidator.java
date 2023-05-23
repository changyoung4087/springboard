package org.board.commons.validators;

public interface MobileValidator {
    default boolean mobileNumCheck(String mobile) {

        /**
         *  ** JoinValidator에서 다중상속이 필요하기 때문에 MobileValidator 를 interface class로 생성해줌 **
         *
         * 1. 형식의 통일화 - 숫자가 아닌 문자 전부 제거
         * 2. 패턴 생성 체크
         */
        /** \\d = 숫자패턴을 의미, \\D = 숫자가 아닌 패턴을 의미 */
        mobile = mobile.replaceAll("\\D", "");
        //문자 클래스 010, 011, 016, {} => 범위(자리수) 3이상 4이하의 패턴, 4개의패턴
        // [^] 문자에서의 ^는 부정의 의미, 패턴에서의 ^는 시작되는 패턴.. (반드시 앞자리가 01부터 시작 $ 끝나는 패턴
        String pattern = "^01[016]\\d{3,4}\\d{4}$";

        return mobile.matches(pattern);
    }
}
