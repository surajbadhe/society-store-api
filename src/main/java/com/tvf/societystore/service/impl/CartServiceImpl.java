package com.tvf.societystore.service.impl;

import com.tvf.societystore.dto.cart.AddToCartRequest;
import com.tvf.societystore.dto.cart.CartItemResponse;
import com.tvf.societystore.dto.cart.CartResponse;
import com.tvf.societystore.dto.product.ProductDTO;
import com.tvf.societystore.entity.CartItem;
import com.tvf.societystore.entity.Product;
import com.tvf.societystore.entity.User;
import com.tvf.societystore.exception.ResourceNotFoundException;
import com.tvf.societystore.repository.CartItemRepository;
import com.tvf.societystore.repository.ProductRepository;
import com.tvf.societystore.repository.UserRepository;
import com.tvf.societystore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public CartResponse getCartForCurrentUser() {
        User currentUser = getCurrentUser();
        List<CartItem> cartItems = cartItemRepository.findByUserId(currentUser.getId());
        return mapToCartResponse(currentUser, cartItems);
    }

    @Override
    public CartResponse addItemToCart(AddToCartRequest request) {
        User currentUser = getCurrentUser();
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setUser(currentUser);
        cartItem.setProduct(product);
        cartItem.setQuantity(request.quantity());

        cartItemRepository.save(cartItem);

        return getCartForCurrentUser();
    }

    @Override
    public void removeItemFromCart(Long cartItemId) {
        User currentUser = getCurrentUser();
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        if (!cartItem.getUser().getId().equals(currentUser.getId())) {
            throw new SecurityException("User not authorized to delete this cart item");
        }

        cartItemRepository.deleteById(cartItemId);
    }

    private User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Current user not found"));
    }

    private CartResponse mapToCartResponse(User user, List<CartItem> cartItems) {
        List<CartItemResponse> itemResponses = cartItems.stream()
                .map(this::mapToCartItemResponse)
                .collect(Collectors.toList());
        return new CartResponse(user.getId(), itemResponses);
    }

    private CartItemResponse mapToCartItemResponse(CartItem cartItem) {
        ProductDTO productDTO = new ProductDTO(
                cartItem.getProduct().getId(),
                cartItem.getProduct().getName(),
                cartItem.getProduct().getDescription(),
                cartItem.getProduct().getUnit(),
                cartItem.getProduct().getPrice(),
                cartItem.getProduct().getWholesaler().getId()
        );
        return new CartItemResponse(cartItem.getId(), productDTO, cartItem.getQuantity());
    }
}



