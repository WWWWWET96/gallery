# 경매 사이트

# 목표

- 스프링 프레임워크와 DB의 깊은 이해

→ 웹 페이지 만들기:갤러리 경매 사이트

# 소개

- 그림 경매 사이트
- **일정 기간 동안 낙찰 신청을 받고, 최종 낙찰자를 선정하는 시스템**
- 낙찰 기준: 경매 기간 동안의 최고가

<aside>
🏜️ 참조 사이트

[갤러리현대](https://www.galleryhyundai.com/main)

</aside>

# 기능

| 관리자 기능 | 사용자 기능 |
| --- | --- |
| 로그인 | 회원 가입 |
| 회원 정보 관리 | 로그인 |
| 작품 관리 : 작품 등록 | 내 정보 조회 |
| 최종 낙찰자 선정 | 찜(wishlist) : 관심 작품 찜 등록 |
| 작품 삭제 | 낙찰 신청 : 신청 기간 내에 가격과 함께 신청 |
|  | 낙찰 조회 : 낙찰 신청했던 정보 조회  |

# ERD

![erd2](https://github.com/WWWWWET96/gallery/assets/76859317/56e14746-42a6-4c91-85d5-5d4f2bb92f26)


---
### URI 정리
<details>
    <summary>로그인(UserController)</summary>
    
    - home: /users(GET)  
    - 로그인: /users/login(POST)  
        - 요청 파라미터  
            - UserLoginDto  
                - String nickname  
                - String password  
    - 회원가입: /users(POST)
        - 요청 파라미터
            - UserDto
                - String nickname(로그인 시 필요한 아이디 ex. sconelee)
                - String name(유저명 ex.kim)
                - String password
                - String phone
                - String email
                - AccountStatus(enum타입)(유저에게 안보이는 항목이여야함. 초기엔 자동으로 WAITING상태로 들어가있도록)
                - 이하는 enum인 AccountStatus 정의한 것

                    
                    @Getter
                    @RequiredArgsConstructor
                    public enum AccountStatus {
                    WAITING("승인 대기"),
                    MEMBER("승인 완료"),
                    DELETED("계정 삭제"),
                    UNAVAILABLE("휴면 계정");
                    
                        private final String accountStatus;
                    
                    }
</details>

<details>
    <summary>Art(ArtController)</summary>
    
    - home: /arts(GET)
    - save: /arts(POST)
        - 요청 파라미터
            - ArtDto
                - art에 대한 소개를 사진으로 넣을지, 아니면 text로 넣을지 정해지면 추가
                - Long id
                - String author
                - String artName
                - Long price
                - String contents
                - int year
                - LocalDateTime regDate(경매 시작 날짜)
                - LocalDateTime closedDate(경매 마감 날짜)
                - Selling isSelling(enum타입)(판매중인지 여부)
                - 이하는 enum인 Selling정의한 것
                    
                
                    @Getter
                    @RequiredArgsConstructor
                    public enum Selling {
                    BEFORE("판매전"),
                    OPEN("판매중"),
                    CLOSED("판매완료");
                    
                        private final String selling;
                    }
                    
    - id에 의한 art찾기(findArtById): /arts/{id}(GET)
        - 요청 파라미터
            - Long id
    - 전체 art 찾기(findArtByAll): /arts(GET)
        - 검색 타입: author, art-name, is-sellling
        - 검색 키워드: author, art-name은 포함된 단어 전부 찾도록(like), is-selling은 enum이기 때문에 해당단어가 온전히 포함되도록(BEFORE, OPEN, CLOSED)
        - 페이지 설정: 한 페이지의 항목 수 5개, sort는 id순이며 내림차순으로 정렬
        - 요청 파라미터
            - int page(기본 0으로 세팅, 다음 페이지 이동 시 pathparam으로 page=1 이런식으로 값 설정됨)
            - String searchType(required = false)
            - String searchKeyword(required = false)
            - 예시
                - [http://localhost:8080/arts](http://localhost:8080/arts)
                    - 모든 arts 표시
                - [http://localhost:8080/arts?searchType=is-selling&searchKeyword=BEFORE](http://localhost:8080/arts?searchType=is-selling&searchKeyword=BEFORE)
                    - BEFORE(판매 전)인 모든 arts 표시
    - art 수정: /arts/{id}(PATCH)
        - 요청 파라미터
            - Long id
            - ArtUpdateDto
                - String author
                - String artName
                - Long price
                - String contents
                - int year
                - LocalDateTime regDate
                - LocalDateTime closedDate
                - Selling isSelling
    - art삭제: /arts/{id}(DELETE)
        - 요청 파라미터
            - Long id
</details>                

<details>
<summary>Applicant(ApplicantController)</summary>
  
    - home: /applicants(GET)
    - save: /applicants(POST)
        - 요청 파라미터
            - Long userId
            - Long artId
            - Long price
    - 단일 건 find: /applicants/{id}(GET)
        - 요청 파라미터
            - Long id
    - 전체 find: /applicants(GET)
    - 신청서 수정: /applicants/{id}(PATCH)
        - 요청 파라미터
            - Long id
            - Long price
    - 신청서 삭제: /applicants/{id}(DELETE)
        - 요청 파라미터
            - Long id
</details>
