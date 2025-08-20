package com.tvf.societystore.repository;

import com.tvf.societystore.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    // 👇 ADD THIS METHOD 👇
    @Query("SELECT o FROM Offer o JOIN o.targetSocieties s WHERE s.id = :societyId AND o.status = 'ACTIVE'")
    List<Offer> findActiveOffersBySocietyId(@Param("societyId") Long societyId);
}
