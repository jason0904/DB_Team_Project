
INSERT INTO User (username, usertype, account_status, created_at, updated_at)
VALUES
    ('hong_gildong', 'personal', 'active', NOW(), NOW()),
    ('kim_minsu', 'organization', 'active', NOW(), NOW()),
    ('lee_jieun', 'personal', 'inactive', NOW(), NOW()),
    ('park_jisung', 'organization', 'active', NOW(), NOW()),
    ('choi_yeojin', 'personal', 'active', NOW(), NOW()),
    ('user6', 'personal', 'active', NOW(), NOW()),
    ('user7', 'organization', 'active', NOW(), NOW()),
    ('user8', 'personal', 'inactive', NOW(), NOW()),
    ('user9', 'organization', 'active', NOW(), NOW()),
    ('user10', 'personal', 'active', NOW(), NOW()),
    ('user11', 'personal', 'active', NOW(), NOW()),
    ('user12', 'organization', 'active', NOW(), NOW()),
    ('user13', 'personal', 'inactive', NOW(), NOW()),
    ('user14', 'organization', 'active', NOW(), NOW()),
    ('user15', 'personal', 'active', NOW(), NOW()),
    ('user16', 'personal', 'active', NOW(), NOW()),
    ('user17', 'organization', 'active', NOW(), NOW()),
    ('user18', 'personal', 'inactive', NOW(), NOW()),
    ('user19', 'organization', 'active', NOW(), NOW()),
    ('user20', 'personal', 'active', NOW(), NOW());

INSERT INTO Personal (uid, social_id_hash)
VALUES
    (1, MD5(CONCAT('social_id_1', 'abc'))),
    (3, MD5(CONCAT('social_id_3', 'abc'))),
    (5, MD5(CONCAT('social_id_5', 'abc'))),
    (6, MD5(CONCAT('social_id_6', 'abc'))),
    (8, MD5(CONCAT('social_id_8', 'abc'))),
    (10, MD5(CONCAT('social_id_10', 'abc'))),
    (11, MD5(CONCAT('social_id_11', 'abc'))),
    (13, MD5(CONCAT('social_id_13', 'abc'))),
    (15, MD5(CONCAT('social_id_15', 'abc'))),
    (18, MD5(CONCAT('social_id_18', 'abc')));

INSERT INTO Organization (uid, business_id_hash)
VALUES
    (2, MD5(CONCAT('business_id_2', 'abc'))),
    (4, MD5(CONCAT('business_id_4', 'abc'))),
    (7, MD5(CONCAT('business_id_7', 'abc'))),
    (9, MD5(CONCAT('business_id_9', 'abc'))),
    (12, MD5(CONCAT('business_id_12', 'abc'))),
    (14, MD5(CONCAT('business_id_14', 'abc'))),
    (16, MD5(CONCAT('business_id_16', 'abc'))),
    (17, MD5(CONCAT('business_id_17', 'abc'))),
    (19, MD5(CONCAT('business_id_19', 'abc'))),
    (20, MD5(CONCAT('business_id_20', 'abc')));

-- UserLoginMeta 테이블에 레코드 삽입
INSERT INTO UserLoginMeta (uid, login_id, login_attempt)
VALUES
    (1, 'login_id_1', 0),
    (2, 'login_id_2', 0),
    (3, 'login_id_3', 0),
    (4, 'login_id_4', 0),
    (5, 'login_id_5', 0),
    (6, 'login_id_6', 0),
    (7, 'login_id_7', 0),
    (8, 'login_id_8', 0),
    (9, 'login_id_9', 0),
    (10, 'login_id_10', 0),
    (11, 'login_id_11', 0),
    (12, 'login_id_12', 0),
    (13, 'login_id_13', 0),
    (14, 'login_id_14', 0),
    (15, 'login_id_15', 0),
    (16, 'login_id_16', 0),
    (17, 'login_id_17', 0),
    (18, 'login_id_18', 0),
    (19, 'login_id_19', 0),
    (20, 'login_id_20', 0);

-- UserLoginPassWord 테이블에 레코드 삽입
INSERT INTO UserLoginPassword (uid, password_hash, updated_at)
VALUES
    (1, MD5(CONCAT('password_hash_1', 'abc')), NOW()),
    (2, MD5(CONCAT('password_hash_2', 'abc')), NOW()),
    (3, MD5(CONCAT('password_hash_3', 'abc')), NOW()),
    (4, MD5(CONCAT('password_hash_4', 'abc')), NOW()),
    (5, MD5(CONCAT('password_hash_5', 'abc')), NOW()),
    (6, MD5(CONCAT('password_hash_6', 'abc')), NOW()),
    (7, MD5(CONCAT('password_hash_7', 'abc')), NOW()),
    (8, MD5(CONCAT('password_hash_8', 'abc')), NOW()),
    (9, MD5(CONCAT('password_hash_9', 'abc')), NOW()),
    (10, MD5(CONCAT('password_hash_10', 'abc')), NOW()),
    (11, MD5(CONCAT('password_hash_11', 'abc')), NOW()),
    (12, MD5(CONCAT('password_hash_12', 'abc')), NOW()),
    (13, MD5(CONCAT('password_hash_13', 'abc')), NOW()),
    (14, MD5(CONCAT('password_hash_14', 'abc')), NOW()),
    (15, MD5(CONCAT('password_hash_15', 'abc')), NOW()),
    (16, MD5(CONCAT('password_hash_16', 'abc')), NOW()),
    (17, MD5(CONCAT('password_hash_17', 'abc')), NOW()),
    (18, MD5(CONCAT('password_hash_18', 'abc')), NOW()),
    (19, MD5(CONCAT('password_hash_19', 'abc')), NOW()),
    (20, MD5(CONCAT('password_hash_20', 'abc')), NOW());

