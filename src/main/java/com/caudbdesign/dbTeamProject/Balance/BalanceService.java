package com.caudbdesign.dbTeamProject.Balance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

  private final BalanceRepository balanceRepository;

  public Balance selectBalance(Integer account_id) {
    return balanceRepository.selectBalance(account_id);
  }

}
