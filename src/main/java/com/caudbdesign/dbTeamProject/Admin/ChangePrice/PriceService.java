package com.caudbdesign.dbTeamProject.Admin.ChangePrice;

import com.caudbdesign.dbTeamProject.Item.ItemPriceInfo;
import com.caudbdesign.dbTeamProject.Item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;
    private final ItemRepository itemRepository;

    public void updatePrice(PriceForm priceForm) {
      ItemPriceInfo itemPriceInfo = itemRepository.getItemPriceInfoByItemId(priceForm.getItem_id());
      //우선 시가 종가 업뎃
      priceRepository.updatePrice(priceForm.getItem_id(), priceForm.getOpening_price(), priceForm.getClosing_price(), priceForm.getCurrent_price());
      //currentPrice도
      priceRepository.updateCurrentPrice(priceForm.getItem_id(), priceForm.getCurrent_price());
      //daily, week, historical high low 업뎃
      if (itemPriceInfo.getDaily_high() < priceForm.getCurrent_price()) {
        priceRepository.updateDailyHighLow(priceForm.getItem_id(), priceForm.getCurrent_price());
      }
      if (itemPriceInfo.getDaily_low() > priceForm.getCurrent_price()) {
        priceRepository.updateDailyHighLow(priceForm.getItem_id(), priceForm.getCurrent_price());
      }
      if (itemPriceInfo.getWeek_52_high() < priceForm.getCurrent_price()) {
        priceRepository.updateWeek52HighLow(priceForm.getItem_id(), priceForm.getCurrent_price());
      }
      if (itemPriceInfo.getWeek_52_low() > priceForm.getCurrent_price()) {
        priceRepository.updateWeek52HighLow(priceForm.getItem_id(), priceForm.getCurrent_price());
      }
      if (itemPriceInfo.getHistorical_high() < priceForm.getCurrent_price()) {
        priceRepository.updateHistoricalHighLow(priceForm.getItem_id(), priceForm.getCurrent_price());
      }
      if (itemPriceInfo.getHistorical_low() > priceForm.getCurrent_price()) {
        priceRepository.updateHistoricalHighLow(priceForm.getItem_id(), priceForm.getCurrent_price());
      }
    }

}