-- UserLoginQuestion 테이블에 레코드 삽입
INSERT INTO UserLoginQuestion (uid, security_question, security_answer_hash)
VALUES
    (1, 'Your first pets name?', MD5(CONCAT('answer_hash_1', 'abc'))),
    (2, 'Your favorite movie?', MD5(CONCAT('answer_hash_2', 'abc'))),
    (3, 'Your best friend''s name?', MD5(CONCAT('answer_hash_3', 'abc'))),
    (4, 'Your childhood nickname?', MD5(CONCAT('answer_hash_4', 'abc'))),
    (5, 'The name of your first school?', MD5(CONCAT('answer_hash_5', 'abc'))),
    (6, 'Your favorite food?', MD5(CONCAT('answer_hash_6', 'abc'))),
    (7, 'Your favorite color?', MD5(CONCAT('answer_hash_7', 'abc'))),
    (8, 'Your favorite book?', MD5(CONCAT('answer_hash_8', 'abc'))),
    (9, 'Your favorite song?', MD5(CONCAT('answer_hash_9', 'abc'))),
    (10, 'Your favorite animal?', MD5(CONCAT('answer_hash_10', 'abc'))),
    (11, 'Your favorite movie?', MD5(CONCAT('answer_hash_11', 'abc'))),
    (12, 'Your favorite movie?', MD5(CONCAT('answer_hash_12', 'abc'))),
    (13, 'Your favorite movie?', MD5(CONCAT('answer_hash_13', 'abc'))),
    (14, 'Your favorite movie?', MD5(CONCAT('answer_hash_14', 'abc'))),
    (15, 'Your favorite movie?', MD5(CONCAT('answer_hash_15', 'abc'))),
    (16, 'Your favorite movie?', MD5(CONCAT('answer_hash_16', 'abc'))),
    (17, 'Your favorite movie?', MD5(CONCAT('answer_hash_17', 'abc'))),
    (18, 'Your favorite movie?', MD5(CONCAT('answer_hash_18', 'abc'))),
    (19, 'Your favorite movie?', MD5(CONCAT('answer_hash_19', 'abc'))),
    (20, 'Your favorite movie?', MD5(CONCAT('answer_hash_20', 'abc')));

-- UserLoginLog 테이블에 레코드 삽입
INSERT INTO UserLoginLog (uid, login_time, login_status)
VALUES
    (1, NOW(), 'success'),
    (2, NOW(), 'fail'),
    (3, NOW(), 'success'),
    (4, NOW(), 'success'),
    (5, NOW(), 'fail'),
    (6, NOW(), 'success'),
    (7, NOW(), 'success'),
    (8, NOW(), 'fail'),
    (9, NOW(), 'success'),
    (10, NOW(), 'success'),
    (11, NOW(), 'success'),
    (12, NOW(), 'fail'),
    (13, NOW(), 'success'),
    (14, NOW(), 'success'),
    (15, NOW(), 'fail'),
    (16, NOW(), 'success'),
    (17, NOW(), 'success'),
    (18, NOW(), 'success'),
    (19, NOW(), 'fail'),
    (20, NOW(), 'success'),
    (1, NOW(), 'success'),
    (2, NOW(), 'fail');

-- SensitiveInfo 테이블에 레코드 삽입
INSERT INTO SensitiveInfo (uid, email, phone, age, sex)
VALUES
    (1, 'john@example.com', '010-1234-5679', 30, 'Male'),
    (2, 'kim@example.com', '010-1234-5678', -1, 'Organization'),
    (3, 'lee@example.com', '010-1234-5978', 25, 'Female'),
    (4, 'park@example.com', '010-1234-9783', -1, 'Organization'),
    (5, 'choi@example.com', '010-1234-5999', 28, 'Male'),
    (6, 'user6@example.com', '010-9994-5999', 31, 'Male'),
    (7, 'org7@example.com', '010-1294-5999', -1, 'Organization'),
    (8, 'user8@example.com', '010-9234-5999', 26, 'Female'),
    (9, 'org9@example.com', '010-1234-5991', -1, 'Organization'),
    (10, 'user10@example.com', '010-1994-5999', 32, 'Male'),
    (11, 'johnn@example.com', '010-1237-5679', 30, 'Male'),
    (12, 'kimn@example.com', '010-1734-5678', -1, 'Organization'),
    (13, 'leen@example.com', '010-1774-5978', 25, 'Female'),
    (14, 'parkn@example.com', '010-1634-9783', -1, 'Organization'),
    (15, 'choni@example.com', '010-1264-5999', 28, 'Male'),
    (16, 'org16@example.com', '010-9554-5999', 31, 'Organization'),
    (17, 'org17@example.com', '010-1444-5999', -1, 'Organization'),
    (18, 'user18@example.com', '010-9334-5999', 26, 'Female'),
    (19, 'org19@example.com', '010-1334-5991', -1, 'Organization'),
    (20, 'org29@example.com', '010-1294-5999', 32, 'Organization');

