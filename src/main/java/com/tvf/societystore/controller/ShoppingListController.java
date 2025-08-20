package com.tvf.societystore.controller;

import com.tvf.societystore.dto.shoppinglist.ShoppingListItemDTO;
import com.tvf.societystore.service.ShoppingListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/shopping-list")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('RESIDENT')")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping
    public ResponseEntity<List<ShoppingListItemDTO>> getMyList() {
        return ResponseEntity.ok(shoppingListService.getMyShoppingList());
    }

    @PostMapping
    public ResponseEntity<ShoppingListItemDTO> addItem(@Valid @RequestBody ShoppingListItemDTO itemDTO) {
        ShoppingListItemDTO createdItem = shoppingListService.addItemToList(itemDTO);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        shoppingListService.removeItemFromList(itemId);
        return ResponseEntity.noContent().build();
    }
}
