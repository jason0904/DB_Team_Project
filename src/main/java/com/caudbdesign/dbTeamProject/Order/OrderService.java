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
public class OrderService {

  private final OrderRepository orderRepository;
  private final PortfolioRepository portfolioRepository;
  private final BalanceRepository balanceRepository;
  private final ItemRepository itemRepository;
  private final ExchangeRepository exchangeRepository;

  //1분뒤 업데이트
  @Async
  public void updateOrderStatus(int order_id, int quantity, int item_id, int account_id, String purchase_type, String market, float limit_price) {
    try {
      Thread.sleep(60000);
      orderRepository.updateOrderStatus(order_id);
      if(purchase_type.equals("SellOrder")) quantity = -quantity;
      portfolioRepository.updatePortfolio(item_id, quantity, account_id);
      if(market.equals("KOSPI")) {
        balanceRepository.updateBalance(account_id, balanceRepository.selectBalance(account_id).getTotal_Balance() + quantity * limit_price, balanceRepository.selectBalance(account_id).getKRW_Balance() + quantity * limit_price ,balanceRepository.selectBalance(account_id).getUSD_Balance());
        portfolioRepository.updateStockPortfolioTotalPurchasePrice(item_id, account_id, portfolioRepository.selectPortfolio(account_id).getTotal_purchase_price() + quantity * limit_price);
      } else {
        float KRWExchange = limit_price * exchangeRepository.selectRate("USD", "KRW").getCurrent_exchange_rate();
        balanceRepository.updateBalance(account_id, balanceRepository.selectBalance(account_id).getTotal_Balance() + quantity * KRWExchange, balanceRepository.selectBalance(account_id).getKRW_Balance(), balanceRepository.selectBalance(account_id).getUSD_Balance() + quantity * limit_price);
        portfolioRepository.updateStockPortfolioTotalPurchasePrice(item_id, account_id, portfolioRepository.selectPortfolio(account_id).getTotal_purchase_price() + quantity * limit_price);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public boolean createOrder(Order order, OrderType orderType) {
    String market = itemRepository.getItemByItemId(order.getItem_id()).get().getMarket();
    if(orderType.getQuantity() * orderType.getLimit_price() > balanceRepository.selectBalance(order.getAccount_id()).getKRW_Balance() && market.equals("KOSPI")) return false;
    if(orderType.getQuantity() * orderType.getLimit_price() > balanceRepository.selectBalance(order.getAccount_id()).getUSD_Balance() && market.equals("NYSC")) return false;
    orderRepository.insertOrder(order, orderType);
    updateOrderStatus(order.getOrder_id(), orderType.getQuantity(), order.getItem_id(), order.getAccount_id(), order.getPurchase_type(), market, orderType.getLimit_price());
    return true;
  }

  public boolean deleteOrder(int order_id) {
    Order order = orderRepository.findOrderById(order_id);
    if(order.getOrder_status().equals("success")) return false;
    orderRepository.deleteOrder(order_id);
    return true;
  }

  public boolean amendOrder(int order_id, int quantity) {
    Order order = orderRepository.findOrderById(order_id);
    OrderType orderType = orderRepository.findOrderTypeById(order_id);
    if(order.getOrder_status().equals("success")) return false;
    deleteOrder(order_id);
    orderType.setQuantity(quantity);
    createOrder(order, orderType);
    return true;
  }

}
