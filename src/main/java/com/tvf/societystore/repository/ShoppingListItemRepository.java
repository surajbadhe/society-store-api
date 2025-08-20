package com.tvf.societystore.repository;

import com.tvf.societystore.entity.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Long> {
    // 👇 ADD THIS METHOD 👇
    List<ShoppingListItem> findByUserId(Long userId);
}
