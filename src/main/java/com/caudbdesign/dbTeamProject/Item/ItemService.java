package com.caudbdesign.dbTeamProject.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public ItemPrice_Info getItemPriceInfoByItemId(int itemId) {
    return itemRepository.getItemPriceInfoByItemId(itemId);
  }

  
}
