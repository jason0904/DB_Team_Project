package com.caudbdesign.dbTeamProject.Exchange;


import com.caudbdesign.dbTeamProject.Account.AccountRepository;
import com.caudbdesign.dbTeamProject.Balance.Balance;
import com.caudbdesign.dbTeamProject.Balance.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeService {

  private final ExchangeRepository exchangeRepository;
  private final AccountRepository accountRepository;
  private final BalanceRepository balanceRepository;

  public boolean Exchange(int account_id, float base_amount, String base_currency, String foreign_currency) {
    if(!accountRepository.isAccountExist(account_id)) {
      return false;
    }
    Balance balance = balanceRepository.selectBalance(account_id);
    if(base_currency.equals("KRW")) {
      if(balance.getKRW_Balance() < base_amount) {
        return false;
      }
    }
    else if(base_currency.equals("USD")) {
      if(balance.getUSD_Balance() < base_amount) {
        return false;
      }
    }
    CurrentExchangeRate currentExchangeRate = exchangeRepository.selectRate(base_currency, foreign_currency);
    float foreign_amount = base_amount * currentExchangeRate.getCurrent_exchange_rate();
    CurrencyExchange currencyExchange = new CurrencyExchange();
    currencyExchange.setAccount_id(account_id);
    currencyExchange.setBase_amount(base_amount);
    currencyExchange.setBase_currency(base_currency);
    currencyExchange.setForeign_currency(foreign_currency);
    currencyExchange.setForeign_amount(foreign_amount);
    exchangeRepository.save(currencyExchange);
    if(base_currency.equals("KRW")) {
      balance.setKRW_Balance(balance.getKRW_Balance() - base_amount);
      balance.setUSD_Balance(balance.getUSD_Balance() + foreign_amount);
    }
    else if(base_currency.equals("USD")) {
      balance.setUSD_Balance(balance.getUSD_Balance() - base_amount);
      balance.setKRW_Balance(balance.getKRW_Balance() + foreign_amount);
    }
    balanceRepository.updateBalance(balance.getAccount_id(), balance.getTotal_Balance(), balance.getKRW_Balance(), balance.getUSD_Balance());
    return true;
  }

  public ExchangeForm showExchangeForm(int account_id) {
    Balance balance = balanceRepository.selectBalance(account_id);
    ExchangeForm exchangeForm = new ExchangeForm();
    exchangeForm.setKRW_Balance(balance.getKRW_Balance());
    exchangeForm.setUSD_Balance(balance.getUSD_Balance());
    CurrentExchangeRate currentExchangeRate = exchangeRepository.selectRate("KRW", "USD");
    exchangeForm.setCurrent_exchange_rate(currentExchangeRate.getCurrent_exchange_rate());
    return exchangeForm;
  }

  public CurrentExchangeRate showCurrentExchangeRate(String base_currency, String foreign_currency) {
    return exchangeRepository.selectRate(base_currency, foreign_currency);
  }
}