-- AdditionalContactInfo 테이블에 레코드 삽입
INSERT INTO AdditionalContactInfo (uid, ContactType, ContactValue)
VALUES
    (1, 'secondary_email', 'john.secondary@example.com'),
    (2, 'secondary_email', 'kim.secondary@example.com'),
    (3, 'secondary_email', 'lee.secondary@example.com'),
    (4, 'secondary_email', 'park.secondary@example.com'),
    (5, 'secondary_email', 'choi.secondary@example.com'),
    (6, 'secondary_email', 'six.secondary@example.com'),
    (7, 'secondary_email', 'seven.secondary@example.com'),
    (8, 'secondary_email', 'eight.secondary@example.com'),
    (9, 'secondary_phone', '010-0234-5992'),
    (10, 'secondary_phone', '010-0234-5993');

-- Address 테이블에 레코드 삽입
INSERT INTO Address (uid, address, postal_code, country)
VALUES
    (1, '123 Main St', 12345, 'Korea'),
    (2, '456 Side St', 23456, 'CountryName'),
    (3, '789 Cross Ave', 34567, 'CountryName'),
    (4, '12 River Rd', 45678, 'CountryName'),
    (5, '1314 Ocean Blvd', 56789, 'CountryName'),
    (6, '123 Main St', 12345, 'CountryName'),
    (7, '456 Side St', 23456, 'CountryName'),
    (8, '789 Cross Ave', 34567, 'CountryName'),
    (9, '12 River Rd', 45678, 'CountryName'),
    (10, '1314 Ocean Blvd', 56789, 'CountryName'),
    (11, '123 Main St', 12345, 'CountryName'),
    (12, '456 Side St', 23456, 'CountryName'),
    (13, '789 Cross Ave', 34567, 'CountryName'),
    (14, '12 River Rd', 45678, 'CountryName'),
    (15, '1314 Ocean Blvd', 56789, 'CountryName'),
    (16, '123 Main St', 12345, 'CountryName'),
    (17, '456 Side St', 23456, 'CountryName'),
    (18, '789 Cross Ave', 34567, 'CountryName'),
    (19, '12 River Rd', 45678, 'CountryName'),
    (20, '1314 Ocean Blvd', 56789, 'CountryName');

INSERT INTO Account (uid, account_number, account_type, stock_value, status, created_at, updated_at)
VALUES
    (1, '12300000000', 'general', 10000.00, 'active', NOW(), NOW()),
    (2, '23400000000', 'gold', 20000.00, 'active', NOW(), NOW()),
    (3, '34500000000', 'general', 15000.00, 'active', NOW(), NOW()),
    (4, '45600000000', 'gold', 30000.00, 'active', NOW(), NOW()),
    (5, '56700000000', 'general', 5000.00, 'active', NOW(), NOW()),
    (6, '67800000000', 'general', 10000.00, 'active', NOW(), NOW()),
    (7, '78900000000', 'gold', 20000.00, 'active', NOW(), NOW()),
    (8, '89000000000', 'general', 15000.00, 'active', NOW(), NOW()),
    (9, '90100000000', 'gold', 30000.00, 'active', NOW(), NOW()),
    (10, '10100000000', 'general', 5000.00, 'active', NOW(), NOW()),
    (11, '11100000000', 'general', 10000.00, 'active', NOW(), NOW()),
    (12, '12100000000', 'gold', 20000.00, 'active', NOW(), NOW()),
    (13, '13100000000', 'general', 15000.00, 'active', NOW(), NOW()),
    (14, '14100000000', 'gold', 30000.00, 'inactive', NOW(), NOW()),
    (15, '15100000000', 'general', 5000.00, 'inactive', NOW(), NOW()),
    (16, '16100000000', 'general', 10000.00, 'inactive', NOW(), NOW()),
    (17, '17100000000', 'gold', 20000.00, 'inactive', NOW(), NOW()),
    (18, '18100000000', 'general', 15000.00, 'inactive', NOW(), NOW()),
    (19, '19100000000', 'gold', 30000.00, 'inactive', NOW(), NOW()),
    (20, '20100000000', 'general', 5000.00, 'inactive', NOW(), NOW()),
    (19, '19200000000', 'gold', 30000.00, 'inactive', NOW(), NOW()),
    (19, '19300000000', 'gold', 30000.00, 'inactive', NOW(), NOW());

