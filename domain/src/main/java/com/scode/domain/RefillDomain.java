package com.scode.domain;

import com.scode.domain.model.*;

public interface RefillDomain {
    public String notifyForRefill(ProductRefillModel productModel);
    public String checkForForNotification(ProductRefillModel productModel, Long percentage);
    public GenericProductResponseModel refillOrder(RefillModel refillModel);
}
