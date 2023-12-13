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
    function CalculateInvestment(p_account_id int, p_item_id int) returns decimal(20, 6)
    deterministic
BEGIN
    DECLARE investmentReturn DECIMAL(20, 6);
    DECLARE currentPrice DECIMAL(20, 6);
    DECLARE p_quantity INT;
    DECLARE totalInvestment DECIMAL(20, 6);

    SELECT quantity INTO p_quantity FROM ItemPortfolio WHERE account_id = p_account_id AND item_id = p_item_id;
    SELECT current_price INTO currentPrice FROM CurrentPrice WHERE item_id = p_item_id ORDER BY updated_at DESC LIMIT 1;
    SELECT total_purchase_price INTO totalInvestment FROM ItemPortfolio WHERE account_id = p_account_id AND item_id = p_item_id;

    SET investmentReturn = currentPrice * p_quantity - totalInvestment;
    RETURN investmentReturn;
END$$

DELIMITER ;

DELIMITER //

CREATE FUNCTION CheckOrderValidity(p_item_id INT, p_quantity INT, p_account_id INT)
    RETURNS BOOLEAN
    DETERMINISTIC
BEGIN
    DECLARE v_currencyType VARCHAR(3);
    DECLARE v_itemPrice DECIMAL(65, 7);
    DECLARE v_balance DECIMAL(65, 7);
    DECLARE v_sufficientBalance BOOLEAN;

    -- 아이템의 마켓을 확인하여 통화 유형 결정
    SELECT IF(LEFT(market, 1) = 'k', 'KRW', 'USD') INTO v_currencyType
    FROM Item
    WHERE item_id = p_item_id;

    -- 해당 아이템의 현재 가격 조회
    SELECT current_price INTO v_itemPrice
    FROM CurrentPrice
    WHERE item_id = p_item_id;

    -- 사용자의 해당 통화 잔액 조회
    IF v_currencyType = 'KRW' THEN
        SELECT krw_balance INTO v_balance
        FROM Balance
        WHERE account_id = p_account_id;
    ELSE
        SELECT usd_balance INTO v_balance
        FROM Balance
        WHERE account_id = p_account_id;
    END IF;

    -- 잔액이 충분한지 확인
    SET v_sufficientBalance = v_balance >= (v_itemPrice * p_quantity);

    RETURN v_sufficientBalance;
END //

DELIMITER ;


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