-- AccountLoginPasswWord 테이블에 레코드 삽입
INSERT INTO AccountLoginPassword (account_id, password_hash, updated_at)
VALUES
    (1, MD5(CONCAT('account_password_hash_1', 'abc')), NOW()),
    (2, MD5(CONCAT('account_password_hash_2', 'abc')), NOW()),
    (3, MD5(CONCAT('account_password_hash_3', 'abc')), NOW()),
    (4, MD5(CONCAT('account_password_hash_4', 'abc')), NOW()),
    (5, MD5(CONCAT('account_password_hash_5', 'abc')), NOW()),
    (6, MD5(CONCAT('account_password_hash_6', 'abc')), NOW()),
    (7, MD5(CONCAT('account_password_hash_7', 'abc')), NOW()),
    (8, MD5(CONCAT('account_password_hash_8', 'abc')), NOW()),
    (9, MD5(CONCAT('account_password_hash_9', 'abc')), NOW()),
    (10, MD5(CONCAT('account_password_hash_10', 'abc')), NOW()),
    (11, MD5(CONCAT('account_password_hash_11', 'abc')), NOW()),
    (12, MD5(CONCAT('account_password_hash_12', 'abc')), NOW()),
    (13, MD5(CONCAT('account_password_hash_13', 'abc')), NOW()),
    (14, MD5(CONCAT('account_password_hash_14', 'abc')), NOW()),
    (15, MD5(CONCAT('account_password_hash_15', 'abc')), NOW()),
    (16, MD5(CONCAT('account_password_hash_16', 'abc')), NOW()),
    (17, MD5(CONCAT('account_password_hash_17', 'abc')), NOW()),
    (18, MD5(CONCAT('account_password_hash_18', 'abc')), NOW()),
    (19, MD5(CONCAT('account_password_hash_19', 'abc')), NOW()),
    (20, MD5(CONCAT('account_password_hash_20', 'abc')), NOW());

INSERT INTO AccountLoginMeta (account_id, login_attempt)
VALUES
    (1, 0),
    (2, 1),
    (3, 0),
    (4, 2),
    (5, 0),
    (6, 0),
    (7, 0),
    (8, 0),
    (9, 0),
    (10, 0),
    (11, 0),
    (12, 0),
    (13, 0),
    (14, 0),
    (15, 0),
    (16, 0),
    (17, 0),
    (18, 0),
    (19, 0),
    (20, 0);

INSERT INTO AccountLoginLog (account_id, login_time, login_status)
VALUES
    (1, NOW(), 'success'),
    (1, NOW(), 'success'),
    (1, NOW(), 'success'),
    (1, NOW(), 'success'),
    (1, NOW(), 'success'),
    (2, NOW(), 'fail'),
    (3, NOW(), 'success'),
    (4, NOW(), 'success'),
    (5, NOW(), 'fail'),
    (6, NOW(), 'success'),
    (7, NOW(), 'success'),
    (8, NOW(), 'fail'),
    (9, NOW(), 'success'),
    (10, NOW(), 'success'),
    (11, NOW(), 'success'),
    (12, NOW(), 'fail'),
    (13, NOW(), 'success'),
    (18, NOW(), 'success'),
    (19, NOW(), 'fail'),
    (20, NOW(), 'success');

-- Balance 테이블에 레코드 삽입
INSERT INTO Balance (account_id, Total_balance, KRW_balance, USD_balance)
VALUES
    (1, 10000.00, 8000.00, 2.00),
    (2, 20000.00, 15000.00, 5.00),
    (3, 15000.00, 10000.00, 5.00),
    (4, 30000.00, 25000.00, 5.00),
    (5, 5000.00, 4000.00, 1.00),
    (6, 10000.00, 8000.00, 2.00),
    (7, 20000.00, 15000.00, 5.00),
    (8, 15000.00, 10000.00, 5.00),
    (9, 30000.00, 25000.00, 5.00),
    (10, 5000.00, 4000.00, 1.00),
    (11, 10000.00, 8000.00, 2.00),
    (12, 20000.00, 15000.00, 5.00),
    (13, 15000.00, 10000.00, 5.00),
    (14, 30000.00, 25000.00, 5.00),
    (15, 5000.00, 4000.00, 1.00),
    (16, 10000.00, 8000.00, 2.00),
    (17, 20000.00, 15000.00, 5.00),
    (18, 15000.00, 10000.00, 5.00),
    (19, 30000.00, 25000.00, 5.00),
    (20, 5000.00, 4000.00, 1.00);

-- GeneralAccount 및 GoldAccount 테이블에 레코드 삽입
INSERT INTO GeneralAccount (account_id, Available_offer_Type)
VALUES
    (1, 'Offer_Type_1'),
    (3, 'Offer_Type_2'),
    (5, 'Offer_Type_3'),
    (6, 'Offer_Type_4'),
    (8, 'Offer_Type_5'),
    (10, 'Offer_Type_6'),
    (11, 'Offer_Type_7'),
    (13, 'Offer_Type_8'),
    (15, 'Offer_Type_9'),
    (16, 'Offer_Type_10'),
    (18, 'Offer_Type_11'),
    (20, 'Offer_Type_12');

INSERT INTO GoldAccount (account_id, Available_offer_Type)
VALUES
    (2, 'Gold_Offer_Type_1'),
    (4, 'Gold_Offer_Type_2'),
    (7, 'Gold_Offer_Type_3'),
    (9, 'Gold_Offer_Type_4'),
    (12, 'Gold_Offer_Type_5'),
    (14, 'Gold_Offer_Type_6'),
    (17, 'Gold_Offer_Type_7'),
    (19, 'Gold_Offer_Type_8'),
    (21, 'Gold_Offer_Type_9'),
    (22, 'Gold_Offer_Type_10');

