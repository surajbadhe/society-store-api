package com.tvf.societystore.controller;

import com.tvf.societystore.dto.cart.AddToCartRequest;
import com.tvf.societystore.dto.cart.CartResponse;
import com.tvf.societystore.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('RESIDENT')") // Secures all methods in this class
public class CartController {

    private final CartService cartService;

    /**
     * Endpoint for a RESIDENT to view their own cart.
     */
    @GetMapping
    public ResponseEntity<CartResponse> getMyCart() {
        return ResponseEntity.ok(cartService.getCartForCurrentUser());
    }

    /**
     * Endpoint for a RESIDENT to add an item to their cart.
     */
    @PostMapping
    public ResponseEntity<CartResponse> addItemToMyCart(@Valid @RequestBody AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addItemToCart(request));
    }

    /**
     * Endpoint for a RESIDENT to remove an item from their cart.
     */
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeItemFromMyCart(@PathVariable Long itemId) {
        cartService.removeItemFromCart(itemId);
        return ResponseEntity.noContent().build();
    }
}