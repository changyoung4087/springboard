# 게시판 포트폴리오
***
## 1일차
* 스프링 부트 기본 설정
    - 오라클 driver / mysql driver
    - (메이븐 레파지토리 : Ojdbc8, querydsl(jpa, apt))
    - apt :: Q형태 코드 자동생성
    - querydsl 의존성 / 설정
    - ModelMapper

* 타임리프 설정

## 2일차
* 타임리프 레이아웃 템플릿 완성
* 스프링 시큐리티 설정
    - 회원가입 엔티티, 레포지토리

## 3일차
* 스프링 시큐리티 설정
    - 로그인 양식
    - UserDetails, UserDetailsService 인터페이스 구현 클래스

* 기본 에러응답 코드처리
    - 템플릿 경로 /error/응답코드.html
        - timestamp - 오류 발생 시각
        - status - 오류 발생 원인
        - exception - 예외 객체
        - errors - Errors 객체
        - trace - printStackTrace()
        - path - 오류의 유입 URL
