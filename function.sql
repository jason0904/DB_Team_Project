DELIMITER $$

CREATE FUNCTION ExchangeCurrency(
    p_account_id INT,
    p_base_currency VARCHAR(3),
    p_foreign_currency VARCHAR(3),
    p_base_amount DECIMAL(65,7),
    p_exchange_rate DECIMAL(65,7)
) RETURNS BOOLEAN DETERMINISTIC
BEGIN
    DECLARE v_base_balance DECIMAL(65,7);
    DECLARE v_foreign_balance DECIMAL(65,7);
    DECLARE v_required_foreign_amount DECIMAL(65,7);
    DECLARE v_total_balance DECIMAL(65,7);
    DECLARE v_success BOOLEAN;

    -- 필요한 기본 통화량 계산
    SET v_required_foreign_amount = p_base_amount * p_exchange_rate;

    -- 잔액 확인
    SELECT krw_balance, usd_balance, total_balance INTO v_base_balance, v_foreign_balance, v_total_balance
    FROM Balance
    WHERE account_id = p_account_id;

    -- 잔액 확인 및 환전 처리
    IF (p_base_currency = 'KRW' AND v_base_balance >= p_base_amount) OR
       (p_base_currency = 'USD' AND v_foreign_balance >= p_base_amount) THEN

        -- Balance 테이블 업데이트
        UPDATE Balance
        SET krw_balance = IF(p_base_currency = 'KRW', v_base_balance - p_base_amount, v_base_balance + v_required_foreign_amount),
            usd_balance = IF(p_base_currency = 'USD', v_foreign_balance - p_base_amount, v_foreign_balance + v_required_foreign_amount)
        WHERE account_id = p_account_id;

            -- CurrencyExchange 테이블 업데이트
            INSERT INTO CurrencyExchange (account_id, base_currency, foreign_currency, base_amount, foreign_amount, exchange_time)
        VALUES (p_account_id, p_base_currency, p_foreign_currency, p_base_amount, v_required_foreign_amount, NOW());

        SET v_success = TRUE;
    ELSE
        SET v_success = FALSE;
    END IF;

    RETURN v_success;
END $$

DELIMITER ;

DELIMITER $$

create
    definer = root@`%` function CalculateInvestment(p_account_id int, p_item_id int) returns decimal(20, 6)
    deterministic
BEGIN
    DECLARE investmentReturn DECIMAL(20, 6);
    DECLARE currentPrice DECIMAL(20, 6);
    DECLARE quantity INT;
    DECLARE totalInvestment DECIMAL(20, 6);

    SELECT quantity INTO quantity FROM ItemPortfolio WHERE account_id = p_account_id AND item_id = p_item_id;
    -- 수량이 NULL인 경우 함수 종료
    IF quantity IS NULL THEN
        RETURN 1234;
    END IF;
    SELECT current_price INTO currentPrice FROM CurrentPrice WHERE item_id = p_item_id ORDER BY updated_at DESC LIMIT 1;
    SELECT total_purchase_price INTO totalInvestment FROM ItemPortfolio WHERE account_id = p_account_id AND item_id = p_item_id;

    SET investmentReturn = currentPrice * quantity - totalInvestment;
    RETURN investmentReturn;
END$$

DELIMITER ;

DELIMITER $$

CREATE FUNCTION CheckOrderValidity(input_order_id INT, input_account_id INT)
    RETURNS BOOLEAN DETERMINISTIC
BEGIN
    DECLARE itemMarket CHAR(1);
    DECLARE accountBalance DECIMAL(65, 7);
    DECLARE orderAmount DECIMAL(65, 7);
    DECLARE currencyType VARCHAR(3);
    DECLARE purchaseType CHAR(1);

    -- Order의 purchase_type과 Item의 market 정보를 가져옵니다.
    SELECT `Order`.purchase_type, Item.market INTO purchaseType, itemMarket
    FROM `Order` INNER JOIN Item ON `Order`.item_id = Item.item_id
    WHERE `Order`.order_id = input_order_id LIMIT 1;

    -- 화폐 타입 결정
    IF LEFT(itemMarket, 1) = 'k' THEN
        SET currencyType = 'KRW';
    ELSE
        SET currencyType = 'USD';
    END IF;

    -- 계정의 잔액을 가져옵니다.
    SELECT IF(currencyType = 'KRW', krw_balance, usd_balance) INTO accountBalance
    FROM Balance
    WHERE account_id = input_account_id LIMIT 1;

    -- 주문 금액 계산
    IF LEFT(purchaseType, 1) = 'B' THEN
        SELECT quantity * limit_price INTO orderAmount
        FROM `BuyOrder`
        WHERE order_id = input_order_id LIMIT 1;
    ELSE
        SELECT quantity * limit_price INTO orderAmount
        FROM `SellOrder`
        WHERE order_id = input_order_id LIMIT 1;
    END IF;

    -- 잔액과 주문 금액 비교
    RETURN accountBalance >= orderAmount;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE UpdateItemPriceInfo(IN p_item_id INTEGER, IN p_closing_price DECIMAL(65,7), IN p_opening_price DECIMAL(15,2))
BEGIN
    UPDATE ItemPriceInfo
    SET closing_price = p_closing_price, opening_price = p_opening_price, updated_at = NOW()
    WHERE item_id = p_item_id;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE UpdateStockPortfolioTotalPurchasePrice(IN p_account_id INTEGER, IN p_item_id INTEGER, IN p_total_purchase_price DECIMAL(15,2))
BEGIN
    UPDATE ItemPortfolio
    SET total_purchase_price = p_total_purchase_price
    WHERE account_id = p_account_id AND item_id = p_item_id;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE FinalizeOrder(IN p_order_id INTEGER)
BEGIN
    UPDATE `Order`
    SET order_status = 'success', success_at = NOW()
    WHERE order_id = p_order_id;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER ResetAccountLoginAttempts AFTER UPDATE ON AccountLoginPassword
    FOR EACH ROW
BEGIN
    IF NEW.password_hash = OLD.password_hash THEN
        UPDATE AccountLoginMeta SET login_attempt = 0 WHERE account_id = NEW.account_id;
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER IncreaseAccountLoginAttempts AFTER INSERT ON AccountLoginLog
    FOR EACH ROW
BEGIN
    IF NEW.login_status = 'fail' THEN
        UPDATE AccountLoginMeta SET login_attempt = AccountLoginMeta.login_attempt + 1 WHERE account_id = NEW.account_id;
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER ResetUserLoginAttempts AFTER UPDATE ON UserLoginPassword
    FOR EACH ROW
BEGIN
    IF NEW.password_hash = OLD.password_hash THEN
        UPDATE UserLoginMeta SET login_attempt = 0 WHERE uid = NEW.uid;
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER IncreaseUserLoginAttempts AFTER INSERT ON UserLoginLog
    FOR EACH ROW
BEGIN
    IF NEW.login_status = 'fail' THEN
        UPDATE UserLoginMeta SET login_attempt = login_attempt + 1 WHERE uid = NEW.uid;
    END IF;
END$$

DELIMITER ;



