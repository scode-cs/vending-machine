package com.scode.service.order;

import com.scode.domain.OrderDomain;
import com.scode.domain.RefillDomain;
import com.scode.domain.exception.DomainBusinessException;
import com.scode.domain.model.GenericProductResponseModel;
import com.scode.domain.model.OrderModel;
import com.scode.domain.model.ProductRefillModel;
import com.scode.persistence.InventoryPersistenceService;
import com.scode.persistence.ProductPersistenceService;
import com.scode.persistence.PurchasePersistenceService;
import com.scode.persistence.entity.InventoryEntity;
import com.scode.persistence.entity.ProductEntity;
import com.scode.persistence.entity.PurchaseHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderDomain {

    private final RefillDomain refillDomain;
    private final ProductPersistenceService productPersistenceService;
    private final InventoryPersistenceService inventoryPersistenceService;
    private final PurchasePersistenceService purchasePersistenceService;

    @Override
    @Transactional(rollbackOn = DomainBusinessException.class)
    public GenericProductResponseModel placeOrder(OrderModel orderModel) {

        Optional<ProductEntity> productEntityOpt = productPersistenceService.findById(orderModel.getProductId());

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
        purchasePersistenceService.save(PurchaseHistoryEntity.builder().productId(orderModel.getProductId())
                .purchasedAmount(orderModel.getQuantity()).purchasedAt(new Date()).purchasedBy(getLoggedinUser()).build());
        inventoryPersistenceService.updateAvailableStock(inventoryEntity.getAvlStock() - orderModel.getQuantity(),
                inventoryEntity.getProductId());

        // Refill Notification
        invokeRefillNotification(productRefillModel, inventoryEntity.getRefillPercentage());

        return GenericProductResponseModel.builder().productId(orderModel.getProductId())
                .productName(productEntity.getName()).message("Order successfully placed!").build();
    }

    private void invokeRefillNotification(ProductRefillModel productModel, Long percentage) {
        new Thread(() -> refillDomain.checkForForNotification(productModel, percentage)).start();
    }

    private String getLoggedinUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loggedinUser = "";
        if (principal instanceof UserDetails) {
            loggedinUser = ((UserDetails)principal).getUsername();
        }

        return loggedinUser;
    }

}
