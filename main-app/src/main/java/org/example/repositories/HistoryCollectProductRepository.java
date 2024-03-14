package org.example.repositories;

import org.example.models.HistoryCollectProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryCollectProductRepository extends JpaRepository<HistoryCollectProduct, Integer> {
    @Query("SELECT h FROM HistoryCollectProduct h WHERE h.collectedAt >= :startDate AND h.collectedAt < :endDate")
    List<HistoryCollectProduct> findProductsCollectedAt(@Param("startDate") LocalDateTime startDate,
                                                                 @Param("endDate") LocalDateTime endDate);
    @Query("SELECT h FROM HistoryCollectProduct h WHERE DATE(h.collectedAt) = :date")
    List<HistoryCollectProduct> findByDate(LocalDateTime date);
}
