package com.caudbdesign.dbTeamProject.Log;

import com.caudbdesign.dbTeamProject.Order.Order;
import com.caudbdesign.dbTeamProject.Order.OrderRepository;
import java.util.ArrayList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class StockTradeLogService {

  private final OrderRepository orderRepository;
  private final StockTradeLogRepository stockTradeLogRepository;

  public ArrayList<StockTradeLog> getTradeLogByAccountId(int accountId, String trade_time) {
    ArrayList<Integer> order_id_list = orderRepository.selectOrderIdByAccountId(accountId);
    ArrayList<StockTradeLog> stockTradeLogArrayList = new ArrayList<>();
    for (int order_id : order_id_list) {
      stockTradeLogArrayList.add(stockTradeLogRepository.selectTradeLogByOrderID(order_id, trade_time));
    }
    return stockTradeLogArrayList;
  }

}
