CREATE TABLE `users` (
  `Uid` integer PRIMARY KEY,
  `username` varchar(255),
  `usertype` varchar(255),
  `account_status` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `personal` (
  `Uid` integer PRIMARY KEY,
  `age` integer,
  `social_id_hash` varchar(255)
);

CREATE TABLE `organization` (
  `Uid` integer PRIMARY KEY,
  `businessid_hash` varchar(255)
);

CREATE TABLE `login` (
  `Uid` integer PRIMARY KEY,
  `ID` varchar(255),
  `password_hash` varchar(255)
);

CREATE TABLE `login_log` (
  `log_id` integer PRIMARY KEY,
  `Uid` integer,
  `attempt_time` timestamp,
  `attempt_status` varchar(255),
  `ip_address` varchar(255)
);

CREATE TABLE `sensitive_info` (
  `Uid` integer PRIMARY KEY,
  `email` varchar(255),
  `phone` varchar(255),
  `secondary_email` varchar(255),
  `secondary_phone` varchar(255)
);

CREATE TABLE `address` (
  `address_id` integer PRIMARY KEY,
  `Uid` integer,
  `street_address` varchar(255),
  `city` varchar(255),
  `state` varchar(255),
  `postal_code` varchar(255),
  `country` varchar(255)
);

CREATE TABLE `certificate_auth` (
  `certificate_id` integer PRIMARY KEY,
  `Uid` integer,
  `certificate_data` varchar(255),
  `issued_at` timestamp,
  `expires_at` timestamp
);

CREATE TABLE `otp_auth` (
  `otp_id` integer PRIMARY KEY,
  `Uid` integer,
  `secret_key` varchar(255),
  `devicename` varchar(255),
  `last_used` timestamp
);

CREATE TABLE `Account` (
  `account_id` integer PRIMARY KEY,
  `Uid` integer,
  `account_number` varchar(255),
  `account_type` varchar(255),
  `stock_value` decimal,
  `status` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `Balance` (
  `account_id` integer PRIMARY KEY,
  `KRW_balance` decimal,
  `USD_balance` decimal
);

CREATE TABLE `GeneralAccount` (
  `account_id` integer PRIMARY KEY,
  `Available_offer_Type` varchar(255)
);

CREATE TABLE `ISAAccount` (
  `account_id` integer PRIMARY KEY,
  `tax_benefit` varchar(255),
  `Available_offer_Type` varchar(255)
);

CREATE TABLE `GoldAccount` (
  `account_id` integer PRIMARY KEY,
  `Available_offer_Type` varchar(255)
);

CREATE TABLE `StockPortfolio` (
  `portfolio_id` integer PRIMARY KEY,
  `account_id` integer,
  `stock_symbol` varchar(255),
  `quantity` integer,
  `average_purchase_price` decimal,
  `current_price` decimal,
  `created_at` timestamp
);

CREATE TABLE `Order` (
  `order_id` integer PRIMARY KEY,
  `account_id` integer,
  `item_id` integer,
  `purchase_type` varchar(255),
  `quantity` integer,
  `order_type` varchar(255),
  `limit_price` decimal,
  `order_status` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `StockTradeLog` (
  `trade_id` integer PRIMARY KEY,
  `order_id` integer,
  `executed_quantity` integer,
  `executed_price` decimal,
  `trade_time` timestamp
);

CREATE TABLE `AccountPassword` (
  `account_id` integer,
  `password_hash` varchar(255),
  `updated_at` timestamp
);

CREATE TABLE `AccountLoginLog` (
  `log_id` integer PRIMARY KEY,
  `account_id` integer,
  `login_time` timestamp,
  `login_status` varchar(255),
  `ip_address` varchar(255),
  `device_info` varchar(255)
);

CREATE TABLE `CurrencyExchange` (
  `exchange_id` integer PRIMARY KEY,
  `account_id` integer,
  `from_currency` varchar(255),
  `to_currency` varchar(255),
  `from_amount` decimal,
  `to_amount` decimal,
  `rate_id` decimal,
  `exchange_time` timestamp
);

CREATE TABLE `ExchangeRate` (
  `rate_id` integer PRIMARY KEY,
  `rate` decimal,
  `last_updated` timestamp
);

CREATE TABLE `Item` (
  `item_id` integer PRIMARY KEY,
  `symbol` varchar(255),
  `name` varchar(255),
  `item_type` varchar(255),
  `market_id` varchar(255),
  `closing_price` decimal,
  `opening_price` decimal,
  `daily_high` decimal,
  `daily_low` decimal,
  `52week_high` decimal,
  `52week_low` decimal,
  `volume` bigint,
  `market_cap` bigint,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `CurrentPrice` (
  `price_id` integer PRIMARY KEY AUTO_INCREMENT,
  `item_id` integer,
  `current_price` decimal,
  `timestamp` timestamp
);

CREATE TABLE `MarketInfo` (
  `market_id` integer PRIMARY KEY,
  `market_name` varchar(255),
  `country` varchar(255),
  `time_zone` varchar(255),
  `opening_time` time,
  `closing_time` time,
  `updated_at` timestamp
);

CREATE TABLE `Stock` (
  `item_id` integer PRIMARY KEY,
  `sector` varchar(255),
  `industry` varchar(255),
  `dividend_yield` decimal,
  `dividend_payment_date` timestamp
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

ALTER TABLE `personal` ADD FOREIGN KEY (`Uid`) REFERENCES `users` (`Uid`);

ALTER TABLE `organization` ADD FOREIGN KEY (`Uid`) REFERENCES `users` (`Uid`);

ALTER TABLE `login` ADD FOREIGN KEY (`Uid`) REFERENCES `users` (`Uid`);

ALTER TABLE `login_log` ADD FOREIGN KEY (`Uid`) REFERENCES `users` (`Uid`);

ALTER TABLE `sensitive_info` ADD FOREIGN KEY (`Uid`) REFERENCES `users` (`Uid`);

ALTER TABLE `address` ADD FOREIGN KEY (`Uid`) REFERENCES `users` (`Uid`);

ALTER TABLE `certificate_auth` ADD FOREIGN KEY (`Uid`) REFERENCES `users` (`Uid`);

ALTER TABLE `otp_auth` ADD FOREIGN KEY (`Uid`) REFERENCES `users` (`Uid`);

ALTER TABLE `Account` ADD FOREIGN KEY (`Uid`) REFERENCES `users` (`Uid`);

ALTER TABLE `Balance` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `GeneralAccount` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `ISAAccount` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `GoldAccount` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `StockPortfolio` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `Order` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `Order` ADD FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

ALTER TABLE `StockTradeLog` ADD FOREIGN KEY (`order_id`) REFERENCES `Order` (`order_id`);

ALTER TABLE `AccountPassword` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `AccountLoginLog` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `CurrencyExchange` ADD FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`);

ALTER TABLE `CurrencyExchange` ADD FOREIGN KEY (`rate_id`) REFERENCES `ExchangeRate` (`rate_id`);

ALTER TABLE `CurrentPrice` ADD FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

ALTER TABLE `MarketInfo` ADD FOREIGN KEY (`market_id`) REFERENCES `Item` (`market_id`);

ALTER TABLE `Stock` ADD FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

ALTER TABLE `Bond` ADD FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

ALTER TABLE `Gold` ADD FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);
