package com.caudbdesign.dbTeamProject.Item;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public ItemPrice_Info getItemPriceInfoByItemId(int itemId) {
    return itemRepository.getItemPriceInfoByItemId(itemId);
  }

  public ArrayList<ItemForm> getItemsbySearch(String search) {
    List<Item> items = itemRepository.getItemsbySearch(search);
    ArrayList<ItemForm> itemForms = new ArrayList<>();
    for (Item item : items) {
      ItemPrice_Info itemPrice_info = itemRepository.getItemPriceInfoByItemId(item.getItem_id());
      ItemForm itemForm = new ItemForm();
      itemForm.setItem_id(item.getItem_id());
      itemForm.setName(item.getName());
      itemForm.setMarket_name(item.getMarket());
      itemForm.setPrice(itemPrice_info.getCurrent_price());
      itemForms.add(itemForm);
    }
    return itemForms;
  }


}
