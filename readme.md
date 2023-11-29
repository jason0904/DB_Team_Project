# CAU 2023-2 Database Design
## Team 1 - Stock Trading System
### Members
* **20196014 물리학과 고지흔**
* **20224680 소프트웨어학부 김경민**
* **20224566 소프트웨어학부 김동우**
* **20225779 소프트웨어학부 김제신**
* **20224280 소프트웨어학부 윤재훈**
<hr>

## To do
- [ ] 1. 테이블 30개 이상
- [ ] 2. 함수 3개 이상
- [ ] 3. 프로시저 3개 이상
- [ ] 4. 트리거 3개 이상
- [ ] 5. Nomalization
- [ ] 6. Your program should create more than 10records for all tables
- [ ] 7. Your program should be able to create, read, update and delete records in your DB

<hr>

## DB 설계
```
Project project_name {
  database_type: 'mySQL'
  Note: 'stock database'
}
```

### User

```
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
```
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
