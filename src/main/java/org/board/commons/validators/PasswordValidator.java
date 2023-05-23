package org.board.commons.validators;

public interface PasswordValidator {

    /**
     * @param password
     * @param caseIncentive
     *          false : 소문자 + 대문자가 반드시 포함되는 패턴
     *          true : 대소문자 상관없이 포함되는 패턴
     * @return
     */
    default boolean alphaCheck(String password, boolean caseIncentive) {
        if(caseIncentive) { // true :: 대소문자 구분없이 체크
            return password.matches("[a-zA-Z]+");
        }
        // false :: 대문자, 소문자 각각 체크
        return password.matches("[a-z]+") && password.matches("[A-Z]+");
    }

    /**
     * 숫자가 포함된 패턴인지 체크
     * @param password
     * @return
     */
    default boolean numberCheck(String password) {
        // \\d :: 모든 숫자패턴
        return password.matches("\\d");
    }

    /**
     * 특수문자가 포함된 패턴인지 체크
     * @param password
     * @return
     */
    default boolean specialCharsCheck(String password) {

        //return password.matches("[`~!@#$%\\^&\\*()-_+=]+");
        return true;
    }
}
