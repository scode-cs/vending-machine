package com.scode.service.order;

import com.scode.domain.OrderDomain;
import com.scode.domain.RefillDomain;
import com.scode.domain.exception.DomainBusinessException;
import com.scode.domain.model.OrderModel;
import com.scode.domain.model.OrderReponseModel;
import com.scode.domain.model.ProductRefillModel;
import com.scode.persistence.entity.InventoryEntity;
import com.scode.persistence.entity.ProductEntity;
import com.scode.persistence.entity.PurchaseHistoryEntity;
import com.scode.persistence.repository.InventoryRepository;
import com.scode.persistence.repository.ProductRepository;
import com.scode.persistence.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderDomain {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final PurchaseRepository purchaseRepository;
    private final RefillDomain refillDomain;

    @Override
    @Transactional(rollbackOn = DomainBusinessException.class)
    public OrderReponseModel placeOrder(OrderModel orderModel) {

        Optional<ProductEntity> productEntityOpt = productRepository.findById(orderModel.getProductId());

        if (!productEntityOpt.isPresent()) {
            throw new DomainBusinessException("Invalid Product");
        }

        ProductEntity productEntity = productEntityOpt.get();
        InventoryEntity inventoryEntity = productEntity.getInventoryEntity();
        ProductRefillModel productRefillModel = ProductRefillModel.builder().id(orderModel.getProductId()).name(productEntity.getName())
                .avlQuantity(inventoryEntity.getAvlStock()).maxCapacity(inventoryEntity.getMaxCapacity()).build();

        if (inventoryEntity.getAvlStock() < orderModel.getQuantity()) {
            invokeRefillNotification(productRefillModel, inventoryEntity.getRefillPercentage());
            throw new DomainBusinessException("Quantity is not available");
        }

        // DB Operation
        purchaseRepository.save(PurchaseHistoryEntity.builder().productId(orderModel.getProductId())
                .purchasedAmount(orderModel.getQuantity()).purchasedAt(new Date()).purchasedBy("user").build());
        inventoryRepository.updateAvailableStock(inventoryEntity.getAvlStock() - orderModel.getQuantity(),
                inventoryEntity.getProductId());

        // Refill Notification
        invokeRefillNotification(productRefillModel, inventoryEntity.getRefillPercentage());

        return OrderReponseModel.builder().message("Order successfully placed").build();
    }

    private void invokeRefillNotification(ProductRefillModel productModel, Long percentage) {
        new Thread(() -> refillDomain.checkForForNotification(productModel, percentage)).start();
    }
}
