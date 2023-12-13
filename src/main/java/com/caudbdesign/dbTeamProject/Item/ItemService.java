package com.caudbdesign.dbTeamProject.Item;

import com.caudbdesign.dbTeamProject.Item.Stock.AnalystInfo;
import com.caudbdesign.dbTeamProject.Item.Stock.FinancialStatements;
import com.caudbdesign.dbTeamProject.Item.Stock.ItemInfoForm;
import com.caudbdesign.dbTeamProject.Item.Stock.StockRate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public ItemPriceInfo getItemPriceInfoByItemId(int itemId) {
    return itemRepository.getItemPriceInfoByItemId(itemId);
  }

  public ArrayList<ItemForm> getItemsbySearch(String search) {
    List<Item> items = itemRepository.getItemsbySearch(search);
    ArrayList<ItemForm> itemForms = new ArrayList<>();
    for (Item item : items) {
      CurrentPrice currentPrice = itemRepository.getCurrentPriceByItemId(item.getItem_id());
      ItemForm itemForm = new ItemForm();
      itemForm.setItem_id(item.getItem_id());
      itemForm.setName(item.getName());
      itemForm.setMarket_name(item.getMarket());
      itemForm.setPrice(currentPrice.getCurrent_price());
      itemForms.add(itemForm);
    }
    return itemForms;
  }

  public float getCurrentPriceByItemId(int itemId) {
    return itemRepository.getCurrentPriceByItemId(itemId).getCurrent_price();
  }

  public ItemInfoForm getItemInfoFormbyItemid(int Itemid, String report_period) {
    StockRate stockRate = itemRepository.getStockRateByItemId(Itemid);
    FinancialStatements financialStatements = itemRepository.getFinancialStatementsByItemId(Itemid, report_period);
    if(stockRate.getAnalyst_id() == null) {
      //FinancialStatments정보만 삽입
      ItemInfoForm itemInfoForm = new ItemInfoForm();
      itemInfoForm.setItem_id(stockRate.getItem_id());
      itemInfoForm.setAnalyst_id(stockRate.getAnalyst_id());
      itemInfoForm.setAnalyst_rating(stockRate.getAnalyst_rating());
      itemInfoForm.setTarget_price(stockRate.getTarget_price());
      itemInfoForm.setReport_date(financialStatements.getReport_date());
      itemInfoForm.setReport_period(financialStatements.getReport_period());
      return itemInfoForm;
    }
    AnalystInfo analystInfo = itemRepository.getAnalystInfoByAnalystId(stockRate.getAnalyst_id());
    ItemInfoForm itemInfoForm = new ItemInfoForm();
    itemInfoForm.setItem_id(stockRate.getItem_id());
    itemInfoForm.setAnalyst_id(stockRate.getAnalyst_id());
    itemInfoForm.setAnalyst_rating(stockRate.getAnalyst_rating());
    itemInfoForm.setTarget_price(stockRate.getTarget_price());
    itemInfoForm.setContact_info(analystInfo.getContact_info());
    itemInfoForm.setFirm(analystInfo.getFirm());
    itemInfoForm.setEquity(financialStatements.getEquity());
    itemInfoForm.setNet_income(financialStatements.getNet_income());
    itemInfoForm.setRevenue(financialStatements.getRevenue());
    itemInfoForm.setTotal_assets(financialStatements.getTotal_assets());
    itemInfoForm.setTotal_liabilities(financialStatements.getTotal_liabilities());
    itemInfoForm.setExpertise_area(analystInfo.getExpertise_area());
    itemInfoForm.setName(analystInfo.getName());
    itemInfoForm.setReport_date(financialStatements.getReport_date());
    itemInfoForm.setReport_period(financialStatements.getReport_period());
    return itemInfoForm;
  }

  public String getmarketByItemId(int itemId) {
    return itemRepository.getmarketByItemId(itemId);
  }


}
