package org.board.controllers.admins;

import lombok.Data;

@Data
public class ConfigForm {
    // 커맨드 객체 생성
    private String siteTitle;
    private String siteDescription;
    private String cssJsVersion;
    private String joinTerms;
}
