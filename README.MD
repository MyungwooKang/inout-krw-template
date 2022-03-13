# Project Description
입출금 모듈 템플릿 프로젝트.
#
# Dependencies
작성 당시 기준.
1. JAVA : openjdk 11
2. DB : oracle 11g
#
# Getting Started
1. Oracle DB 설치
2. 사용자 계정 생성 및 schema 생성
   1. schema : BTCKOREADB_OWN
3. 프로퍼티 변경
   1. application-local.yml
   2. application-test.yml
```yml
server:
   port: 80 # 변경
spring:
   datasource:
      url: jdbc:oracle:thin:@localhost:1521:XE # 변경
      username: inoutkrw # 변경
      password: 1234     # 변경
```
#
# Package Hierarchy
```bash
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─example
    │  │          └─inoutkrwtemplate
    │  │              ├─common
    │  │              │  ├─config           // 공통 설정 작성
    │  │              │  ├─exception        // 커스텀 Exception 작성
    │  │              │  └─util             // 필요한 유틸 작성 
    │  │              ├─controller        // 컨트롤러 모음
    │  │              │  ├─code             // response errorcode : 서비스 자체 정의 에러코드
    │  │              │  ├─dto              // 컨트롤러 DTO
    │  │              │  └─template         // response template
    │  │              ├─external          // 외부 시스템 연동 
    │  │              │  └─dto              // 외부 시스템 DTO
    │  │              ├─repository        // DB 
    │  │              │  ├─entity           // 도메인 엔티티
    │  │              │  ├─enums            // 엔티티에서 사용하는 enums
    │  │              │  │  └─converter     // enum converter 모음 
    │  │              │  └─vo               // view 또는 특정 칼럼 조회시 사용되는 obejct 모음
    │  │              └─service           // service
    │  │                  └─impl            // 서비스 구현체
    │  └─resources
    └─test
        └─java
            └─com
               └─example
                   └─inoutkrwtemplate
                       ├─config             // 테스트 설정
                       ├─controller         // unit test
                       ├─repository         // unit test
                       └─service            
                          └─impl            // unit test
``` 
# 기타
## 현재까지 추가한 내용
1. 기본 RestApi 작성 방법
   1. 응답 객체 (에러응답 포함)
2. 외부 Http Api 호출 방법 : Feign
3. DB jpql 예시
   1. 인덱스, 쿼리힌트
   2. Legacy code -> enum 컨버팅 방법
4. 단위테스트 작성 방법
   1. 컨트롤러, 서비스, repository 예시
##
## 추가 예정 or 확인 필요 사항
1. Security
2. 로그 추적
3. api documentation
   1. swagger vs restdocs
#

   