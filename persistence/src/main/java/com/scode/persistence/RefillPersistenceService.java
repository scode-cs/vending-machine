package com.scode.persistence;

import com.scode.persistence.entity.RefillHistoryEntity;
import com.scode.persistence.repository.RefillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RefillPersistenceService {

    private final RefillRepository refillRepository;

    public RefillHistoryEntity save(RefillHistoryEntity refillHistoryEntity) {
        return refillRepository.save(refillHistoryEntity);
    }
}