-- Item, ItemPriceInfo, CurrentPrice 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO Item (symbol, name, item_type, market, created_at)
VALUES
    ('AAPL', 'Apple Inc.', 'Stock', 'NYSE', NOW()),
    ('Stock1', 'Stock1', 'Stock', 'KOSPI', NOW()),
    ('Stock2', 'Stock2', 'Stock', 'KOSPI', NOW()),
    ('Stock3', 'Stock3', 'Stock', 'KOSPI', NOW()),
    ('Stock4', 'Stock4', 'Stock', 'KOSPI', NOW()),
    ('Stock5', 'Stock5', 'Stock', 'KOSPI', NOW()),
    ('Stock6', 'Stock6', 'Stock', 'KOSPI', NOW()),
    ('Stock7', 'Stock7', 'Stock', 'KOSPI', NOW()),
    ('Stock8', 'Stock8', 'Stock', 'KOSPI', NOW()),
    ('Stock9', 'Stock9', 'Stock', 'KOSPI', NOW()),
    ('Bond1', 'Bond_samsung', 'Bond', 'Bond', NOW()),
    ('Bond2', 'Bond2', 'Bond', 'Bond', NOW()),
    ('Bond3', 'Bond3', 'Bond', 'Bond', NOW()),
    ('Bond4', 'Bond4', 'Bond', 'Bond', NOW()),
    ('Bond5', 'Bond5', 'Bond', 'Bond', NOW()),
    ('Bond6', 'Bond6', 'Bond', 'Bond', NOW()),
    ('Bond7', 'Bond7', 'Bond', 'Bond', NOW()),
    ('Bond8', 'Bond8', 'Bond', 'Bond', NOW()),
    ('Bond9', 'Bond9', 'Bond', 'Bond', NOW()),
    ('Bond10', 'Bond10', 'Bond', 'Bond', NOW()),
    ('Gold1', 'Gold', 'Gold', 'Gold', NOW()),
    ('Gold2', 'Gold2', 'Gold', 'Gold', NOW()),
    ('Gold3', 'Gold3', 'Gold', 'Gold', NOW()),
    ('Gold4', 'Gold4', 'Gold', 'Gold', NOW()),
    ('Gold5', 'Gold5', 'Gold', 'Gold', NOW()),
    ('Gold6', 'Gold6', 'Gold', 'Gold', NOW()),
    ('Gold7', 'Gold7', 'Gold', 'Gold', NOW()),
    ('Gold8', 'Gold8', 'Gold', 'Gold', NOW()),
    ('Gold9', 'Gold9', 'Gold', 'Gold', NOW()),
    ('Gold10', 'Gold10', 'Gold', 'Gold', NOW());

-- ItemPortfolio 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO ItemPortfolio (account_id, item_id, quantity, total_purchase_price, current_price, created_at)
VALUES
    (1, 1, 10, 1000.00, 1100.00, NOW()),
    (2, 2, 20, 2000.00, 2100.00, NOW()),
    (3, 4, 2, 200.00, 210.00, NOW()),
    (3, 5, 3, 300.00, 310.00, NOW()),
    (3, 6, 4, 400.00, 410.00, NOW()),
    (3, 7, 5, 500.00, 510.00, NOW()),
    (3, 8, 6, 600.00, 610.00, NOW()),
    (3, 9, 7, 700.00, 710.00, NOW()),
    (3, 10, 8, 800.00, 810.00, NOW());

-- Order, BuyOrder, SellOrder 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO `Order` (account_id, item_id, purchase_type, order_status, created_at, success_at)
VALUES
    (1, 1, 'buyorder', 'pending', NOW(), NULL),
    (2, 2, 'sellorder', 'success', NOW(), NOW()),
    (3, 3, 'buyorder', 'pending', NOW(), NULL),
    (3, 4, 'sellorder', 'success', NOW(), NOW()),
    (3, 5, 'buyorder', 'pending', NOW(), NULL),
    (3, 6, 'sellorder', 'success', NOW(), NOW()),
    (3, 7, 'buyorder', 'pending', NOW(), NULL),
    (3, 8, 'sellorder', 'success', NOW(), NOW()),
    (3, 9, 'buyorder', 'pending', NOW(), NULL),
    (3, 10, 'sellorder', 'success', NOW(), NOW()),
    (3, 10, 'buyorder', 'cancelled', NOW(), NULL),
    (3, 10, 'sellorder', 'success', NOW(), NOW()),
    (3, 10, 'buyorder', 'cancelled', NOW(), NULL),
    (3, 10, 'sellorder', 'success', NOW(), NOW()),
    (3, 10, 'buyorder', 'cancelled', NOW(), NULL),
    (3, 10, 'sellorder', 'success', NOW(), NOW()),
    (3, 10, 'buyorder', 'cancelled', NOW(), NULL),
    (3, 10, 'sellorder', 'success', NOW(), NOW()),
    (3, 10, 'buyorder', 'cancelled', NOW(), NULL),
    (3, 10, 'sellorder', 'success', NOW(), NOW());

INSERT INTO BuyOrder (order_id, quantity, order_type, limit_price)
VALUES
    (1, 10, 'market', 100.00),
    (3, 20, 'limit', 100.00),
    (5, 30, 'market', 100.00),
    (7, 40, 'limit', 100.00),
    (9, 50, 'market', 100.00),
    (11, 60, 'limit', 100.00),
    (13, 70, 'market', 100.00),
    (15, 80, 'limit', 100.00),
    (17, 90, 'market', 100.00),
    (19, 100, 'limit', 100.00);

