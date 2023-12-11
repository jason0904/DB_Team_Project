INSERT INTO User (username, usertype, account_status, created_at, updated_at)
VALUES 
    ('hong_gildong', 'personal', 'active', NOW(), NOW()),
    ('kim_minsu', 'business', 'active', NOW(), NOW()),
    ('lee_jieun', 'personal', 'inactive', NOW(), NOW()),
    ('park_jisung', 'business', 'active', NOW(), NOW()),
    ('choi_yeojin', 'personal', 'active', NOW(), NOW());

INSERT INTO Personal (uid, social_id_hash)
VALUES 
    (1, 'hash_value_1'),
    (3, 'hash_value_3'),
    (5, 'hash_value_5');

INSERT INTO Organization (uid, business_id_hash)
VALUES 
    (2, 'business_hash_value_2'),
    (4, 'business_hash_value_4');

-- UserLoginMeta 테이블에 레코드 삽입
INSERT INTO UserLoginMeta (uid, login_id, login_attempt)
VALUES 
    (1, 'login_id_1', 0),
    (2, 'login_id_2', 0),
    (3, 'login_id_3', 0),
    (4, 'login_id_4', 0),
    (5, 'login_id_5', 0);

-- UserLoginPasswWord 테이블에 레코드 삽입
INSERT INTO UserLoginPasswWord (uid, password_hash, updated_at)
VALUES 
    (1, 'password_hash_1', NOW()),
    (2, 'password_hash_2', NOW()),
    (3, 'password_hash_3', NOW()),
    (4, 'password_hash_4', NOW()),
    (5, 'password_hash_5', NOW());

-- UserLoginQuestion 테이블에 레코드 삽입
INSERT INTO UserLoginQuestion (uid, security_question, security_answer_hash)
VALUES 
    (1, 'Your first pet’s name?', 'answer_hash_1'),
    (2, 'Your favorite movie?', 'answer_hash_2'),
    (3, 'Your best friend’s name?', 'answer_hash_3'),
    (4, 'Your childhood nickname?', 'answer_hash_4'),
    (5, 'The name of your first school?', 'answer_hash_5');

-- UserLoginLog 테이블에 레코드 삽입
INSERT INTO UserLoginLog (uid, login_time, login_status)
VALUES 
    (1, NOW(), 'success'),
    (2, NOW(), 'failed'),
    (3, NOW(), 'success'),
    (4, NOW(), 'success'),
    (5, NOW(), 'failed');

-- SensitiveInfo 테이블에 레코드 삽입
INSERT INTO SensitiveInfo (uid, email, phone, age, sex)
VALUES 
    (1, 'john@example.com', '1234567890', 30, 'M'),
    (2, 'kim@example.com', '2345678901', 40, 'M'),
    (3, 'lee@example.com', '3456789012', 25, 'F'),
    (4, 'park@example.com', '4567890123', 35, 'F'),
    (5, 'choi@example.com', '5678901234', 28, 'M');

-- AdditionalContactInfo 테이블에 레코드 삽입
INSERT INTO AdditionalContactInfo (uid, ContactType, ContactValue)
VALUES 
    (1, 'secondary_email', 'john.secondary@example.com'),
    (2, 'secondary_email', 'kim.secondary@example.com'),
    (3, 'secondary_email', 'lee.secondary@example.com'),
    (4, 'secondary_email', 'park.secondary@example.com'),
    (5, 'secondary_email', 'choi.secondary@example.com');

-- Address 테이블에 레코드 삽입
INSERT INTO Address (uid, address, postal_code, country)
VALUES 
    (1, '123 Main St', 12345, 'CountryName'),
    (2, '456 Side St', 23456, 'CountryName'),
    (3, '789 Cross Ave', 34567, 'CountryName'),
    (4, '12 River Rd', 45678, 'CountryName'),
    (5, '1314 Ocean Blvd', 56789, 'CountryName');

INSERT INTO Account (uid, account_number, account_type, stock_value, status, created_at, updated_at)
VALUES 
    (1, '00000000123', 'general', 10000.00, 'active', NOW(), NOW()),
    (2, '00000000234', 'gold', 20000.00, 'active', NOW(), NOW()),
    (3, '00000000345', 'general', 15000.00, 'active', NOW(), NOW()),
    (4, '00000000456', 'gold', 30000.00, 'active', NOW(), NOW()),
    (5, '00000000567', 'general', 5000.00, 'active', NOW(), NOW());

INSERT INTO AccountLoginPasswWord (account_id, password_hash, updated_at)
VALUES 
    (1, 'account_password_hash_1', NOW()),
    (2, 'account_password_hash_2', NOW()),
    (3, 'account_password_hash_3', NOW()),
    (4, 'account_password_hash_4', NOW()),
    (5, 'account_password_hash_5', NOW());

INSERT INTO AccountLoginMeta (account_id, login_attempt)
VALUES 
    (1, 0),
    (2, 1),
    (3, 0),
    (4, 2),
    (5, 0);

INSERT INTO AccountLoginLog (account_id, login_time, login_status)
VALUES 
    (1, NOW(), 'success'),
    (2, NOW(), 'failure'),
    (3, NOW(), 'success'),
    (4, NOW(), 'success'),
    (5, NOW(), 'failure');

