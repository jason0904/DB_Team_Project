package com.caudbdesign.dbTeamProject.Scheduler;

import com.caudbdesign.dbTeamProject.Item.ItemRepository;
import com.caudbdesign.dbTeamProject.ItemPortfolio.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrentPriceScheduler {

  private final PortfolioRepository portfolioRepository;
  private final ItemRepository itemRepository;

  //1시간마다 실행
  @Async
  @Scheduled(fixedDelay = 60 * 60 * 1000)
  public void updateCurrentPriceToPortfolio() {
    List<Integer> item_id_list = portfolioRepository.selectAllItemIdInPortfolio();
    for (Integer item_id : item_id_list) {
      float current_price = itemRepository.getCurrentPriceByItemId(item_id).getCurrent_price();
      portfolioRepository.updateCurrentPrice(item_id, current_price);
    }
  }
}
