package com.tvf.societystore.repository;


import com.tvf.societystore.entity.BulkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulkOrderRepository extends JpaRepository<BulkOrder, Long> {}