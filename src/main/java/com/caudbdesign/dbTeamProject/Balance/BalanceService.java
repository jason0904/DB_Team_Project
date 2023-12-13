package com.caudbdesign.dbTeamProject.Balance;

import com.caudbdesign.dbTeamProject.Item.ItemRepository;
import com.caudbdesign.dbTeamProject.ItemPortfolio.ItemPortfolio;
import com.caudbdesign.dbTeamProject.ItemPortfolio.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {

  private final BalanceRepository balanceRepository;
  private final PortfolioRepository portfolioRepository;
  private final ItemRepository itemRepository;

  public Balance selectBalance(Integer account_id) {
    return balanceRepository.selectBalance(account_id);
  }


  public List<BalanceForm> showBalanceForm(Integer account_id) {


    List<ItemPortfolio> itemPortfolios = portfolioRepository.selectPortfolio(account_id);
    List<BalanceForm> balanceForms = new ArrayList<>();

    float totalValue = portfolioRepository.totalValue(account_id);

    for(ItemPortfolio itemPortfolio : itemPortfolios) {

      BalanceForm balanceForm = new BalanceForm();

      float currentValue = itemPortfolio.getCurrent_price() * itemPortfolio.getQuantity();
      float purchasePrice = (float) (itemPortfolio.getTotal_purchase_price() / itemPortfolio.getQuantity());
      float profitLoss = (float) (currentValue - itemPortfolio.getTotal_purchase_price());

      balanceForm.setItem_id(itemPortfolio.getItem_id());
      balanceForm.setName(itemRepository.getNameByItemId(itemPortfolio.getItem_id()));
      balanceForm.setProfit_loss(profitLoss); // current_value - total_purchase_price
      balanceForm.setQuantity(itemPortfolio.getQuantity());
      balanceForm.setCurrent_value(currentValue);
      balanceForm.setWeight(currentValue/totalValue);  // ( currentValue/totalValue)
      balanceForm.setPurchase_price(purchasePrice);
      balanceForm.setPurchase_amount(itemPortfolio.getTotal_purchase_price());
      balanceForm.setCurrent_price(itemRepository.getOnlyCurrentPriceByItemId(itemPortfolio.getItem_id()));

      balanceForms.add(balanceForm);
    }
    return balanceForms;


  }

  public float returnRate(Integer account_id) {
    List<Integer> item_id_list = portfolioRepository.selectItemIdInPortfolioByAccountId(account_id);
    double total_investment = 0;
    double total_purchase_price = portfolioRepository.totalPriceSum(account_id);
    for(int item_id : item_id_list) {
      float investment = portfolioRepository.calculateTotalReturnInKrw(item_id, account_id);
      total_investment += investment;
    }
    return (float) (total_investment / total_purchase_price) * 100;
  }

}
