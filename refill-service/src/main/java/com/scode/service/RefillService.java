package com.scode.service;

import com.scode.domain.RefillDomain;
import com.scode.domain.model.ProductRefillModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RefillService implements RefillDomain {

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

        Long avlLimit = productModel.getMaxCapacity() * (percentage/100);

        if (avlLimit > productModel.getAvlQuantity()) {
            notifyForRefill(productModel);
        }

        return "success";
    }
}
