package com.scode.persistence.repository;

import com.scode.persistence.entity.RefillHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefillRepository extends JpaRepository<RefillHistoryEntity, Long> {

}
