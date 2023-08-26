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
# URI 정리
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



# 회의록

<details>
    <summary>회의록 정리</summary>
    
| 날짜 | 내용 |
| --- | --- |
| 09/19 | 1. 데이터 구조 확립 및 ERD 추출<br> 2. 서비스 로직의 구체화<br>　2.1 서비스는 크게 로그인과 낙찰로.. 두 가지??(미정) |
| 10/01 | 1. 로그인하면 상단우측 <LOGIN>에서 <000님> →누르면 작은 네비바 나와서 입찰내역, 장바구니, 내정보<br>2. 서치바 클릭 → 전체화면에 팝업처럼 서치박스 나오게. 백그라운드 블러처리.<br>3. 어드민 회원가입: 이메일, 아이디, 비밀번호<br>4. 유저 회원가입: 아이디, 비번, 이메일, 폰번호, 닉네임 <br>5. 어드민 페이지 추가→ 유저 회원관리(가입 승인, 회원 강퇴), 그림관리(그림 등록/수정/삭제)<br>6. 경매 끝나도 작품 남겨두고(완료상태), 아티스트페이지 수정 → 자음순으로 나열, 아티스트 선택하면<br>상단에 아티스트 소개/하단에 작품 |
| 10/2 |1.toEntity는 service단에서 해결<br>　→ 매개변수로 받는 user_id, art_id를 key 값으로 user ,art객체를 찾아서 service단에서 직접 toEntity(user, art)로 넣어주면 됨<br>2. 회원상태랑 휴면계정상태 두 속성으로 나누면 안되는 이유<br>　→ 【비승인된 탈퇴된 회원】 이런건 애초에 말이 안되는 경우의 수임<br>3. enum은 객체인가<br>　→ O<br>4. applicantDto로 처음 신청, 가격 수정(재신청) 다 가능하도록???<br>　→user_id, art_id가 있어야 해당 데이터 찾아서 값 수정 가능하니까<br>동일하게 이용해야한다고 생각… |
| 10/18 |1. toEntity삭제 후 of로 dto↔entity convert 구현<br>2. 스프링시큐리티 로그인 수정 필요<br>　→ loadUserByUsername()함수 삭제 후 jwt로 구현?<br>　→ jwt을 사용하면 토큰을 통해 로그인하는듯<br>　→JWT는 토큰 자체에 유저 정보를 담아서 암호화한 토큰<br>　https://velog.io/@jkijki12/Spirng-Security-Jwt-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0<br>　https://inpa.tistory.com/m/entry/WEB-%F0%9F%93%9A-JWTjson-web-token-%EB%9E%80-%F0%9F%92%AF-%EC%A0%95%EB%A6%AC<br>3. 프로젝트에 대한 정책/api명세서 필요<br>4. 테스트 코드 작성 시,<br>　→ @SpringBootTest : 무거움, 통합 테스트 시 이용<br>　→ @DataJpaTest : 가벼움, 자체db만들어서 테스트 진행, repository테스트 시 유용<br>　→ @Mock : Controller, Service의 단체테스트 진행 시 사용. 의존 빈을 직접주입하는 것이 아닌, mock의 형태로 주입하여 테스트 진행<br>　→ test-resources-application.yml, db정보 없다면 main-resources-application.yml의 db정보 이용하여 db연결함 (테스트는 <br>　테스트용 db 사용하도록 지향. )<br>5. 유효성 검사는 프론트에서1차, 백에서2차 이뤄지는게 맞다고 봄<br>　→ 맨 처음 컨트롤러로 들어올 때 dto에서 유효성 검사 이뤄지도록<br>　→ entity에 들어올 때 유효성 검사 이뤄져도 똑같을듯? |
| 10/23 |1. 반환값: 회원아이디(string) → 00님, pk아이디값/유저정보 (회원가입)<br>2. 유효성검사(프론트/백에서 한번씩): 회원가입, 그림 등록시간 현재보다 과거로 되지 않게 <br>3. api 명세서 작성<br>4. 회원가입, 로그인 화면단만들기 + 비동기통신 (통신해보기) |
| 11/1 | 1. admin → 회원가입 시, admin_role입력(Manager) |

</details>
