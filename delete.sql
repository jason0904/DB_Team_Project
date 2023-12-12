use caudbdesign;

SET FOREIGN_KEY_CHECKS = 0;
truncate Account ;
truncate AccountLoginLog;
truncate AccountLoginPassword;
truncate AccountLoginMeta;
truncate AdditionalContactInfo;
truncate Address;
truncate AnalystInfo;
truncate Balance;
truncate Bond;
truncate BuyOrder;
truncate CurrencyExchange;
truncate CurrentExchangeRate;
truncate CurrentPrice;
truncate ExchangeRateHistory;
truncate FinancialStatements;
truncate GeneralAccount;
truncate Gold;
truncate GoldAccount;
truncate Item;
truncate ItemPortfolio;
truncate ItemPriceInfo;
truncate `Order`;
truncate Organization;
truncate Personal;
truncate SellOrder;
truncate SensitiveInfo;
truncate Stock;
truncate StockData;
truncate StockRate;
truncate User;
truncate UserLoginLog;
truncate UserLoginMeta;
truncate UserLoginPassword;
truncate UserLoginQuestion;
SET FOREIGN_KEY_CHECKS = 1;