INSERT INTO SellOrder (order_id, quantity, order_type, limit_price)
VALUES
    (2, 20, 'limit', 105.00),
    (4, 30, 'market', 105.00),
    (6, 40, 'limit', 105.00),
    (8, 50, 'market', 105.00),
    (10, 60, 'limit', 105.00),
    (12, 70, 'market', 105.00),
    (14, 80, 'limit', 105.00),
    (16, 90, 'market', 105.00),
    (18, 100, 'limit', 105.00),
    (20, 110, 'market', 105.00);

-- CurrentExchangeRate 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO CurrentExchangeRate (base_currency, foreign_currency, current_exchange_rate, updated_at)
VALUES
    ('USD', 'KRW', 1000.00, NOW()),
    ('KRW', 'USD', 0.001, NOW()),
    -- 아래는 안쓸꺼
    ('USD', 'JPY', 100.00, NOW()),
    ('USD', 'EUR', 1.00, NOW()),
    ('USD', 'CNY', 10.00, NOW()),
    ('USD', 'GBP', 0.80, NOW()),
    ('USD', 'CAD', 1.20, NOW()),
    ('USD', 'AUD', 1.30, NOW()),
    ('USD', 'NZD', 1.40, NOW()),
    ('USD', 'CHF', 1.10, NOW()),
    ('USD', 'HKD', 7.80, NOW());

-- CurrencyExchange 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO CurrencyExchange (account_id, base_currency, foreign_currency, base_amount, foreign_amount, exchange_time)
VALUES
    (1, 'USD', 'KRW', 1.00, 1000.00, NOW()),
    (2, 'USD', 'KRW', 2.00, 2000.00, NOW()- INTERVAL 1 DAY),
    (3, 'USD', 'KRW', 3.00, 3000.00, NOW()- INTERVAL 2 DAY),
    (4, 'USD', 'KRW', 4.00, 4000.00, NOW()- INTERVAL 3 DAY),
    (5, 'USD', 'KRW', 5.00, 5000.00, NOW()- INTERVAL 4 DAY),
    (6, 'USD', 'KRW', 6.00, 6000.00, NOW()- INTERVAL 5 DAY),
    (7, 'USD', 'KRW', 7.00, 7000.00, NOW()- INTERVAL 6 DAY),
    (8, 'USD', 'KRW', 8.00, 8000.00, NOW()- INTERVAL 7 DAY),
    (9, 'USD', 'KRW', 9.00, 9000.00, NOW()- INTERVAL 8 DAY),
    (10, 'USD', 'KRW', 10.00, 10000.00, NOW()- INTERVAL 9 DAY);

-- ExchangeRateHistory 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO ExchangeRateHistory (base_currency, foreign_currency, created_at, exchange_rate)
VALUES
    ('USD', 'KRW', NOW(), 1000.00),
    ('USD', 'KRW', NOW() - INTERVAL 1 DAY, 1000.00),
    ('USD', 'KRW', NOW() - INTERVAL 2 DAY, 1000.00),
    ('USD', 'KRW', NOW() - INTERVAL 3 DAY, 1000.00),
    ('USD', 'KRW', NOW() - INTERVAL 4 DAY, 1000.00),
    ('USD', 'KRW', NOW() - INTERVAL 5 DAY, 1000.00),
    ('USD', 'KRW', NOW() - INTERVAL 6 DAY, 1000.00),
    ('USD', 'KRW', NOW() - INTERVAL 7 DAY, 1000.00),
    ('USD', 'KRW', NOW() - INTERVAL 8 DAY, 1000.00),
    ('USD', 'KRW', NOW() - INTERVAL 9 DAY, 1000.00),
    ('USD', 'KRW', NOW() - INTERVAL 10 DAY, 1000.00);

