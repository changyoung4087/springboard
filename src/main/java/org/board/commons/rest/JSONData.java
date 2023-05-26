package org.board.commons.rest;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class JSONData<T> {

    private boolean success;
    private HttpStatus status = HttpStatus.OK; // 오류코드 기본값 200
    private String message;
    private T data;
}
