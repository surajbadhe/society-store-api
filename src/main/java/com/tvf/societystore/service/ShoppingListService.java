package com.tvf.societystore.service;

import com.tvf.societystore.dto.shoppinglist.ShoppingListItemDTO;
import java.util.List;

public interface ShoppingListService {
    List<ShoppingListItemDTO> getMyShoppingList();
    ShoppingListItemDTO addItemToList(ShoppingListItemDTO itemDTO);
    void removeItemFromList(Long itemId);
}
