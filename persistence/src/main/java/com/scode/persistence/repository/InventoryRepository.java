package com.scode.persistence.repository;

import com.scode.persistence.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    public InventoryEntity findByProductId(Long productId);

    @Modifying
    @Query("update InventoryEntity pe set pe.avlStock = ?1 where pe.productId = ?2")
    int updateAvailableStock(Long avlStock, Long productId);
}
