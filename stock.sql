CREATE TABLE `User` (
  `uid` integer PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(255),
  `usertype` varchar(255),
  `account_status` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `Personal` (
  `uid` integer PRIMARY KEY,
  `social_id_hash` varchar(255)
);

CREATE TABLE `Organization` (
  `uid` integer PRIMARY KEY,
  `business_id_hash` varchar(255)
);

CREATE TABLE `UserLoginMeta` (
  `uid` integer PRIMARY KEY,
  `login_id` varchar(255),
  `login_attempt` integer
);

CREATE TABLE `UserLoginPasswWord` (
  `uid` integer PRIMARY KEY,
  `password_hash` varchar(255),
  `updated_at` timestamp
);

CREATE TABLE `UserLoginQuestion` (
  `uid` integer PRIMARY KEY,
  `security_question` varchar(255),
  `security_answer_hash` varchar(255)
);

CREATE TABLE `UserLoginLog` (
  `log_id` integer PRIMARY KEY AUTO_INCREMENT,
  `uid` integer,
  `login_time` timestamp,
  `login_status` varchar(255)
);

CREATE TABLE `SensitiveInfo` (
  `uid` integer PRIMARY KEY,
  `email` varchar(255),
  `phone` varchar(255),
  `age` integer,
  `sex` varchar(255)
);

CREATE TABLE `AdditionalContactInfo` (
  `contact_info_id` integer PRIMARY KEY AUTO_INCREMENT,
  `uid` integer,
  `ContactType` varchar(255),
  `ContactValue` varchar(255)
);

CREATE TABLE `Address` (
  `address_id` integer PRIMARY KEY AUTO_INCREMENT,
  `uid` integer,
  `address` varchar(255),
  `postal_code` int,
  `country` varchar(255)
);

CREATE TABLE `Account` (
  `account_id` integer PRIMARY KEY AUTO_INCREMENT,
  `uid` integer,
  `account_number` varchar(255),
  `account_type` varchar(255),
  `stock_value` decimal,
  `status` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `AccountLoginPasswWord` (
  `account_id` integer PRIMARY KEY,
  `password_hash` varchar(255),
  `updated_at` timestamp
);

CREATE TABLE `AccountLoginMeta` (
  `account_id` integer PRIMARY KEY,
  `login_attempt` integer
);

CREATE TABLE `AccountLoginLog` (
  `log_id` integer PRIMARY KEY AUTO_INCREMENT,
  `account_id` integer,
  `login_time` timestamp,
  `login_status` varchar(255)
);

CREATE TABLE `Balance` (
  `account_id` integer PRIMARY KEY,
  `Total_balance` decimal,
  `KRW_balance` decimal,
  `USD_balance` decimal
);

CREATE TABLE `GeneralAccount` (
  `account_id` integer PRIMARY KEY,
  `Available_offer_Type` varchar(255)
);

CREATE TABLE `GoldAccount` (
  `account_id` integer PRIMARY KEY,
  `Available_offer_Type` varchar(255)
);

CREATE TABLE `ItemPortfolio` (
  `account_id` integer,
  `item_id` integer,
  `quantity` integer,
  `total_purchase_price` decimal,
  `current_price` decimal,
  `created_at` timestamp,
  PRIMARY KEY (`account_id`, `item_id`)
);

CREATE TABLE `Order` (
  `order_id` integer PRIMARY KEY AUTO_INCREMENT,
  `account_id` integer,
  `item_id` integer,
  `purchase_type` varchar(255),
  `order_status` varchar(255),
  `created_at` timestamp,
  `success_at` timestamp
);

CREATE TABLE `BuyOrder` (
  `order_id` integer PRIMARY KEY,
  `quantity` integer,
  `order_type` varchar(255),
  `limit_price` decimal
);

CREATE TABLE `SellOrder` (
  `order_id` integer PRIMARY KEY,
  `quantity` integer,
  `order_type` varchar(255),
  `limit_price` decimal
);

CREATE TABLE `CurrencyExchange` (
  `exchange_id` integer PRIMARY KEY AUTO_INCREMENT,
  `account_id` integer,
  `base_currency` varchar(255),
  `foreign_currency` varchar(255),
  `base_amount` decimal,
  `foreign_amount` decimal,
  `exchange_time` timestamp
);

CREATE TABLE `CurrentExchangeRate` (
  `base_currency` varchar(255),
  `foreign_currency` varchar(255),
  `current_exchange_rate` decimal,
  `updated_at` timestamp,
  PRIMARY KEY (`base_currency`, `foreign_currency`)
);

CREATE TABLE `ExchangeRateHistory` (
  `base_currency` varchar(255),
  `foreign_currency` varchar(255),
  `created_at` timestamp,
  `exchange_rate` decimal,
  PRIMARY KEY (`base_currency`, `foreign_currency`, `created_at`)
);

CREATE TABLE `Item` (
  `item_id` integer PRIMARY KEY AUTO_INCREMENT,
  `symbol` varchar(255),
  `name` varchar(255),
  `item_type` varchar(255),
  `market` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `ItemPriceInfo` (
  `item_id` integer PRIMARY KEY,
  `closing_price` decimal,
  `opening_price` decimal,
  `daily_high` decimal,
  `daily_low` decimal,
  `week_52_high` decimal,
  `week_52_low` decimal,
  `historical_high` decimal,
  `historical_low` decimal,
  `updated_at` timestamp
);

CREATE TABLE `CurrentPrice` (
  `item_id` integer,
  `updated_at` timestamp,
  `current_price` decimal,
  PRIMARY KEY (`item_id`, `updated_at`)
);

CREATE TABLE `Stock` (
  `item_id` integer PRIMARY KEY,
  `CEO` varchar(255),
  `sector` varchar(255),
  `industry` varchar(255),
  `dividend_yield` decimal,
  `dividend_payment_month` integer
);

CREATE TABLE `StockData` (
  `item_id` integer,
  `volume` bigint,
  `market_cap` bigint,
  `PER` decimal,
  `EPS` decimal,
  `updated_at` timestamp
);

CREATE TABLE `FinancialStatements` (
  `statement_id` integer PRIMARY KEY AUTO_INCREMENT,
  `item_id` integer,
  `report_period` varchar(255),
  `revenue` bigint,
  `net_income` bigint,
  `total_assets` bigint,
  `total_liabilities` bigint,
  `equity` bigint,
  `report_date` timestamp
);

CREATE TABLE `StockRate` (
  `stock_info_id` integer PRIMARY KEY AUTO_INCREMENT,
  `item_id` integer,
  `analyst_id` integer,
  `analyst_rating` varchar(255),
  `target_price` decimal,
  `updated_at` timestamp
);

CREATE TABLE `AnalystInfo` (
  `analyst_id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `firm` varchar(255),
  `expertise_area` varchar(255),
  `contact_info` varchar(255)
);

CREATE TABLE `Bond` (
  `item_id` integer PRIMARY KEY,
  `bond_type` varchar(255),
  `coupon_rate` decimal,
  `coupon_payment_date` timestamp,
  `maturity_date` timestamp,
  `credit_rating` varchar(255)
);

CREATE TABLE `Gold` (
  `item_id` integer PRIMARY KEY,
  `weight` varchar(255)
);

ALTER TABLE `Personal` ADD FOREIGN KEY (`uid`) REFERENCES `User` (`uid`);

ALTER TABLE `Organization` ADD FOREIGN KEY (`uid`) REFERENCES `User` (`uid`);

ALTER TABLE `UserLoginMeta` ADD FOREIGN KEY (`uid`) REFERENCES `User` (`uid`);

ALTER TABLE `UserLoginPasswWord` ADD FOREIGN KEY (`uid`) REFERENCES `User` (`uid`);

ALTER TABLE `UserLoginQuestion` ADD FOREIGN KEY (`uid`) REFERENCES `User` (`uid`);

ALTER TABLE `UserLoginLog` ADD FOREIGN KEY (`uid`) REFERENCES `User` (`uid`);

ALTER TABLE `SensitiveInfo` ADD FOREIGN KEY (`uid`) REFERENCES `User` (`uid`);

ALTER TABLE `AdditionalContactInfo` ADD FOREIGN KEY (`uid`) REFERENCES `SensitiveInfo` (`uid`);

ALTER TABLE `Address` ADD FOREIGN KEY (`uid`) REFERENCES `SensitiveInfo` (`uid`);

ALTER TABLE `Account` ADD FOREIGN KEY (`uid`) REFERENCES `User` (`uid`);

ALTER TABLE `AccountLoginPasswWord` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `AccountLoginMeta` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `AccountLoginLog` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `Balance` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `GeneralAccount` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `GoldAccount` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `ItemPortfolio` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `ItemPortfolio` ADD FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

ALTER TABLE `Order` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `Order` ADD FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

ALTER TABLE `BuyOrder` ADD FOREIGN KEY (`order_id`) REFERENCES `Order` (`order_id`);

ALTER TABLE `SellOrder` ADD FOREIGN KEY (`order_id`) REFERENCES `Order` (`order_id`);

ALTER TABLE `CurrencyExchange` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `ItemPriceInfo` ADD FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

ALTER TABLE `CurrentPrice` ADD FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

ALTER TABLE `Stock` ADD FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

ALTER TABLE `StockData` ADD FOREIGN KEY (`item_id`) REFERENCES `Stock` (`item_id`);

ALTER TABLE `FinancialStatements` ADD FOREIGN KEY (`item_id`) REFERENCES `Stock` (`item_id`);

ALTER TABLE `StockRate` ADD FOREIGN KEY (`item_id`) REFERENCES `Stock` (`item_id`);

ALTER TABLE `StockRate` ADD FOREIGN KEY (`analyst_id`) REFERENCES `AnalystInfo` (`analyst_id`);

ALTER TABLE `Bond` ADD FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

ALTER TABLE `Gold` ADD FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

ALTER TABLE `ExchangeRateHistory` ADD FOREIGN KEY (`base_currency`, `foreign_currency`) REFERENCES `CurrentExchangeRate` (`base_currency`, `foreign_currency`);

ALTER TABLE `CurrencyExchange` ADD FOREIGN KEY (`base_currency`, `foreign_currency`) REFERENCES `CurrentExchangeRate` (`base_currency`, `foreign_currency`);