INSERT INTO ItemPriceInfo (item_id, closing_price, opening_price, daily_high, daily_low, week_52_high, week_52_low, historical_high, historical_low, updated_at)
VALUES
    (1, 150.00, 145.00, 152.00, 144.00, 200.00, 100.00, 250.00, 90.00, NOW()),
    (2, 100.00, 95.00, 105.00, 94.00, 150.00, 80.00, 200.00, 70.00, NOW()),
    (3, 2000.00, 1900.00, 2100.00, 1800.00, 3000.00, 1500.00, 3500.00, 1000.00, NOW()),
    (4, 150.00, 145.00, 152.00, 144.00, 200.00, 100.00, 250.00, 90.00, NOW()),
    (5, 100.00, 95.00, 105.00, 94.00, 150.00, 80.00, 200.00, 70.00, NOW()),
    (6, 2000.00, 1900.00, 2100.00, 1800.00, 3000.00, 1500.00, 3500.00, 1000.00, NOW()),
    (7, 150.00, 145.00, 152.00, 144.00, 200.00, 100.00, 250.00, 90.00, NOW()),
    (8, 100.00, 95.00, 105.00, 94.00, 150.00, 80.00, 200.00, 70.00, NOW()),
    (9, 2000.00, 1900.00, 2100.00, 1800.00, 3000.00, 1500.00, 3500.00, 1000.00, NOW()),
    (10, 150.00, 145.00, 152.00, 144.00, 200.00, 100.00, 250.00, 90.00, NOW()),
    (11, 100.00, 95.00, 105.00, 94.00, 150.00, 80.00, 200.00, 70.00, NOW()),
    (12, 2000.00, 1900.00, 2100.00, 1800.00, 3000.00, 1500.00, 3500.00, 1000.00, NOW()),
    (13, 150.00, 145.00, 152.00, 144.00, 200.00, 100.00, 250.00, 90.00, NOW()),
    (14, 100.00, 95.00, 105.00, 94.00, 150.00, 80.00, 200.00, 70.00, NOW()),
    (15, 2000.00, 1900.00, 2100.00, 1800.00, 3000.00, 1500.00, 3500.00, 1000.00, NOW()),
    (16, 150.00, 145.00, 152.00, 144.00, 200.00, 100.00, 250.00, 90.00, NOW()),
    (17, 100.00, 95.00, 105.00, 94.00, 150.00, 80.00, 200.00, 70.00, NOW()),
    (18, 2000.00, 1900.00, 2100.00, 1800.00, 3000.00, 1500.00, 3500.00, 1000.00, NOW()),
    (19, 150.00, 145.00, 152.00, 144.00, 200.00, 100.00, 250.00, 90.00, NOW()),
    (20, 100.00, 95.00, 105.00, 94.00, 150.00, 80.00, 200.00, 70.00, NOW()),
    (21, 2000.00, 1900.00, 2100.00, 1800.00, 3000.00, 1500.00, 3500.00, 1000.00, NOW()),
    (22, 150.00, 145.00, 152.00, 144.00, 200.00, 100.00, 250.00, 90.00, NOW()),
    (23, 100.00, 95.00, 105.00, 94.00, 150.00, 80.00, 200.00, 70.00, NOW()),
    (24, 2000.00, 1900.00, 2100.00, 1800.00, 3000.00, 1500.00, 3500.00, 1000.00, NOW()),
    (25, 150.00, 145.00, 152.00, 144.00, 200.00, 100.00, 250.00, 90.00, NOW()),
    (26, 100.00, 95.00, 105.00, 94.00, 150.00, 80.00, 200.00, 70.00, NOW()),
    (27, 2000.00, 1900.00, 2100.00, 1800.00, 3000.00, 1500.00, 3500.00, 1000.00, NOW()),
    (28, 150.00, 145.00, 152.00, 144.00, 200.00, 100.00, 250.00, 90.00, NOW()),
    (29, 100.00, 95.00, 105.00, 94.00, 150.00, 80.00, 200.00, 70.00, NOW()),
    (30, 2000.00, 1900.00, 2100.00, 1800.00, 3000.00, 1500.00, 3500.00, 1000.00, NOW());


INSERT INTO CurrentPrice (item_id, updated_at, current_price)
VALUES
    (1, NOW(), 149.00),
    (2, NOW(), 99.00),
    (3, NOW(), 1999.00),
    (4, NOW(), 149.00),
    (5, NOW(), 99.00),
    (6, NOW(), 1999.00),
    (7, NOW(), 149.00),
    (8, NOW(), 99.00),
    (9, NOW(), 1999.00),
    (10, NOW(), 149.00),
    (11, NOW(), 99.00),
    (12, NOW(), 1999.00),
    (13, NOW(), 149.00),
    (14, NOW(), 99.00),
    (15, NOW(), 1999.00),
    (16, NOW(), 149.00),
    (17, NOW(), 99.00),
    (18, NOW(), 1999.00),
    (19, NOW(), 149.00),
    (20, NOW(), 99.00),
    (21, NOW(), 1999.00),
    (22, NOW(), 149.00),
    (23, NOW(), 99.00),
    (24, NOW(), 1999.00),
    (25, NOW(), 149.00),
    (26, NOW(), 99.00),
    (27, NOW(), 1999.00),
    (28, NOW(), 149.00),
    (29, NOW(), 99.00),
    (30, NOW(), 1999.00);

-- Stock, StockData 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO Stock (item_id, CEO, sector, industry, dividend_yield, dividend_payment_month)
VALUES
    (1, 'Tim Cook', 'Technology', 'Consumer Electronics', 0.005, 3),
    (2, 'CEO 1', 'Sector 1', 'Industry 1', 0.005, 3),
    (3, 'CEO 2', 'Sector 2', 'Industry 2', 0.005, 3),
    (4, 'CEO 3', 'Sector 3', 'Industry 3', 0.005, 3),
    (5, 'CEO 4', 'Sector 4', 'Industry 4', 0.005, 3),
    (6, 'CEO 5', 'Sector 5', 'Industry 5', 0.005, 3),
    (7, 'CEO 6', 'Sector 6', 'Industry 6', 0.005, 3),
    (8, 'CEO 7', 'Sector 7', 'Industry 7', 0.005, 3),
    (9, 'CEO 8', 'Sector 8', 'Industry 8', 0.005, 3),
    (10, 'CEO 9', 'Sector 9', 'Industry 9', 0.005, 3);

