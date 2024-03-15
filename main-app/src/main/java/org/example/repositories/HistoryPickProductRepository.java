package org.example.repositories;

import org.example.models.HistoryPickProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryPickProductRepository extends JpaRepository<HistoryPickProduct, Integer> {
    @Query("SELECT h FROM HistoryPickProduct h WHERE h.pickAt >= :startDate AND h.pickAt < :endDate")
    List<HistoryPickProduct> findProductsCollectedAt(@Param("startDate") LocalDateTime startDate,
                                                     @Param("endDate") LocalDateTime endDate);
    @Query("SELECT h FROM HistoryPickProduct h WHERE DATE(h.pickAt) = :date")
    List<HistoryPickProduct> findByDate(LocalDateTime date);
}
