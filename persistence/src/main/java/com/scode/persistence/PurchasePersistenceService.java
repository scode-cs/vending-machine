package com.scode.persistence;

import com.scode.persistence.entity.PurchaseHistoryEntity;
import com.scode.persistence.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchasePersistenceService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseHistoryEntity save(PurchaseHistoryEntity purchaseHistoryEntity) {
        return purchaseRepository.save(purchaseHistoryEntity);
    }
}
