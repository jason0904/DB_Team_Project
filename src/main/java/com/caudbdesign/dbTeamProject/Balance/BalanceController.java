package com.caudbdesign.dbTeamProject.Balance;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @PostMapping("/api/balance")
    @CrossOrigin
    public ResponseEntity<?> balance(@RequestBody Balance balance) {
        int account_id = balance.getAccount_id();
        Balance accountBalance = balanceService.selectBalance(account_id);
        BalanceOutputForm balanceOutputForm = new BalanceOutputForm();
        balanceOutputForm.setAccount_id(account_id);
        balanceOutputForm.setReturn_rate(balanceService.returnRate(account_id));
        balanceOutputForm.setKRW_Balance(accountBalance.getKRW_Balance());
        balanceOutputForm.setUSD_Balance(accountBalance.getUSD_Balance());
        balanceOutputForm.setTotal_Balance(accountBalance.getTotal_Balance());
        balanceOutputForm.setItemPortfolios(balanceService.showBalanceForm(account_id));
        return ResponseEntity.ok(balanceOutputForm);
    }

}
