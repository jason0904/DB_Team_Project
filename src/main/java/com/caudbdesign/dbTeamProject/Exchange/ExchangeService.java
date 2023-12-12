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
  private final BalanceRepository balanceRepository;

  public boolean exchange(int account_id, String base_currency, String foreign_currency, float amount) {
    return exchangeRepository.exchange(account_id, base_currency, foreign_currency, amount);
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

  public float showExchangeRateByDay(String base_currency, String foreign_currency, String day) {
    return exchangeRepository.selectRateByDay(base_currency, foreign_currency, day);
  }
}
