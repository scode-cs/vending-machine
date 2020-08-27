package com.scode.domain;

import com.scode.domain.model.ProductModel;
import com.scode.domain.model.ProductRefillModel;

public interface RefillDomain {
    public String notifyForRefill(ProductRefillModel productModel);
    public String checkForForNotification(ProductRefillModel productModel, Long percentage);
}
