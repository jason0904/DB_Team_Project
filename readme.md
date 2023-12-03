# CAU 2023-2 Database Design
## Team 1 - Stock Trading System
### Members
* **20196014 물리학과 고지흔**
* **20224680 소프트웨어학부 김경민**
* **20224566 소프트웨어학부 김동우**
* **20225779 소프트웨어학부 김제신**
* **20224280 간호학과 윤재훈**
<hr>

## To do
1. 테이블 30개 이상
      - [ ] stock_info
      - [ ] 입출금
2. 함수 3개 이상
      - [ ] 통화 변환
      - [ ] 배당 수익 계산
      - [ ] 수익률 계산
3. 프로시저 3개 이상
      - [ ] 최저가
      - [ ] 최고가
      - [ ] 환율
      - [ ] 미채결량
4. 트리거 3개 이상
      - [ ] 로그
      - [ ] 주문 유효성 검사
      - [ ] 로그인 실패 횟수 10회
5. Nomalization
6. Your program should create more than 10records for all tables
7. Your program should be able to create, read, update and delete records in your DB
8. Front and Back
      - [x] 로그인(/api/login)
         - input
          ```json
              {
                "id": "admin",
                "password_hash": "admin"
               }
          ```
          - output
          ```json
                {
                  "uID" : 1
                  "username": "admin",
                  "accountNumbers": [
                     "120312312-1231231"
                  ]
                  "account_type": [
                     "GeneralAccount"
                  ]
                }
          ```
      - [ ] 계좌 로그인 - accountNumber(account), password_hash(accountPassword) / ok, bad request
      - [ ] 종목검색 - 검색어 / itemid, name, market_name, price
      - [ ] 주문창 페이지 created - account_id, itemid / balance, 주식 종목 명, current_price, start_price, 현재 나와있는 물량(가격,수량)
      - [ ] 주문 창 - itemid, account_id, 시장가 여부, 지정가, 매수 매도 여부, 수량 / 주문 신청 성공여부
      - [ ] 주문 창(미채결 주문) - itemid, account_id / 종목명, 매매구분(매수 매도),주문단가, 미채결량,현재가, 원주문번호
      - [ ] 주문 창(정정) - itemid, account_id, 정정 가격, 원주문번호/ 정정주문번호, 정정주문 신청 성공여부
      - [ ] 주문 창(취소) - itemid, account_id, 원주문번호/ 정정(취소)주문 신청 성공여부
      - [ ] 잔고 조회 - account_id / 현재환율, 잔고에있는 종목들(종목명, 평가손익, 수익률, 보유수량, 평가금액, 비중, 매입단가, 매수금액, 현재가), 예수금, 원화 달러 여부(/api/account) -> 더 추가할예정
         - input
          ```json
          {
            "account_number" : "120312312-1231231"
            "password": "1234"
          }
          ```
         - output(아직 미완성)
          ```json
            {
                "stock_value": 2012030.0, // Account 평가금액
                "KRW_balance": 100001, // balance //예수금
                "name": 삼성전자 // item
                "평가손익": 40000  // (current_price - average_purchase_price) * quantity
                "수익률": 3 // (current_price - average_purchase_price) / average_purchase_price * 100
                "quantity": 3 // stockportfolio 보유수량
                "평가금액": 120000 // current_price * quantity
                "average_purchase_price": 90000 // 매입단가 stockportfolio
                "매수금액": 270000 // average_purchase_price * quantity
                "current_price": 40000 // 현재가 CurrentPrice
            }
          ``` 
      - [ ] 환율 기록조회 - 시작 날짜, 종료날짜, from통화, to 통화 / 날짜, 환율
      - [ ] 환전 - account_id / 원화, 달러 잔고, 현재 환율
      - [ ] 환전 신청 - account_id, from통화, to 통화, to 통화기준 금액/ 성공여부, 환전후 원화잔고, 달러잔고
      - [ ] 기업 정보 - item_id/기업정보
      - [ ] 채결 기록 - 날짜(하루), account_id/ stockTradinglog
      - [ ] 개인 정보 확인 (수정) - userid/ user, username, email, phone, street_address, city, state, country, ..
9. Front
      - [x] 주문 정정창에 등락 없애기, 취소 누르기, 정정에서 수량 칸 구매가능 주수 없애기
      - [x] 로그인 후 창에 계좌 선택 넣고 계좌를 선택해야 주문하기, 잔고확인 버튼 생기게
      - [x] vuex에 account 정보, 현재 선택된 account
      - [ ] 종목 검색페이지 갈때 request parameter로 계좌 타입도
      - [ ] 주문 신청 성공 여부 창
      - [ ] 정정신청 시 기존 주문 지우고 새주문 띄우기
      - [ ] 잔고에서 전일가 지우고 표 열 맞추기
      - [ ] 잔고에서 D+2빼고 달러 원화나눠서 표시
      - [ ] 환율확인 -> 환전 / from to 통화/ 얼마할꺼냐 / from테이블 기준으로 얼마 환전 가능한지
      - [ ] 잔고확인 창 박으로 빼기
      - [ ] 환율 조회 기간-최대한달. end date 현재날짜 까지.
      - [ ] 계좌 비밀번호 창
      - [ ] 기업 정보 - 주문 창에서 넘어갈수 있는 페이지
      - [ ] 채결 기록 잔고확인 전환 버튼 
<hr>

## DB 설계(아직 미완성) ![DB](https://github.com/jason0904/DB_Team_Project/assets/37035547/9d4bdaa5-16fa-4b5f-bef5-96d61e4945a6)
```sql
Project project_name {
  database_type: 'mySQL'
  Note: 'stock database'
}
```

### User

```sql
Table users {
  Uid integer [primary key]
  username varchar
  usertype varchar
  //country varchar
  account_status varchar //'active', 'inactive', 'locked'
  created_at timestamp
}

Table personal {
  Uid integer [primary key, ref: - users.Uid ]
  age integer
  social_id_hash varchar

}

Table organization {
  Uid integer [primary key, ref: - users.Uid ]
  businessid_hash varchar
}
Table sensitive_info {
  Uid integer [primary key, ref: - users.Uid]
  email varchar
  phone varchar
  secondary_email varchar
  secondary_phone varchar
  //security_question varchar
  //security_answer_hash varchar
}

Table address {
  address_id integer [primary key]
  Uid integer [ref: - users.Uid]
  street_address varchar
  city varchar
  state varchar
  postal_code varchar
  country varchar
}
```

### login
```sql
Table login {
  Uid integer [primary key, ref: - users.Uid]
  ID varchar //로그인 할 때 필요한 아이디
  password_hash varchar
}

Table login_log {
  log_id integer [primary key]
  Uid integer [ref: > users.Uid]
  attempt_time timestamp
  attempt_status varchar //'success', 'failure'
  ip_address varchar
}
/**
2FA 테이블 제거
필요 없어 보임
토론 필요
**/

Table certificate_auth {
  certificate_id integer [primary key]
  Uid integer [ref: - users.Uid]
  certificate_data varchar //company
  issued_at timestamp
  expires_at timestamp
}

Table otp_auth {
  otp_id integer [primary key]
  Uid integer [ref: - users.Uid]
  secret_key varchar
  devicename varchar
  last_used timestamp
}
```
