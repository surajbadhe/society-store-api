package com.tvf.societystore.service.impl;

import com.tvf.societystore.dto.shoppinglist.ShoppingListItemDTO;
import com.tvf.societystore.entity.ShoppingListItem;
import com.tvf.societystore.entity.User;
import com.tvf.societystore.exception.ResourceNotFoundException;
import com.tvf.societystore.repository.ShoppingListItemRepository;
import com.tvf.societystore.repository.UserRepository;
import com.tvf.societystore.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListItemRepository shoppingListItemRepository;
    private final UserRepository userRepository;

    @Override
    public List<ShoppingListItemDTO> getMyShoppingList() {
        User currentUser = getCurrentUser();
        return shoppingListItemRepository.findByUserId(currentUser.getId())
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShoppingListItemDTO addItemToList(ShoppingListItemDTO itemDTO) {
        User currentUser = getCurrentUser();
        ShoppingListItem item = new ShoppingListItem();
        item.setUser(currentUser);
        item.setProductDescription(itemDTO.productDescription());
        item.setQuantity(itemDTO.quantity());
        item.setFrequency(itemDTO.frequency());

        ShoppingListItem savedItem = shoppingListItemRepository.save(item);
        return mapToDTO(savedItem);
    }

    @Override
    public void removeItemFromList(Long itemId) {
        User currentUser = getCurrentUser();
        ShoppingListItem item = shoppingListItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        if (!item.getUser().getId().equals(currentUser.getId())) {
            throw new SecurityException("User not authorized to delete this item");
        }

        shoppingListItemRepository.delete(item);
    }

    // You'll need to add findByUserId to the repository
    // public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Long> {
    //     List<ShoppingListItem> findByUserId(Long userId);
    // }

    private User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Current user not found"));
    }

    private ShoppingListItemDTO mapToDTO(ShoppingListItem item) {
        return new ShoppingListItemDTO(
                item.getId(),
                item.getProductDescription(),
                item.getQuantity(),
                item.getFrequency()
        );
    }
}