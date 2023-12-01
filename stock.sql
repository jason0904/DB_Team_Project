use caudbdesign;

#일단 db가 있으면 db테이블 다 날리고 다시 만듬
DROP DATABASE IF EXISTS caudbdesign;

CREATE DATABASE caudbdesign;

use caudbdesign;

-- User-related tables
CREATE TABLE user
(
    Uid            INTEGER PRIMARY KEY AUTO_INCREMENT,
    username       VARCHAR(255),
    usertype       VARCHAR(255),
    account_status VARCHAR(255),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP
);

CREATE TABLE personal
(
    Uid            INTEGER PRIMARY KEY,
    social_id_hash VARCHAR(255),
    FOREIGN KEY (Uid) REFERENCES user (Uid)
);

CREATE TABLE organization
(
    Uid             INTEGER PRIMARY KEY,
    businessid_hash VARCHAR(255),
    FOREIGN KEY (Uid) REFERENCES user (Uid)
);

CREATE TABLE login
(
    Uid                  INTEGER PRIMARY KEY,
    ID                   VARCHAR(255),
    password_hash        VARCHAR(255),
    password_attempts    INTEGER,
    security_question    VARCHAR(255),
    security_answer_hash VARCHAR(255),
    FOREIGN KEY (Uid) REFERENCES user (Uid)
);

CREATE TABLE login_log
(
    log_id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    Uid            INTEGER,
    attempt_time   TIMESTAMP,
    attempt_status VARCHAR(255),
    ip_address     VARCHAR(255),
    FOREIGN KEY (Uid) REFERENCES user (Uid)
);

CREATE TABLE sensitive_info
(
    Uid   INTEGER PRIMARY KEY,
    email VARCHAR(255),
    phone VARCHAR(255),
    age   VARCHAR(255),
    sex   VARCHAR(255),
    FOREIGN KEY (Uid) REFERENCES user (Uid)
);

CREATE TABLE AdditionalContactInfo
(
    ContactInfoID INTEGER PRIMARY KEY AUTO_INCREMENT,
    Uid           INTEGER,
    ContactType   VARCHAR(255),
    ContactValue  VARCHAR(255),
    FOREIGN KEY (Uid) REFERENCES sensitive_info (Uid)
);

CREATE TABLE address
(
    address_id     INTEGER PRIMARY KEY AUTO_INCREMENT,
    Uid            INTEGER,
    street_address VARCHAR(255),
    city           VARCHAR(255),
    state          VARCHAR(255),
    postal_code    VARCHAR(255),
    country        VARCHAR(255),
    FOREIGN KEY (Uid) REFERENCES sensitive_info (Uid)
);

-- Account-related tables
CREATE TABLE Account
(
    account_id     INTEGER PRIMARY KEY AUTO_INCREMENT,
    Uid            INTEGER,
    account_number VARCHAR(255),
    account_type   VARCHAR(255),
    stock_value    DECIMAL(10, 2),
    status         VARCHAR(255),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,
    FOREIGN KEY (Uid) REFERENCES user (Uid)
);

CREATE TABLE AccountPassword
(
    account_id        INTEGER PRIMARY KEY,
    password_attempts INTEGER,
    password_hash     VARCHAR(255),
    updated_at        TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Account (account_id)
);

CREATE TABLE AccountLoginLog
(
    log_id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    account_id   INTEGER,
    login_time   TIMESTAMP,
    login_status VARCHAR(255),
    ip_address   VARCHAR(255),
    FOREIGN KEY (account_id) REFERENCES Account (account_id)
);

CREATE TABLE Balance
(
    account_id    INTEGER PRIMARY KEY,
    Total_balance DECIMAL(10, 2),
    KRW_balance   DECIMAL(10, 2),
    USD_balance   DECIMAL(10, 2),
    FOREIGN KEY (account_id) REFERENCES Account (account_id)
);

-- Offer Type-related tables
CREATE TABLE GeneralAccount
(
    account_id           INTEGER PRIMARY KEY,
    Available_offer_Type VARCHAR(255),
    FOREIGN KEY (account_id) REFERENCES Account (account_id)
);

CREATE TABLE GoldAccount
(
    account_id           INTEGER PRIMARY KEY,
    Available_offer_Type VARCHAR(255),
    FOREIGN KEY (account_id) REFERENCES Account (account_id)
);