-- Balance 테이블에 레코드 삽입
INSERT INTO Balance (account_id, Total_balance, KRW_balance, USD_balance)
VALUES 
    (1, 10000.00, 8000.00, 2.00),
    (2, 20000.00, 15000.00, 5.00),
    (3, 15000.00, 10000.00, 5.00),
    (4, 30000.00, 25000.00, 5.00),
    (5, 5000.00, 4000.00, 1.00);

-- GeneralAccount 및 GoldAccount 테이블에 레코드 삽입
INSERT INTO GeneralAccount (account_id, Available_offer_Type)
VALUES 
    (1, 'Offer_Type_1'),
    (3, 'Offer_Type_2'),
    (5, 'Offer_Type_3');

INSERT INTO GoldAccount (account_id, Available_offer_Type)
VALUES 
    (2, 'Gold_Offer_Type_1'),
    (4, 'Gold_Offer_Type_2');

-- Item, ItemPriceInfo, CurrentPrice 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO Item (symbol, name, item_type, market, created_at)
VALUES 
    ('AAPL', 'Apple Inc.', 'Stock', 'NYSE', NOW()),
    ('Bond1', 'Bond_samsung', 'Bond', 'Bond', NOW()),
    ('Gold1', 'Gold', 'Gold', 'Gold', NOW());

-- ItemPortfolio 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO ItemPortfolio (account_id, item_id, quantity, total_purchase_price, current_price, created_at)
VALUES 
    (1, 1, 10, 1000.00, 1100.00, NOW()),
    (2, 2, 20, 2000.00, 2100.00, NOW());

-- Order, BuyOrder, SellOrder 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO `Order` (account_id, item_id, purchase_type, order_status, created_at, success_at)
VALUES 
    (1, 1, 'buy', 'pending', NOW(), NULL),
    (2, 2, 'sell', 'complete', NOW(), NOW());

INSERT INTO BuyOrder (order_id, quantity, order_type, limit_price)
VALUES 
    (1, 10, 'market', NULL);

INSERT INTO SellOrder (order_id, quantity, order_type, limit_price)
VALUES 
    (2, 20, 'limit', 105.00);

-- CurrentExchangeRate 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO CurrentExchangeRate (base_currency, foreign_currency, current_exchange_rate, updated_at)
VALUES 
    ('USD', 'KRW', 1000.00, NOW());
    
-- CurrencyExchange 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO CurrencyExchange (account_id, base_currency, foreign_currency, base_amount, foreign_amount, exchange_time)
VALUES 
    (1, 'USD', 'KRW', 1.00, 1000.00, NOW());


-- ExchangeRateHistory 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO ExchangeRateHistory (base_currency, foreign_currency, created_at, exchange_rate)
VALUES 
    ('USD', 'KRW', NOW() - INTERVAL 1 DAY, 1150.00);

INSERT INTO ItemPriceInfo (item_id, closing_price, opening_price, daily_high, daily_low, week_52_high, week_52_low, historical_high, historical_low, updated_at)
VALUES 
    (1, 150.00, 145.00, 152.00, 144.00, 200.00, 100.00, 250.00, 90.00, NOW()),
    (2, 100.00, 95.00, 105.00, 94.00, 150.00, 80.00, 200.00, 70.00, NOW()),
    (3, 2000.00, 1900.00, 2100.00, 1800.00, 3000.00, 1500.00, 3500.00, 1000.00, NOW());

INSERT INTO CurrentPrice (item_id, updated_at, current_price)
VALUES 
    (1, NOW(), 149.00);

-- Stock, StockData 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO Stock (item_id, CEO, sector, industry, dividend_yield, dividend_payment_month)
VALUES 
    (1, 'Tim Cook', 'Technology', 'Consumer Electronics', 0.005, 3);

INSERT INTO StockData (item_id, volume, market_cap, PER, EPS, updated_at)
VALUES 
    (1, 5000000, 2000000000, 30.00, 5.00, NOW());

-- FinancialStatements 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO FinancialStatements (item_id, report_period, revenue, net_income, total_assets, total_liabilities, equity, report_date)
VALUES 
    (1, '2022-Q4', 100000000, 20000000, 500000000, 300000000, 200000000, NOW());

-- StockRate, AnalystInfo 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO AnalystInfo (name, firm, expertise_area, contact_info)
VALUES 
    ('Analyst 1', 'Firm 1', 'Technology', 'analyst1@firm.com');

INSERT INTO StockRate (item_id, analyst_id, analyst_rating, target_price, updated_at)
VALUES 
    (1, 1, 'Buy', 160.00, NOW());

-- Bond, Gold 테이블에 레코드 삽입 (가상의 데이터 사용)
INSERT INTO Bond (item_id, bond_type, coupon_rate, coupon_payment_date, maturity_date, credit_rating)
VALUES 
    (2, 'Corporate', 4, NOW(), NOW() + INTERVAL 5 YEAR, 'AA');

INSERT INTO Gold (item_id, weight)
VALUES 
    (3, '1');
