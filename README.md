# 아티스트 굿즈 자유거래 플랫폼
<aside>
🌲 master

- dev
    - dev-front
        - feature-front-기능명
        - feature-front-mainpage
    - dev-back
        - feature-back-기능명
    - fix : 문제가 생긴 브랜치에서 분기
        - fix-front-기능명
        - fix-back-기능명
- docs/문서타입[ex) README, ppt]
</aside>

브랜치 전략
- 젠킨스 배포를 위해 백, 프론트로 크게 브랜치를 나눔(dev-).
- dev-front, dev-back에서 각각 브랜치를 파서 작업 후, 완성한 기능은 머지.
- 문제가 생긴 브랜치에서 분기(fix-)해서 수정 후 정상화하면 머지(merge).



# 아티스트 NFT 큐레이팅 플랫폼 - FANFTASY

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2013aca7-900c-4bc0-a445-4458f4525c61/Untitled.png)

---

# 🚀 프로젝트 소개

## 프로젝트 진행 기간

- 2022.02.20(월) ~ 2022.04.07(금)

## 기획 의도

NFT의 3가지 주요 요소인 투자가치성, 희소성, 소장가치를 모두 충족하는 상품 -> **아티스트 굿즈**

## 주요 기능

- 민팅 기능
    - ERC 721을 사용하여 다양한 타입의 NFT들을 민팅할 수 있습니다.
    - 민팅된 NFT는 드롭스 페이지에 자동으로 게시됩니다.
- 구매 및 판매 기능
    - 드롭스와 마켓 플레이스의 NFT를 구매할 수 있습니다.
    - 구매한 NFT를 마이 페이지에서 판매가를 산정하여 마켓플레이스에 게시할 수 있습니다.

---

# 🛠️ 기술 스택

### Backend

- Spring boot 2.7.9
- JDK
- JPA
- Spring Security
- Swagger 3.0
- MySQL

### FrontEnd

- Vue3
- Vuetify 3
- Web3.js
- Web3.j
- Vuex
- Vue-router

### Block Chain

- Solidity ≥ 0.7.0 <0.9.0
- OpenZeppelin

### CI/CD

- Jenkins
- Docker
- AWS
- Nginx

---

# 📐 아키텍처 및 파일 구조

## 아키텍처

![특화_아키텍쳐.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/aa0b5a08-a1a1-46ac-b31f-eef6018569fd/%ED%8A%B9%ED%99%94_%EC%95%84%ED%82%A4%ED%85%8D%EC%B3%90.png)

## 파일 구조

### Frontend

- project tree
    
    ```bash
    frontend
    ├─ .gitignore
    ├─ babel.config.js
    ├─ jsconfig.json
    ├─ package-lock.json
    ├─ package.json
    ├─ path
    │  ├─ contracts
    │  │  ├─ NFT.sol
    │  │  ├─ Sale.sol
    │  │  └─ SaleFactory.sol
    │  └─ to
    │     ├─ BankABI.json
    │     ├─ NFTABI.json
    │     ├─ SaleABI.json
    │     └─ SaleFactoryABI.json
    ├─ path.zip
    ├─ public
    │  ├─ favicon.ico
    │  ├─ index.html
    │  └─ logo.png
    ├─ README.md
    ├─ src
    │  ├─ App.vue
    │  ├─ assets
    │  │  ├─ arrow-up-circle.png
    │  │  ├─ ...
    │  │  ├─ fonts
    │  │  │  ├─ KCC-Ganpan.eot
    │  │  │  ├─ ...
    │  │  │  └─ NotoSansKR-Medium.otf
    │  │  ├─ ...
    │  │  └─ 흰배경.png
    │  ├─ components
    │  │  ├─ headers
    │  │  │  ├─ AllFooter.vue
    │  │  │  ├─ LoginUserHeaders.vue
    │  │  │  └─ NotLoginUserHeaders.vue
    │  │  ├─ HelloWorld.vue
    │  │  ├─ mainpage
    │  │  │  ├─ anim.vue
    │  │  │  ├─ NFTphoto.vue
    │  │  │  └─ RankingCard.vue
    │  │  ├─ market
    │  │  │  └─ NFTCard.vue
    │  │  ├─ mypage
    │  │  │  └─ modal.vue
    │  │  └─ resell
    │  │     └─ NFTCard.vue
    │  ├─ main.js
    │  ├─ plugins
    │  │  ├─ vuetify.js
    │  │  └─ webfontloader.js
    │  ├─ router
    │  │  └─ index.js
    │  ├─ store
    │  │  └─ index.js
    │  └─ views
    │     ├─ AboutView.vue
    │     ├─ account
    │     │  ├─ MyPageUpdateView.vue
    │     │  ├─ MyPageView.vue
    │     │  ├─ SignInView.vue
    │     │  ├─ SignUpSelectView.vue
    │     │  ├─ SignUpView.vue
    │     │  └─ UserPageView.vue
    │     ├─ chatting
    │     │  ├─ ChattingListView.vue
    │     │  └─ ChattingRoomView.vue
    │     ├─ community
    │     │  ├─ CommunityAddView.vue
    │     │  ├─ CommunityDetailView.vue
    │     │  └─ CommunityMainView.vue
    │     ├─ mainpage
    │     │  └─ HomeView.vue
    │     ├─ market
    │     │  ├─ MarketAddView.vue
    │     │  ├─ MarketDetailView.vue
    │     │  └─ MarketListView.vue
    │     └─ resell
    │        ├─ ResellDetailView.vue
    │        ├─ ResellListView.vue
    │        └─ UserNFTView.vue
    └─ vue.config.js
    ```
    

### Backend

- project tree

### Blockchain

- project tree

---

# 🧑‍🤝‍🧑 협업 환경

- MatterMost
    - 의견 조율
- Gitlab
    - 코드 버전 관리 용도
- Notion
    - 프로젝트 정리
    - 회의록, 개발 컨벤션, 참고 문서 등 정리
        - 매일 아침마다 진행한 데일리 스크럼 결과 내용 정리
- JIRA
    - 매주 작업량 관리 용도

---

# ⛄ 프로젝트 인원

| 김성환 | 신선호 | 심호연 | 장근우 | 조민수 | 최현제 |
| --- | --- | --- | --- | --- | --- |
| BackendBlockchain | Frontend | FrontendBlockchain | Frontend | Backend | Backend |

---