-- Stock-related tables
CREATE TABLE StockPortfolio
(
    portfolio_id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    account_id             INTEGER,
    stock_symbol           VARCHAR(255),
    quantity               INTEGER,
    average_purchase_price DECIMAL(10, 2),
    current_price          DECIMAL(10, 2),
    created_at             TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Account (account_id)
);

CREATE TABLE Item
(
    item_id    INTEGER PRIMARY KEY AUTO_INCREMENT,
    symbol     VARCHAR(255),
    name       VARCHAR(255),
    item_type  VARCHAR(255),
    market     VARCHAR(255),
    created_at TIMESTAMP
);

CREATE TABLE _Order
(
    order_id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    account_id    INTEGER,
    item_id       INTEGER,
    purchase_type VARCHAR(255),
    order_status  VARCHAR(255),
    created_at    TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Account (account_id),
    FOREIGN KEY (item_id) REFERENCES Item (item_id)
);

CREATE TABLE BuyOrder
(
    order_id      INTEGER PRIMARY KEY,
    quantity      INTEGER,
    left_quantity INTEGER,
    order_type    VARCHAR(255),
    limit_price   DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES _Order (order_id)
);

CREATE TABLE SellOrder
(
    order_id      INTEGER PRIMARY KEY,
    quantity      INTEGER,
    left_quantity INTEGER,
    order_type    VARCHAR(255),
    limit_price   DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES _Order (order_id)
);

CREATE TABLE StockTradeLog
(
    trade_id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    order_id          INTEGER,
    purchase_type     VARCHAR(255),
    executed_quantity INTEGER,
    executed_price    DECIMAL(10, 2),
    trade_time        TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES _Order (order_id)
);

-- Currency Exchange-related tables
CREATE TABLE CurrencyExchange
(
    exchange_id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    account_id       INTEGER,
    base_currency    VARCHAR(255),
    foreign_currency VARCHAR(255),
    base_amount      DECIMAL(10, 2),
    foreign_amount   DECIMAL(10, 2),
    exchange_time    TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Account (account_id)
);

CREATE TABLE CurrentExchangeRate
(
    base_currency         VARCHAR(255),
    foreign_currency      VARCHAR(255),
    current_exchange_rate DECIMAL(10, 2),
    updated_at            TIMESTAMP,
    PRIMARY KEY (base_currency, foreign_currency)
);

CREATE TABLE ExchangeRateHistory
(
    base_currency    VARCHAR(255),
    foreign_currency VARCHAR(255),
    updated_at       TIMESTAMP,
    exchange_rate    DECIMAL(10, 2),
    PRIMARY KEY (base_currency, foreign_currency, updated_at)
);

CREATE TABLE ItemPrice_Info
(
    item_id         INTEGER PRIMARY KEY,
    closing_price   DECIMAL(10, 2),
    opening_price   DECIMAL(10, 2),
    daily_high      DECIMAL(10, 2),
    daily_low       DECIMAL(10, 2),
    week_52_high    DECIMAL(10, 2),
    week_52_low     DECIMAL(10, 2),
    historical_high DECIMAL(10, 2),
    historical_low  DECIMAL(10, 2),
    updated_at      TIMESTAMP,
    FOREIGN KEY (item_id) REFERENCES Item (item_id)
);

CREATE TABLE CurrentPrice
(
    item_id       INTEGER,
    timestamp     TIMESTAMP,
    current_price DECIMAL(10, 2),
    PRIMARY KEY (item_id, timestamp),
    FOREIGN KEY (item_id) REFERENCES Item (item_id)
);

CREATE TABLE Stock
(
    item_id               INTEGER PRIMARY KEY,
    CEO                   VARCHAR(255),
    sector                VARCHAR(255),
    industry              VARCHAR(255),
    dividend_yield        DECIMAL(5, 2),
    dividend_payment_date TIMESTAMP,
    FOREIGN KEY (item_id) REFERENCES Item (item_id)
);

CREATE TABLE StockData
(
    item_id    INTEGER,
    volume     BIGINT,
    market_cap BIGINT,
    PER        DECIMAL(5, 2),
    EPS        DECIMAL(5, 2),
    updated_at TIMESTAMP,
    FOREIGN KEY (item_id) REFERENCES Stock (item_id)
);

-- Financial Statements-related table
CREATE TABLE FinancialStatements
(
    statement_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    item_id      INTEGER
);
