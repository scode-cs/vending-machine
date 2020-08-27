package com.scode.persistence;

import com.scode.persistence.entity.InventoryEntity;
import com.scode.persistence.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryPersistenceService {

    private final InventoryRepository inventoryRepository;

    public InventoryEntity findByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    public int updateAvailableStock(Long avlStock, Long productId) {
        return inventoryRepository.updateAvailableStock(avlStock, productId);
    }
}
