package com.scode.service;

import com.scode.domain.RefillDomain;
import com.scode.domain.exception.DomainBusinessException;
import com.scode.domain.model.GenericProductResponseModel;
import com.scode.domain.model.OrderReponseModel;
import com.scode.domain.model.ProductRefillModel;
import com.scode.domain.model.RefillModel;
import com.scode.persistence.InventoryPersistenceService;
import com.scode.persistence.ProductPersistenceService;
import com.scode.persistence.RefillPersistenceService;
import com.scode.persistence.entity.InventoryEntity;
import com.scode.persistence.entity.ProductEntity;
import com.scode.persistence.entity.RefillHistoryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefillService implements RefillDomain {

    private final ProductPersistenceService productPersistenceService;
    private final InventoryPersistenceService inventoryPersistenceService;
    private final RefillPersistenceService refillPersistenceService;

    @Override
    public String notifyForRefill(ProductRefillModel productModel) {

        // Call message service for Refill Notification
        // based on:
        // productModel.getId()
        // productModel.productName()
        // productModel.getAvlQuantity()
        // productModel.getMaxCapacity()

        log.info("Initiate Notification to Refill: ProductId: {}, ProductName: {}, AvailableQty: {}, MaxCapacity: {}",
                productModel.getId(), productModel.getName(), productModel.getAvlQuantity(), productModel.getMaxCapacity());

        return "success";
    }

    @Override
    public String checkForForNotification(ProductRefillModel productModel, Long percentage) {

        Long avlLimit = productModel.getMaxCapacity() * (percentage / 100);

        if (avlLimit > productModel.getAvlQuantity()) {
            notifyForRefill(productModel);
        }

        return "success";
    }

    @Override
    @Transactional(rollbackOn = DomainBusinessException.class)
    public GenericProductResponseModel refillOrder(RefillModel refillModel) {

        Optional<ProductEntity> productEntityOpt = productPersistenceService.findById(refillModel.getProductId());

        if (!productEntityOpt.isPresent()) {
            throw new DomainBusinessException("Invalid Product");
        }

        ProductEntity productEntity = productEntityOpt.get();
        InventoryEntity inventoryEntity = productEntity.getInventoryEntity();

        if (refillModel.getRefillQuantity() > (inventoryEntity.getAvlStock() + inventoryEntity.getMaxCapacity())) {
            throw new DomainBusinessException("Out of capacity!");
        }

        // DB Operation
        refillPersistenceService.save(RefillHistoryEntity.builder().productId(refillModel.getProductId())
                .refilledAmount(refillModel.getRefillQuantity()).refilledAt(new Date()).refilledBy("user").build());
        inventoryPersistenceService.updateAvailableStock(inventoryEntity.getAvlStock()+refillModel.getRefillQuantity(),
                inventoryEntity.getProductId());

        return GenericProductResponseModel.builder().productId(refillModel.getProductId())
                .productName(productEntity.getName()).message("Successfully Refilled!").build();
    }
}
