package com.caudbdesign.dbTeamProject.Order;

import com.caudbdesign.dbTeamProject.Balance.BalanceRepository;
import com.caudbdesign.dbTeamProject.Exchange.ExchangeRepository;
import com.caudbdesign.dbTeamProject.Item.ItemRepository;
import com.caudbdesign.dbTeamProject.ItemPortfolio.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSuccess {


  private final OrderRepository orderRepository;
  private final PortfolioRepository portfolioRepository;
  private final BalanceRepository balanceRepository;
  private final ExchangeRepository exchangeRepository;
  private final ItemRepository itemRepository;

  @Async
  public void updateOrderStatus(int order_id, int quantity, int item_id, int account_id, String purchase_type, String market, float limit_price) {
    try {
      Thread.sleep(60000);
      if(orderRepository.findOrderById(order_id).getOrder_status().equalsIgnoreCase("cancelled")) return;
      orderRepository.updateOrderStatus(order_id);
      if(purchase_type.equalsIgnoreCase("buyorder")) quantity = quantity * -1;
      if(!portfolioRepository.checkPortfolioPresent(account_id, item_id)) {
        portfolioRepository.makePortfolio(account_id, item_id, 0, 0, 0);
      }
      if(market.equals("KOSPI")) {
        balanceRepository.updateBalance(account_id, balanceRepository.selectBalance(account_id).getTotal_Balance() + quantity * limit_price, balanceRepository.selectBalance(account_id).getKRW_Balance() + quantity * limit_price ,balanceRepository.selectBalance(account_id).getUSD_Balance());
        portfolioRepository.updateStockPortfolioTotalPurchasePrice(item_id, account_id, portfolioRepository.selectPortfoliobyItemId(account_id, item_id).getTotal_purchase_price()
            + quantity * -1 * limit_price);
      } else {
        float KRWExchange = limit_price * exchangeRepository.selectRate("USD", "KRW").getCurrent_exchange_rate();
        balanceRepository.updateBalance(account_id, balanceRepository.selectBalance(account_id).getTotal_Balance() + quantity * KRWExchange, balanceRepository.selectBalance(account_id).getKRW_Balance(), balanceRepository.selectBalance(account_id).getUSD_Balance() + quantity * limit_price);
        portfolioRepository.updateStockPortfolioTotalPurchasePrice(item_id, account_id, portfolioRepository.selectPortfoliobyItemId(account_id, item_id).getTotal_purchase_price()
            + quantity * -1 * limit_price);
      }
      portfolioRepository.updatePortfolio(item_id, portfolioRepository.selectPortfoliobyItemId(account_id, item_id).getQuantity() + quantity * -1, account_id);
      portfolioRepository.updateCurrentPrice(item_id,itemRepository.getCurrentPriceByItemId(item_id).getCurrent_price());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