INSERT INTO StockData (item_id, volume, market_cap, PER, EPS, updated_at)
VALUES
    (1, 5000000, 2000000000, 30.00, 5.00, NOW()),
    (2, 5000000, 2000000000, 30.00, 5.00, NOW()),
    (3, 5000000, 2000000000, 30.00, 5.00, NOW()),
    (4, 5000000, 2000000000, 30.00, 5.00, NOW()),
    (5, 5000000, 2000000000, 30.00, 5.00, NOW()),
    (6, 5000000, 2000000000, 30.00, 5.00, NOW()),
    (7, 5000000, 2000000000, 30.00, 5.00, NOW()),
    (8, 5000000, 2000000000, 30.00, 5.00, NOW()),
    (9, 5000000, 2000000000, 30.00, 5.00, NOW()),
    (10, 5000000, 2000000000, 30.00, 5.00, NOW());

-- FinancialStatements 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO FinancialStatements (item_id, report_period, revenue, net_income, total_assets, total_liabilities, equity, report_date)
VALUES
    (1, '2022-Q4', 100000000, 20000000, 500000000, 300000000, 200000000, NOW()),
    (1, '2022-Q3', 100000000, 20000000, 500000000, 300000000, 200000000, NOW()),
    (1, '2022-Q2', 100000000, 20000000, 500000000, 300000000, 200000000, NOW()),
    (1, '2022-Q1', 100000000, 20000000, 500000000, 300000000, 200000000, NOW()),
    (1, '2021-Q4', 100000000, 20000000, 500000000, 300000000, 200000000, NOW()),
    (1, '2021-Q3', 100000000, 20000000, 500000000, 300000000, 200000000, NOW()),
    (1, '2021-Q2', 100000000, 20000000, 500000000, 300000000, 200000000, NOW()),
    (1, '2021-Q1', 100000000, 20000000, 500000000, 300000000, 200000000, NOW()),
    (1, '2020-Q4', 100000000, 20000000, 500000000, 300000000, 200000000, NOW()),
    (1, '2020-Q3', 100000000, 20000000, 500000000, 300000000, 200000000, NOW());


-- StockRate, AnalystInfo 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO AnalystInfo (name, firm, expertise_area, contact_info)
VALUES
    ('Analyst 1', 'Firm 1', 'Technology', 'analyst1@firm.com'),
    ('Analyst 2', 'Firm 2', 'Finance', 'analyst2@firm.com'),
    ('Analyst 3', 'Firm 3', 'Economics', 'analyst3@firm.com'),
    ('Analyst 4', 'Firm 4', 'Banking', 'analyst4@firm.com'),
    ('Analyst 5', 'Firm 5', 'Technology', 'analyst5@firm.com'),
    ('Analyst 6', 'Firm 6', 'Healthcare', 'analyst6@firm.com'),
    ('Analyst 7', 'Firm 7', 'Energy', 'analyst7@firm.com'),
    ('Analyst 8', 'Firm 8', 'Utilities', 'analyst8@firm.com'),
    ('Analyst 9', 'Firm 9', 'Consumer Goods', 'analyst9@firm.com'),
    ('Analyst 10', 'Firm 10', 'Real Estate', 'analyst10@firm.com');

INSERT INTO StockRate (item_id, analyst_id, analyst_rating, target_price, updated_at)
VALUES
    (1, 1, 'A', 160.00, NOW()),
    (2, 2, 'A', 120.00, NOW()),
    (3, 3, 'C', 90.00, NOW()),
    (4, 4, 'B', 180.00, NOW()),
    (5, 5, 'A', 150.00, NOW()),
    (6, 6, 'B', 200.00, NOW()),
    (7, 7, 'C', 80.00, NOW()),
    (8, 8, 'A', 110.00, NOW()),
    (9, 9, 'A', 170.00, NOW()),
    (10, 10, 'C', 60.00, NOW());

-- Bond, Gold 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO Bond (item_id, bond_type, coupon_rate, coupon_payment_date, maturity_date, credit_rating)
VALUES
    (11, 'Corporate', 4, NOW(), NOW() + INTERVAL 5 YEAR, 'A'),
    (12, 'Corporate', 4, NOW(), NOW() + INTERVAL 5 YEAR, 'A'),
    (13, 'Corporate', 4, NOW(), NOW() + INTERVAL 5 YEAR, 'A'),
    (14, 'Corporate', 4, NOW(), NOW() + INTERVAL 5 YEAR, 'A'),
    (15, 'Corporate', 4, NOW(), NOW() + INTERVAL 5 YEAR, 'A'),
    (16, 'Corporate', 4, NOW(), NOW() + INTERVAL 5 YEAR, 'A'),
    (17, 'Corporate', 4, NOW(), NOW() + INTERVAL 5 YEAR, 'A'),
    (18, 'Corporate', 4, NOW(), NOW() + INTERVAL 5 YEAR, 'A'),
    (19, 'Corporate', 4, NOW(), NOW() + INTERVAL 5 YEAR, 'A'),
    (20, 'Corporate', 4, NOW(), NOW() + INTERVAL 5 YEAR, 'A');


INSERT INTO Gold (item_id, weight)
VALUES
    (21, 1),
    (22, 1),
    (23, 1),
    (24, 1),
    (25, 1),
    (26, 1),
    (27, 1),
    (28, 1),
    (29, 1),
    (30, 1);