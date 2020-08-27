package com.scode.persistence.repository;

import com.scode.persistence.entity.PurchaseHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseRepository extends JpaRepository<PurchaseHistoryEntity, Long> {

}
