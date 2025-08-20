package com.tvf.societystore.service;

import com.tvf.societystore.dto.cart.AddToCartRequest;
import com.tvf.societystore.dto.cart.CartResponse;

public interface CartService {
    CartResponse getCartForCurrentUser();
    CartResponse addItemToCart(AddToCartRequest request);
    void removeItemFromCart(Long cartItemId);
}
