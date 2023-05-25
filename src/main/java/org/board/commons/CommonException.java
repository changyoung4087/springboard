package org.board.commons;

import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

/**
 * 공통 예외
 */
public class CommonException extends RuntimeException{

    protected static ResourceBundle bundleValidation; // 메세지 번들
    protected static ResourceBundle bundleError; // 메세지 번들
    protected HttpStatus httpStatus; //응답코드

    static {
        bundleValidation = ResourceBundle.getBundle("messages.validations");
        bundleError = ResourceBundle.getBundle("messages.errors");
    }

    public CommonException(String message) {
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    public CommonException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    public HttpStatus getStatus() {
        return httpStatus;
    }

}
